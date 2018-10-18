package com.apap2018.tugas1.repository;

import com.apap2018.tugas1.model.JabatanModel;
import com.apap2018.tugas1.model.PegawaiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, Long> {
    List<PegawaiModel> findAllByNip(String nip);
    List<PegawaiModel> findByJabatan(JabatanModel jabatanModel);
    long countByTanggalLahirAndTahunMasuk(Date tangalLahir, String tahunMasuk);
}
