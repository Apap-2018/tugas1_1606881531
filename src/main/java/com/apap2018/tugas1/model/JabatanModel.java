package com.apap2018.tugas1.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "jabatan")
public class JabatanModel {

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

    @NotNull
    @Column(name = "gaji_pokok", nullable = false)
    private String gajiPokok;

    // TODO many to many buat ke pegawai

}
