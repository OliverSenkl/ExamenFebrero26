package model;
import java.util.List;
import java.util.ArrayList;


/**
 * Write a description of class ProductoPerecedero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ProductoPerecedero extends Producto
{
    private String dateCaducidad;   
    
    public ProductoPerecedero(String codigoProducto, String descripcion, double precio, int stock,String dateCaducidad){
        super(codigoProducto,descripcion,precio,stock);
        this.dateCaducidad = dateCaducidad;
    }
    
    public String getDateCaducidad(){
        return dateCaducidad;
    }
    
    public void setDateCaducidad(String dateCaducidad){
        this.dateCaducidad = dateCaducidad;
    }
    
}