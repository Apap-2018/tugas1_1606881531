package com.apap2018.tugas1.controller;

import com.apap2018.tugas1.model.InstansiModel;
import com.apap2018.tugas1.model.JabatanModel;
import com.apap2018.tugas1.model.PegawaiModel;
import com.apap2018.tugas1.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class PegawaiController {

    @Autowired
    PegawaiService pegawaiService;

    @RequestMapping("/")
    private String home() {
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

    @RequestMapping("/pegawai/tambah")
    private String pegawaiTambahForm(Model model){
        model.addAttribute("pegawaiBaru", new PegawaiModel());
        return "pegawai-tambah";
    }

    @RequestMapping("/pegawai/tambah")
    private String pegawaiTambahProcess(@ModelAttribute PegawaiModel pegawai) {
        pegawaiService.addPegawai(pegawai);
        return "pegawai-tambah"; // TODO bikin halaman baru untuk konfirmasi
    }
}
