package com.apap2018.tugas1.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "instansi")
public class InstansiModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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
    @JoinColumn(name = "id_provinsi", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ProvinsiModel provinsi;

    @OneToMany(
            mappedBy = "instansi",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    private List<PegawaiModel> instansiPegawai;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public String getNamadanProvinsi() {
        return getNama() + " - " + getProvinsi().getNama();
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public ProvinsiModel getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(ProvinsiModel provinsi) {
        this.provinsi = provinsi;
    }

    public List<PegawaiModel> getInstansiPegawai() {
        return instansiPegawai;
    }

    public void setInstansiPegawai(List<PegawaiModel> instansiPegawai) {
        this.instansiPegawai = instansiPegawai;
    }
}
