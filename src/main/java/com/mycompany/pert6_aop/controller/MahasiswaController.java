/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pert6_aop.controller;

import com.mycompany.pert6_aop.model.ModelMahasiswa;
import java.util.List;

/**
 *
 * @author Aditya Ananta
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mycompany.pert6_aop.model.ModelMahasiswa;
import com.mycompany.pert6_aop.service.MahasiswaService;
import org.springframework.stereotype.Component;
import java.util.List;
import org.springframework.stereotype.Controller;


@Component // <-- GANTI INI
public class MahasiswaController {

    @Autowired // <-- BIARKAN INI, ini adalah inti dari Spring
    private MahasiswaService mahasiswaService;

    // Hapus constructor manual (jika ada)

    // Method-method ini HARUS sesuai dengan apa yang dipanggil oleh View
    // (Tanpa @RequestBody, @PathVariable, dan tidak return String)

    public void addMahasiswa(ModelMahasiswa mhs) {
        mahasiswaService.addMhs(mhs);
    }

    public ModelMahasiswa getMahasiswa(int id) {
        return mahasiswaService.getMhs(id);
    }

    public void updateMahasiswa(ModelMahasiswa mhs) {
        mahasiswaService.updateMhs(mhs);
    }

    public void deleteMahasiswa(int id) {
        mahasiswaService.deleteMhs(id);
    }

    public List<ModelMahasiswa> getAllMahasiswa() {
        return mahasiswaService.getAllMahasiswa();
    }
}

