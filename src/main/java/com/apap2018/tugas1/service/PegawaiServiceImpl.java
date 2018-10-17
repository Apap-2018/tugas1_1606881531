package com.apap2018.tugas1.service;

import com.apap2018.tugas1.model.PegawaiModel;
import com.apap2018.tugas1.repository.PegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{

    @Autowired
    PegawaiDb pegawaiDb;

    @Override
    public PegawaiModel getPegawaiByNIP(String nip) {
        return pegawaiDb.findAllByNip(nip).get(0);
    }

    @Override
    public void addPegawai(PegawaiModel pegawai) {
        pegawaiDb.save(pegawai);
    }
}
