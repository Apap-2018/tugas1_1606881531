package com.apap2018.tugas1.service;

import com.apap2018.tugas1.model.JabatanModel;

import java.util.List;

public interface JabatanService {
    JabatanModel testOneJabatan();
    void addJabatan(JabatanModel jabatanModel);

    JabatanModel getJabatanById(Long idBeneran);

    List<JabatanModel> getAllJabatan();
}
