package com.apap2018.tugas1.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pegawai")
public class PegawaiModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nip", unique = true)
    private String nip;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat_lahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    private Date tanggalLahir;

    @NotNull
    @Size(max = 255)
    @Column(name = "tahun_masuk", nullable = false)
    private String tahunMasuk;

    // TODO many to one ke instansi
    // TODO many to many buat ke Jabatan
}
