package com.aldia.poli.dto;

import com.aldia.poli.model.TipoActividad;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class ActividadDto {
    @NotBlank private String nombre;
    @NotNull private TipoActividad tipo;
    @DecimalMin("0.01") @DecimalMax("100.0") private double porcentaje;
    @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) private LocalDate fechaEntrega;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public TipoActividad getTipo() { return tipo; }
    public void setTipo(TipoActividad tipo) { this.tipo = tipo; }
    public double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }
}
