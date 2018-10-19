package com.apap2018.tugas1.service;

import com.apap2018.tugas1.model.PegawaiModel;
import com.apap2018.tugas1.repository.InstansiDb;
import com.apap2018.tugas1.repository.PegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{

    @Autowired
    PegawaiDb pegawaiDb;

    @Autowired
    InstansiDb instansiDb;

    @Override
    public PegawaiModel getPegawaiByNIP(String nip) {
        return pegawaiDb.findAllByNip(nip).get(0);
    }

    @Override
    public void addPegawai(PegawaiModel pegawai) {
        pegawaiDb.save(pegawai);
    }

    @Override
    public long countByTanggalLahirAndTahunMasuk(Date tanggalLahir, String tahun) {
        return pegawaiDb.countByTanggalLahirAndTahunMasuk(tanggalLahir, tahun);
    }

    @Override
    public List<PegawaiModel> getAllPegawai() {
        return pegawaiDb.findAll();
    }

    @Override
    public List<PegawaiModel> getPegawaiByInstansiId(Long idInstansi) {
        return pegawaiDb.findByInstansi(instansiDb.getOne(idInstansi));
    }
}
