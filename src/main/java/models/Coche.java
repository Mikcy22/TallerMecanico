package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Coche")
public class Coche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "año")
    private int año;
    @Column(name = "precio")
    private double precio;
    @OneToMany(mappedBy = "coche", cascade = CascadeType.ALL)
    private List<Reparacion> reparaciones;
    @ManyToMany(mappedBy = "coches",cascade = CascadeType.ALL)
    private List<Venta> ventas;
    @ManyToMany(mappedBy = "coches")
    private List<Cliente> clientes;

    public Coche() {
    }

    public Coche(String marca, String modelo, int año, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.precio = precio;
    }

    public Coche(int id, String marca, String modelo, int año, double precio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.precio = precio;
    }

    public Coche(String marca, String modelo, int año, double precio, List<Reparacion> reparaciones, List<Venta> ventas, List<Cliente> clientes) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.precio = precio;
        this.reparaciones = reparaciones;
        this.ventas = ventas;
        this.clientes = clientes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Reparacion> getReparaciones() {
        return reparaciones;
    }

    public void setReparaciones(List<Reparacion> reparaciones) {
        this.reparaciones = reparaciones;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", año=" + año +
                ", precio=" + precio +
                ", reparaciones=" + reparaciones +
                ", ventas=" + ventas +
                ", clientes=" + clientes +
                '}';
    }
}
