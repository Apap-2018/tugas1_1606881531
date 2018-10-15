package com.apap2018.tugas1.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "instansi")
public class InstansiModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "deskripsi", nullable = false)
    private String deskripsi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinsi_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ProvinsiModel provinsi;

    // TODO one to many ke pegawai
}
