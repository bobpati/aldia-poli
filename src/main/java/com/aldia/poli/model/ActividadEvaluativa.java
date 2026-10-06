package com.aldia.poli.model;

import com.aldia.poli.observer.IObserver;
import com.aldia.poli.observer.ISubject;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actividad_evaluativa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class ActividadEvaluativa implements ISubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @DecimalMin("0.01")
    @DecimalMax("100.0")
    @Column(nullable = false)
    private double porcentaje;

    @Column(name = "fecha_entrega", nullable = false)
    private LocalDate fechaEntrega;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoActividad estado = EstadoActividad.PENDIENTE;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;

    @OneToOne(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Calificacion calificacion;

    @Transient
    private final List<IObserver> observers = new ArrayList<>();

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    public EstadoActividad getEstado() { return estado; }
    public void setEstado(EstadoActividad estado) { this.estado = estado; }
    public Materia getMateria() { return materia; }
    public void setMateria(Materia materia) { this.materia = materia; }
    public Calificacion getCalificacion() { return calificacion; }

    public void registrarCalificacion(double valor, String observacion) {
        if (valor < 0.0 || valor > 5.0) throw new IllegalArgumentException("La nota debe estar entre 0.0 y 5.0");
        if (calificacion == null) {
            calificacion = new Calificacion();
            calificacion.setActividad(this);
        }
        calificacion.setValor(valor);
        calificacion.setObservacion(observacion);
        calificacion.setFechaAsignacion(LocalDate.now());
        estado = EstadoActividad.CALIFICADA;
        notificarObservers("Calificación actualizada: " + nombre + " = " + valor);
    }

    public double calcularNota() {
        return calificacion == null ? 0.0 : calificacion.getValor() * (porcentaje / 100.0);
    }

    public abstract TipoActividad getTipo();

    @Override public void agregarObserver(IObserver observer) { observers.add(observer); }
    @Override public void eliminarObserver(IObserver observer) { observers.remove(observer); }
    @Override public void notificarObservers(String mensaje) { observers.forEach(o -> o.actualizar(mensaje)); }
}
