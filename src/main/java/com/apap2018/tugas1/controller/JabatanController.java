package com.apap2018.tugas1.controller;

import com.apap2018.tugas1.model.JabatanModel;
import com.apap2018.tugas1.service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


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
        model.addAttribute("pegawaiCount", jabatanService.getPegawaiCountById(idBeneran));
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
        jabatanService.addJabatan(jabatan);
        model.addAttribute("status", "sukses!");
        return "jabatan-status";
    }

    @RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
    public String hapusJabatan(@RequestParam("id") String id, Model model) {
        jabatanService.deleteJabatanModel(Long.valueOf(id));
        model.addAttribute("status", "sukses menghapus!");
        return "jabatan-status";
    }

    @RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
    public String tampilkanSemuaJabatan(Model model) {
        model.addAttribute("jabatanAll", jabatanService.getAllJabatan());
        return "jabatan-view-all";
    }
}
