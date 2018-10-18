package com.apap2018.tugas1.service;

import com.apap2018.tugas1.model.InstansiModel;
import com.apap2018.tugas1.repository.InstansiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService {

    @Autowired
    InstansiDb instansiDB;

    @Override
    public InstansiModel testOneInstansiModel() {
        return instansiDB.getOne((long) 1101);
    }
}
