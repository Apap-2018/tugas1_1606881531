package com.apap2018.tugas1.controller;

import com.apap2018.tugas1.model.JabatanModel;
import com.apap2018.tugas1.service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class JabatanController {
    @Autowired
    JabatanService jabatanService;

    @RequestMapping("/jabatan/tambah")
    public String tambahJabatanForm(Model model) {
        model.addAttribute("jabatanBaru", new JabatanModel());
        return "jabatan-tambah";
    }

    @RequestMapping(value="/jabatan/tambah", method=RequestMethod.POST)
    public String tambahJabatanProcess(@ModelAttribute JabatanModel jabatan, Model model) {
        jabatanService.addJabatan(jabatan);
        model.addAttribute("status", "sukses");
        return "jabatan-tambah";
    }

    @RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
    public String tampilkanJabatan(@RequestParam("idJabatan") String idJabatan, Model model) {
        Long idBeneran = Long.valueOf(idJabatan);
        model.addAttribute("jabatan", jabatanService.getJabatanById(idBeneran));
        return "jabatan-view";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
    public String ubahJabatan(@RequestParam("idJabatan") String idJabatan, Model model) {
        Long idBeneran = Long.valueOf(idJabatan);
        model.addAttribute("jabatan", jabatanService.getJabatanById(idBeneran));
        return "jabatan-ubah";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
    public String ubahJabatan(@ModelAttribute JabatanModel jabatan, Model model) {
        model.addAttribute("status", "sukses!");
        return "jabatan-ubah";
    }
}
