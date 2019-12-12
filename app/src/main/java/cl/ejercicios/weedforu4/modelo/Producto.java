package cl.ejercicios.weedforu4.modelo;

public class Producto {
    private String nombre;
    private int cantidad;
    private String unidad;
    private boolean estado; //true si está pendiente - false si está comprado
    private static final boolean PENDIENTE=true;

    //Arreglo de productos para probar la lista
/*
    public static final Producto[] productos={
            new Producto("Bebida",1,"litro"),
            new Producto("Papas fritas",2,"Bolsas"),
            new Producto("Aceitunas",2,"paquetes")
    };
    */

    //Constructor


    public Producto(String nombre, int cantidad, String unidad, boolean estado) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.estado = estado;
    }

    public Producto(String nombre, int cantidad, String unidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.estado=PENDIENTE;
    }

    //Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    //toString para mostrar en la lista

    @Override
    public String toString() {
        //Devuelve el nombre del producto y si está comprado o pendiente
        String comprado;
        if(estado) comprado="pendiente";
        else comprado="comprado";
        return nombre + ": " + comprado;
    }
}
