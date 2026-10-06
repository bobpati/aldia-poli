package com.aldia.poli.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "calificacion")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double valor;

    private String observacion;

    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "actividad_id", nullable = false, unique = true)
    private ActividadEvaluativa actividad;

    public Long getId() { return id; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public LocalDate getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(LocalDate fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }
    public ActividadEvaluativa getActividad() { return actividad; }
    public void setActividad(ActividadEvaluativa actividad) { this.actividad = actividad; }
}
