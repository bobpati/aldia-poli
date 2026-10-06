package com.aldia.poli.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistroDto {
    @NotBlank private String nombre;
    @NotBlank @Email private String correo;
    @NotBlank @Size(min = 6) private String clave;
    @NotBlank private String programaAcademico;
    private String sede;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
    public String getProgramaAcademico() { return programaAcademico; }
    public void setProgramaAcademico(String programaAcademico) { this.programaAcademico = programaAcademico; }
    public String getSede() { return sede; }
    public void setSede(String sede) { this.sede = sede; }
}
