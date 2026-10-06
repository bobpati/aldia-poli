package com.aldia.poli.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
@Entity @DiscriminatorValue("PARCIAL")
public class Parcial extends ActividadEvaluativa { @Override public TipoActividad getTipo(){ return TipoActividad.PARCIAL; } }
