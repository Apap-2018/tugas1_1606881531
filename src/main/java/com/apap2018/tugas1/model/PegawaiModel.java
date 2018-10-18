package com.apap2018.tugas1.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_instansi", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private InstansiModel instansi;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "jabatan_pegawai", joinColumns = { @JoinColumn(name = "id_pegawai") }, inverseJoinColumns = { @JoinColumn(name = "id_jabatan") })
    private Set<JabatanModel> jabatan = new HashSet<>(); //If gone wrong, use list

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTahunMasuk() {
        return tahunMasuk;
    }

    public void setTahunMasuk(String tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }

    public InstansiModel getInstansi() {
        return instansi;
    }

    public void setInstansi(InstansiModel instansi) {
        this.instansi = instansi;
    }

    public Set<JabatanModel> getJabatan() {
        return jabatan;
    }

    public void setJabatan(Set<JabatanModel> jabatan) {
        this.jabatan = jabatan;
    }

    public double getGaji() {
        double gajiMax = Double.MIN_VALUE;
        InstansiModel instansiModel = getInstansi();
        ProvinsiModel provinsiModel = instansiModel.getProvinsi(); // Gotta be like this. Idk why. But it must.
        double tunjangan = provinsiModel.getPresentaseTunjangan() / 100;

        for (JabatanModel jm : getJabatan()) {
            if (jm.getGajiPokok() > gajiMax) gajiMax = jm.getGajiPokok();
        }

        return gajiMax + (tunjangan * gajiMax);
    }
}
