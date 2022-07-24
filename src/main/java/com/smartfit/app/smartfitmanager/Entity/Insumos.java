package com.smartfit.app.smartfitmanager.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "insumos")
public class Insumos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Column(name = "nombre_insumo", nullable = false, length = 30)
    private String nombre;

    @Column(name = "descripcion_insumo", nullable = false, length = 30)
    private String descripcion;

    @Column(name = "unidad_insumo", nullable = false, length = 30)
    private String unidad;

    @Column(name = "categoria_insumo", nullable = false, length = 30)
    private String categoria;
    

    public Insumos(long id, String nombre, String descripcion, String unidad, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.categoria = categoria;
    }

    public Insumos() {
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad() {
        return this.unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

