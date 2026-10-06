package com.aldia.poli.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificacion")
public class Notificacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 500) private String mensaje;
    @Column(name = "fecha_envio", nullable = false) private LocalDateTime fechaEnvio = LocalDateTime.now();
    @Column(nullable = false) private boolean leida = false;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actividad_id")
    private ActividadEvaluativa actividad;

    public Long getId() { return id; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
    public boolean isLeida() { return leida; }
    public void setLeida(boolean leida) { this.leida = leida; }
    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }
    public ActividadEvaluativa getActividad() { return actividad; }
    public void setActividad(ActividadEvaluativa actividad) { this.actividad = actividad; }
}
