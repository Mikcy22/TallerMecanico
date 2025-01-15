package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "Venta")
public class Venta{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "monto")
    private double monto;
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToMany
    @JoinTable(
            name = "Venta_Coche",
            joinColumns = {@JoinColumn(name = "venta_id")},
            inverseJoinColumns = {@JoinColumn(name = "coche_id")}
    )
    private List<Coche> coches;

    public Venta() {
    }

    public Venta(Date fecha, double monto, Empleado empleado, Cliente cliente, List<Coche> coches) {
        this.fecha = fecha;
        this.monto = monto;
        this.empleado = empleado;
        this.cliente = cliente;
        this.coches = coches;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Coche> getCoches() {
        return coches;
    }

    public void setCoches(List<Coche> coches) {
        this.coches = coches;
    }
}
