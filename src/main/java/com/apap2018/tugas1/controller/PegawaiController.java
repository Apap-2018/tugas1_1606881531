package com.apap2018.tugas1.controller;

import com.apap2018.tugas1.model.JabatanModel;
import com.apap2018.tugas1.model.PegawaiModel;
import com.apap2018.tugas1.service.InstansiService;
import com.apap2018.tugas1.service.JabatanService;
import com.apap2018.tugas1.service.PegawaiService;
import com.apap2018.tugas1.service.ProvinsiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PegawaiController {

    @Autowired
    PegawaiService pegawaiService;

    @Autowired
    ProvinsiService provinsiService;

    @Autowired
    JabatanService jabatanService;

    @Autowired
    InstansiService instansiService;

    @RequestMapping("/")
    private String home(Model model) {
        model.addAttribute("jabatanList", jabatanService.getAllJabatan());
        model.addAttribute("instansiList", instansiService.getAllInstansi());
        return "home";
    }

    @RequestMapping(value = "/pegawai", method = RequestMethod.GET)
    private String tampilkanPegawai(@RequestParam("nip") String nip, Model model) {
        PegawaiModel byNip = pegawaiService.getPegawaiByNIP(nip);
        Double gaji = byNip.getGaji();

        model.addAttribute("pegawai", byNip);
        model.addAttribute("gaji", gaji.intValue());
        return "pegawai-view";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping("/pegawai/tambah")
    private String pegawaiTambahForm(Model model) {
        model.addAttribute("pegawaiBaru", new PegawaiModel());
        model.addAttribute("provinsiBanyak", provinsiService.getAllProvinsiOrderedAsc());
        return "pegawai-tambah";
    }

    // Sudah fungsional selain jabatan dan instansi
    @RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
    private String pegawaiTambahProcess(@ModelAttribute PegawaiModel pegawai, Model model) {

        Set<JabatanModel> testJabatan = new HashSet<>(); //DEBUG

        testJabatan.add(jabatanService.testOneJabatan()); //DEBUG
        pegawai.setJabatan(testJabatan); //DEBUG

        pegawai.setInstansi(instansiService.testOneInstansiModel()); //DEBUG

        /*
        NIP MAKER
         */
        StringBuilder nipResult = new StringBuilder();
        nipResult.append(pegawai.getInstansi().getId());
        nipResult.append(pegawai.getTanggalLahir().getDay())
                .append(pegawai.getTanggalLahir().getMonth())
                .append(pegawai.getTanggalLahir().getYear());
        nipResult.append(pegawai.getTahunMasuk());
        nipResult.append(pegawaiService.countByTanggalLahirAndTahunMasuk(pegawai.getTanggalLahir(), pegawai.getTahunMasuk()));
        pegawai.setNip(nipResult.toString());
        /*
        NIP MAKER END
         */

        pegawaiService.addPegawai(pegawai);
        model.addAttribute("status", "SUKSES");
        return "pegawai-tambah";
    }

    @RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
    public String cariPegawai(@RequestParam(value = "idProvinsi", required = false) Optional<Long> idProvinsi
            , @RequestParam(value = "idInstansi", required = false) Optional<Long> idInstansi
            , @RequestParam(value = "idJabatan", required = false) Optional<Long> idJabatan
            , Model model) {
        if (idProvinsi.isPresent() || idJabatan.isPresent() || idInstansi.isPresent()) {
            List<PegawaiModel> semuaPegawai = pegawaiService.getAllPegawai(); //ib4 "har ini kan gak efisien"
            if (idProvinsi.isPresent()) {
                semuaPegawai.removeIf(i -> !(i.getInstansi().getProvinsi().getId() == idProvinsi.get()));
            }
            if (idJabatan.isPresent()) {
                semuaPegawai.removeIf(i -> !(jabatanExist(i,idJabatan.get())));
            }
            if (idInstansi.isPresent()) {
                semuaPegawai.removeIf(i -> !(i.getInstansi().getId() == idInstansi.get()));
            }
            model.addAttribute("pegawaiFiltered", semuaPegawai);
        }
        model.addAttribute("provinsiAll", provinsiService.getAllProvinsi());
        model.addAttribute("instansiAll", instansiService.getAllInstansi());
        model.addAttribute("jabatanAll", jabatanService.getAllJabatan());
        return "pegawai-cari-spesifik";
    }

    @RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
    public String pegawaiTermudaTertua(@RequestParam("idInstansi") Long idInstansi, Model model) {
        List<PegawaiModel> pegawaiInstansi = pegawaiService.getPegawaiByInstansiId(idInstansi);
        pegawaiInstansi.sort(new Comparator<PegawaiModel>() {
            @Override
            public int compare(PegawaiModel o1, PegawaiModel o2) {
                return o1.getTanggalLahir().compareTo(o2.getTanggalLahir());
            }
        });

        model.addAttribute("tertua", pegawaiInstansi.get(0));
        model.addAttribute("termuda", pegawaiInstansi.get(pegawaiInstansi.size() - 1));
        return "pegawai-termuda-tertua"; //TODO buat page ini
    }

    public boolean jabatanExist(PegawaiModel pegawai, Long idJabatan){
        for (JabatanModel jb : pegawai.getJabatan()) {
            if (jb.getId() == idJabatan) return true;
        }
        return false;
    }
}
