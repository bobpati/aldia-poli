package com.aldia.poli.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiante")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Estudiante extends Persona {
    @Column(name = "programa_academico", nullable = false)
    private String programaAcademico;

    private String sede;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Semestre> semestres = new ArrayList<>();

    public String getProgramaAcademico() { return programaAcademico; }
    public void setProgramaAcademico(String programaAcademico) { this.programaAcademico = programaAcademico; }
    public String getSede() { return sede; }
    public void setSede(String sede) { this.sede = sede; }
    public List<Semestre> getSemestres() { return semestres; }

    public void agregarSemestre(Semestre semestre) {
        semestres.add(semestre);
        semestre.setEstudiante(this);
    }
}
