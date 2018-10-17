package com.apap2018.tugas1.service;

import com.apap2018.tugas1.model.PegawaiModel;

public interface PegawaiService {
    PegawaiModel getPegawaiByNIP(String nip);

    void addPegawai(PegawaiModel pegawai);
}
