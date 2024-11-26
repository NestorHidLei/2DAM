package model;

import java.util.List;

import jakarta.persistence.*;


@Entity
public class Localidad {
    @Id
    private Integer codLocalidad;

    private String nombre;

    @OneToMany(mappedBy = "localidad", cascade= CascadeType.ALL)
    private List<Restaurante> restaurante;
    
    public Integer getCodLocalidad() {
        return codLocalidad;
    }

    public void setCodLocalidad(Integer codLocalidad) {
        this.codLocalidad = codLocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
