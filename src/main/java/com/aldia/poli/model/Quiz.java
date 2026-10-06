package com.aldia.poli.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
@Entity @DiscriminatorValue("QUIZ")
public class Quiz extends ActividadEvaluativa { @Override public TipoActividad getTipo(){ return TipoActividad.QUIZ; } }
