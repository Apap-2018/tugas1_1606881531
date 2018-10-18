package com.apap2018.tugas1.repository;

import com.apap2018.tugas1.model.ProvinsiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinsiDb extends JpaRepository<ProvinsiModel, Long> {

}
