package com.apap2018.tugas1.service;

import com.apap2018.tugas1.model.ProvinsiModel;
import com.apap2018.tugas1.repository.ProvinsiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {
    @Autowired
    ProvinsiDb provinsiDb;

    @Override
    public List<ProvinsiModel> getAllProvinsiOrderedAsc() {
        List<ProvinsiModel> ordered = provinsiDb.findAll();
        ordered.sort((o1, o2) -> o1.getNama().compareTo(o2.getNama()));
        return ordered;
    }

    @Override
    public List<ProvinsiModel> getAllProvinsi() {
        return provinsiDb.findAll();
    }
}
