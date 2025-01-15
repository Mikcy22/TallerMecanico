package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<Venta> ventas;
    @ManyToMany
    @JoinTable(
            name = "Cliente_Coche",
            joinColumns = {@JoinColumn(name = "cliente_id")},
            inverseJoinColumns = {@JoinColumn(name = "coche_id")}
    )
    private List<Coche> coches;

    public Cliente() {
    }

    public Cliente(String nombre, String email, String telefono, List<Venta> ventas, List<Coche> coches) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.ventas = ventas;
        this.coches = coches;
    }

    public Cliente(int id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public Cliente(String nombre, String email, String telefono, List<Coche> coches) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.coches = coches;
    }

    public Cliente(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public Cliente(int id, String nombre, String email, String telefono, List<Venta> ventas, List<Coche> coches) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.ventas = ventas;
        this.coches = coches;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Coche> getCoches() {
        return coches;
    }

    public void setCoches(List<Coche> coches) {
        this.coches = coches;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
}
