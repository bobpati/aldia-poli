package com.aldia.poli.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "materia")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @NotBlank
    @Column(nullable = false)
    private String codigo;

    @Min(1)
    @Column(nullable = false)
    private int creditos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "semestre_id", nullable = false)
    private Semestre semestre;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("fechaEntrega ASC")
    private List<ActividadEvaluativa> actividades = new ArrayList<>();

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoCalendario> eventos = new ArrayList<>();

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }
    public Semestre getSemestre() { return semestre; }
    public void setSemestre(Semestre semestre) { this.semestre = semestre; }
    public List<ActividadEvaluativa> getActividades() { return actividades; }
    public List<EventoCalendario> getEventos() { return eventos; }

    public void agregarActividad(ActividadEvaluativa actividad) {
        actividades.add(actividad);
        actividad.setMateria(this);
    }
}
