package model;

//Clase producto

public class Producto implements Comparable<Producto> {
    private String codigoProducto;
    private String descripcion;
    private double precio;
    private int stock;

    // Constructor
    public Producto(String codigoProducto, String descripcion, double precio, int stock) {
        setCodigoProducto(codigoProducto); // Usamos el setter para validar
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters
    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        if (codigoProducto == null || codigoProducto.length() < 8 || codigoProducto.length() > 16) {
            throw new IllegalArgumentException("El código de producto debe ser alfanumérico y tener entre 8 y 16 caracteres.");
        }
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio>=0.0) this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void changeStock(int newStock) {
        if (this.stock + newStock < 0) return; //nunca tenemos stock negativo
        this.stock = newStock;  //si newStock es negativo, se quita al almacén unidades del producto
    }

    // Método para mostrar la información del producto. CSV Plus
    @Override
    public String toString() {
        return "Clase=Producto;" +
                "codigoProducto=" + codigoProducto + ";" +
                "descripcion=" + descripcion + ";" +
                "precio=" + precio + ";" +
                "stock=" + stock;
    }
    
    public static Producto cargarDesdeCSVPlus() {
        String codigoProducto = "";
        String descripcion = "";
        double precio = 0;
        int stock = 0;
        
        //Poner aquí tu código
        
        return new Producto(codigoProducto, descripcion, precio, stock);
        
    }
    
    public int compareTo(Producto p){
        return this.getDescripcion().compareToIgnoreCase(p.getDescripcion());
    }
    
    public int comparableToCodigo(Producto p){
        return this.getDescripcion().compareToIgnoreCase(p.getCodigoProducto());
    }
    
    
    
}
