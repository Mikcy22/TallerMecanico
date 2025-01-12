package models;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "Reparacion")
public class Reparacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "costo")
    private double costo;
    @ManyToOne
    @JoinColumn(name = "coche_id")
    private Coche coche;
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    public Reparacion(String descripcion, Date fecha, double costo, Coche coche, Empleado empleado) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.costo = costo;
        this.coche = coche;
        this.empleado = empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
