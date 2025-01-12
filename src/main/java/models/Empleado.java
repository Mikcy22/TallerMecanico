package models;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "puesto")
    private String puesto;
    @Column(name = "salario")
    private double salario;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Usuario usuario;
    @OneToMany(mappedBy = "empleado",cascade = CascadeType.ALL)
    private List<Reparacion> reparaciones;

    public Empleado() {
    }

    public Empleado(String nombre, String puesto, double salario, Usuario usuario, List<Reparacion> reparaciones) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.usuario = usuario;
        this.reparaciones = reparaciones;
    }


    public Empleado(String nombre, String puesto, double salario) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
    }

    public Empleado(int id, String nombre, String puesto, double salario) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Reparacion> getReparaciones() {
        return reparaciones;
    }

    public void setReparaciones(List<Reparacion> reparaciones) {
        this.reparaciones = reparaciones;
    }
}
