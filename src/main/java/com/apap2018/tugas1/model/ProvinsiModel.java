package com.apap2018.tugas1.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "provinsi")
public class ProvinsiModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "presentase_tunjangan", nullable = false)
    private double presentaseTunjangan;

    @OneToMany(
            mappedBy = "provinsi",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    private List<InstansiModel> provinsiInstansi;
}
