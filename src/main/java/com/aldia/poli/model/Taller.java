package com.aldia.poli.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TALLER")
public class Taller extends ActividadEvaluativa {
    @Override
    public TipoActividad getTipo() {
        return TipoActividad.TALLER;
    }
}
