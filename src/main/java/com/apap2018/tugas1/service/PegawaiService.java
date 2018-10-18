package com.apap2018.tugas1.service;

import com.apap2018.tugas1.model.PegawaiModel;

import java.sql.Date;

public interface PegawaiService {
    PegawaiModel getPegawaiByNIP(String nip);

    void addPegawai(PegawaiModel pegawai);

    long countByTanggalLahirAndTahunMasuk(Date tanggalLahir, String tahun);
}
