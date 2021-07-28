package Model.Models;

public class Producto {
    private int id;
    private String Nombre;
    private int cantidad;
    private double costoUnitario;
    private double valorVenta;

    public Producto(int id, String nombre, int cantidad, double costoUnitario, double valorVenta) {
        this.id = id;
        Nombre = nombre;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
        this.valorVenta = valorVenta;
    }

    public Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(double valorVenta) {
        this.valorVenta = valorVenta;
    }
}
