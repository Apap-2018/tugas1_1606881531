package com.apap2018.tugas1.service;

import com.apap2018.tugas1.model.JabatanModel;
import com.apap2018.tugas1.repository.JabatanDb;
import com.apap2018.tugas1.repository.PegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{

    @Autowired
    JabatanDb jabatanDb;

    @Autowired
    PegawaiDb pegawaiDb;

    @Override
    public JabatanModel testOneJabatan() {
        return jabatanDb.getOne(1L);
    }

    @Override
    public void addJabatan(JabatanModel jabatanModel) {
        jabatanDb.save(jabatanModel);
    }

    @Override
    public JabatanModel getJabatanById(Long idBeneran) {
        return jabatanDb.getOne(idBeneran);
    }

    @Override
    public List<JabatanModel> getAllJabatan() {
        return jabatanDb.findAll();
    }

    @Override
    public void deleteJabatanModel(long jabatanId) {
        jabatanDb.deleteById(jabatanId);
    }

    @Override
    public long getPegawaiCountById(Long id) {
        return pegawaiDb.countByJabatan(jabatanDb.getOne(id));
    }
}
