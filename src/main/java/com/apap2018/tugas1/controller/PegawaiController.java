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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
}
