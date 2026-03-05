package controller;
import model.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Controlador
{
    // instance variables 
    private List<Producto> misProductos;
    private List<Producto> misProductosRetirados;
    private List<Producto> listaDeProductosInicial;
    
    // Singleton
    private static Controlador singleton;
    
    /**
     * Constructor for objects of class Controlador
     */
    private Controlador()
    {
        // CORREGIDO: quitar redeclaración
        listaDeProductosInicial = DataAccess.loadData();
        
        if(listaDeProductosInicial == null){
            listaDeProductosInicial = new ArrayList<Producto>();
        }
        
        misProductos = new ArrayList<Producto>();
        misProductosRetirados = new ArrayList<Producto>();
    }
    
    public static Controlador getSingleton()
    {
        // CORREGIDO: devolver singleton
        if(singleton == null){
            singleton = new Controlador();
        }
        return singleton;
    }
    
    public void addProducto(String datos){
        String [] dato = datos.split(";");
        
        if (dato.length == 4){
            String codProducto = dato[0].trim();
            String descripcion = dato[1].trim();
            double precio = Double.parseDouble(dato[2].trim());
            int stock = Integer.parseInt(dato[3].trim());
            
            Producto nuevoProducto = new Producto(codProducto,descripcion,precio,stock);
            misProductos.add(nuevoProducto);
            System.out.println("Producto añadido");
        }
        
        if (dato.length == 5){
            String codProducto = dato[0].trim();
            String descripcion = dato[1].trim();
            double precio = Double.parseDouble(dato[2].trim());
            int stock = Integer.parseInt(dato[3].trim());
            String dateCaducidad = dato[4].trim();
            
            ProductoPerecedero p = new ProductoPerecedero(
            codProducto,descripcion,precio,stock,dateCaducidad);
            misProductos.add(p);
            System.out.println("Producto añadido");
        }
    }
    
    public String listarProductos(){
        String resultado ="";
        int i = 0;
        Collections.sort(misProductos);
        Collections.sort(listaDeProductosInicial);
        Collections.sort(misProductosRetirados);
        if(misProductos.isEmpty() && listaDeProductosInicial.isEmpty() && misProductosRetirados.isEmpty()){
            return "No hay Productos registrados";
        }
    
        for(Producto p: misProductos){
            if(i == 0){
                resultado +=("Productos Añadidos:\n");
                System.out.println("");
                }
            resultado += p.toString() + "\n";
            i++;
        }
        i = 0;
        for(Producto d: listaDeProductosInicial){
            if(i == 0){
                resultado +=("Productos Iniciales:\n");
                System.out.println("");
            }
            resultado += d.toString() + "\n";
            i++;
        }
        i = 0;
        for(Producto r: misProductosRetirados){
            if(i == 0){
                resultado +=("Productos Retirados:\n");
                System.out.println("");
            }
            resultado += r.toString() + "\n";
            i++;
        }
    
        return resultado;
    }
    
    public boolean addStock(String codigo,int newStock){
        Producto p = getProductoByCodigo(codigo);
        if(p==null){
            System.out.println("Código no encontrado");
            return false;
        }
        System.out.println("Nuevo Stock cambiado con exito");
        p.changeStock(newStock);
        
        return true;
    }
    
    public Producto getProductoByCodigo(String codigo){
        for(Producto p : misProductos){
            if(p.getCodigoProducto().equalsIgnoreCase(codigo)){
                return p;
            }
        }
        
        // añadido: buscar también en lista inicial
        for(Producto d : listaDeProductosInicial){
            if(d.getCodigoProducto().equalsIgnoreCase(codigo)){
                return d;
            }
        }
        
        for(Producto r : misProductosRetirados){
            if(r.getCodigoProducto().equalsIgnoreCase(codigo)){
                return r;
            }
        }
        
        return null;
    }
    
    public boolean deleteProducto(String codigo){
        Producto p = getProductoByCodigo(codigo);
        if(p==null){
            System.out.println("Código no encontrado");
            return false;
        }
        
        System.out.println("Eliminado correctamente");
        
        misProductosRetirados.add(p);
        misProductos.remove(p);
        listaDeProductosInicial.remove(p);
        
        return true;
    }
    
    public String listarProductos0Stock(){
        String resultado ="";
        int i = 0;
        if(misProductos.isEmpty() && listaDeProductosInicial.isEmpty() && misProductosRetirados.isEmpty()){
            return "No hay Productos registrados";
        }
    
        for(Producto p: misProductos){
            if(p.getStock() == 0){
                if(i == 0){
                    resultado +=("Productos añadidos con 0 Stock:\n");
                }
                resultado += p.toString() + "\n";
                i++;
            }
        }
        i = 0;
        for(Producto d: listaDeProductosInicial){
            if(d.getStock() == 0){
                if(i == 0){
                    resultado +=("Productos Iniciales con 0 Stock:\n");
                }
                resultado += d.toString() + "\n";
                i++;
            }
        }
        i = 0;
        for(Producto r: misProductosRetirados){
            if(r.getStock() == 0){
                if(i == 0){
                    resultado +=("Productos Retirados con 0 Stock:\n");
                }
                resultado += r.toString() + "\n";
                i++;
            }
        }
    
        if(resultado.equals("")){
            System.out.println("Hay 0 Productos con 0 Stock");
        }
    
        return resultado;
    }
    
    public String listarRetirados(){
        String resultado = "";
        if(misProductosRetirados.isEmpty()){
            return "No hay Productos retirados";
        }
        System.out.println("Productos Retirados:");
        System.out.println("");
        for(Producto r: misProductosRetirados){
            resultado += r.toString() + "\n";
        }
        return resultado;
    }
    
    public String mostrarEntrePrecio(double min,double max){
        String resultado = "";
        int i = 0;
        if(misProductos.isEmpty() && listaDeProductosInicial.isEmpty() && misProductosRetirados.isEmpty()){
            return "No hay Productos registrados";
        }
        
        for(Producto p: misProductos){
            if( p.getPrecio() >= min && p.getPrecio() <= max){
                if (i == 0){
                    resultado +=("Productos añadidos entre " + min + "€ y " + max + "€\n");
                }
                resultado += p.toString() + "\n";
                i ++;
            }
        }
        i = 0;
        for(Producto d: listaDeProductosInicial){
            if(d.getPrecio() >= min && d.getPrecio() <= max){
                if ( i == 0){
                    resultado += ("Productos iniciales entre " + min + "€ y " + max + "€\n");
                }
                resultado += d.toString() + "\n";
                i ++;
            }
        }
        i = 0;
        for(Producto r: misProductosRetirados){
            if(r.getPrecio() >= min && r.getPrecio() <= max){
                if ( i == 0) {
                    resultado += ("Productos retirados entre " + min + "€ y " + max + "€\n");
                }
                resultado += r.toString() +"\n";
                i++;
            }
        }
        
        if(resultado == ""){
            System.out.println("Hay 0 Productos con 0 Stock");
        }
        
        return resultado;
    }
    
    
    public String listarCaducados(int dia,int mes,int año){
        String resultado = "";
        int i = 0;
        if(misProductos.isEmpty() && listaDeProductosInicial.isEmpty() && misProductosRetirados.isEmpty()){
            return "No hay Productos registrados";
        }
        
        for(Producto p : misProductos){
            if(p instanceof ProductoPerecedero){
                ProductoPerecedero pere = (ProductoPerecedero) p;
                String fecha = pere.getDateCaducidad();
                String [] partes = fecha.split("-");
                int añoP = Integer.parseInt(partes[0]);
                int mesP = Integer.parseInt(partes[1]);
                int diaP = Integer.parseInt(partes[2]);
                if(añoP < año || añoP == año && mesP < mes || añoP == año && mesP == mes && diaP < dia){
                    if (i == 0){
                        resultado +=("Productos añadidos caducados: \n");
                        i++;
                    }
                    resultado += pere.toString() + "\n";
                }
            }   
        }
        i = 0;
        for(Producto d : listaDeProductosInicial){
            if(d instanceof ProductoPerecedero){
                ProductoPerecedero pere = (ProductoPerecedero) d;
                String fecha = pere.getDateCaducidad();
                String [] partes = fecha.split("-");
                int añoP = Integer.parseInt(partes[0]);
                int mesP = Integer.parseInt(partes[1]);
                int diaP = Integer.parseInt(partes[2]);
                if(añoP < año || añoP == año && mesP < mes || añoP == año && mesP == mes && diaP < dia){
                    if (i == 0){
                        resultado +=("Productos iniciales caducados: \n");
                        i++;
                    }
                    resultado += pere.toString() + "\n";
                }
            }   
        }
        i = 0;
        for(Producto r : misProductosRetirados){
            if(r instanceof ProductoPerecedero){
                ProductoPerecedero pere = (ProductoPerecedero) r;
                String fecha = pere.getDateCaducidad();
                String [] partes = fecha.split("-");
                int añoP = Integer.parseInt(partes[0]);
                int mesP = Integer.parseInt(partes[1]);
                int diaP = Integer.parseInt(partes[2]);
                if(añoP < año || añoP == año && mesP < mes || añoP == año && mesP == mes && diaP < dia){
                    if (i == 0){
                        resultado +=("Productos retirados caducados: \n");
                        i++;
                    }
                    resultado += pere.toString() + "\n";
                }
            }   
        }
        
        if(resultado == ""){
            System.out.println("No hay productos caducados");
        }
        return resultado;
        
    }
    
    public String compararXCodigo(){
        Collections.sort(misProductos, new CompareToXCodigo());
        Collections.sort(listaDeProductosInicial, new CompareToXCodigo());
        Collections.sort(misProductosRetirados, new CompareToXCodigo());
        String resultado ="";
        int i = 0;
        if(misProductos.isEmpty() && listaDeProductosInicial.isEmpty() && misProductosRetirados.isEmpty()){
            return "No hay Productos registrados";
        }
    
        for(Producto p: misProductos){
            if(i == 0){
                resultado +=("Productos Añadidos:\n");
                System.out.println("");
                }
            resultado += p.toString() + "\n";
            i++;
        }
        i = 0;
        for(Producto d: listaDeProductosInicial){
            if(i == 0){
                resultado +=("Productos Iniciales:\n");
                System.out.println("");
            }
            resultado += d.toString() + "\n";
            i++;
        }
        i = 0;
        for(Producto r: misProductosRetirados){
            if(i == 0){
                resultado +=("Productos Retirados:\n");
                System.out.println("");
            }
            resultado += r.toString() + "\n";
            i++;
        }
    
        return resultado;
    }
    
    public String compararXPrecio(){
        Collections.sort(misProductos, new CompareToXPrecio());
        Collections.sort(listaDeProductosInicial, new CompareToXPrecio());
        Collections.sort(misProductosRetirados, new CompareToXPrecio());
        String resultado ="";
        int i = 0;
        if(misProductos.isEmpty() && listaDeProductosInicial.isEmpty() && misProductosRetirados.isEmpty()){
            return "No hay Productos registrados";
        }
    
        for(Producto p: misProductos){
            if(i == 0){
                resultado +=("Productos Añadidos:\n");
                System.out.println("");
                }
            resultado += p.toString() + "\n";
            i++;
        }
        i = 0;
        for(Producto d: listaDeProductosInicial){
            if(i == 0){
                resultado +=("Productos Iniciales:\n");
                System.out.println("");
            }
            resultado += d.toString() + "\n";
            i++;
        }
        i = 0;
        for(Producto r: misProductosRetirados){
            if(i == 0){
                resultado +=("Productos Retirados:\n");
                System.out.println("");
            }
            resultado += r.toString() + "\n";
            i++;
        }
    
        return resultado;
    }
    
    public String compararXStock(){
        Collections.sort(misProductos, new CompareToXStock());
        Collections.sort(listaDeProductosInicial, new CompareToXStock());
        Collections.sort(misProductosRetirados, new CompareToXStock());
        String resultado ="";
        int i = 0;
        if(misProductos.isEmpty() && listaDeProductosInicial.isEmpty() && misProductosRetirados.isEmpty()){
            return "No hay Productos registrados";
        }
    
        for(Producto p: misProductos){
            if(i == 0){
                resultado +=("Productos Añadidos:\n");
                System.out.println("");
                }
            resultado += p.toString() + "\n";
            i++;
        }
        i = 0;
        for(Producto d: listaDeProductosInicial){
            if(i == 0){
                resultado +=("Productos Iniciales:\n");
                System.out.println("");
            }
            resultado += d.toString() + "\n";
            i++;
        }
        i = 0;
        for(Producto r: misProductosRetirados){
            if(i == 0){
                resultado +=("Productos Retirados:\n");
                System.out.println("");
            }
            resultado += r.toString() + "\n";
            i++;
        }
    
        return resultado;
    }
    
    public String compararXCodigoDes(){
        Collections.sort(misProductos, new CompareToXCodigoDes());
        Collections.sort(listaDeProductosInicial, new CompareToXCodigoDes());
        Collections.sort(misProductosRetirados, new CompareToXCodigoDes());
        String resultado ="";
        int i = 0;
        if(misProductos.isEmpty() && listaDeProductosInicial.isEmpty() && misProductosRetirados.isEmpty()){
            return "No hay Productos registrados";
        }
    
        for(Producto p: misProductos){
            if(i == 0){
                resultado +=("Productos Añadidos:\n");
                System.out.println("");
                }
            resultado += p.toString() + "\n";
            i++;
        }
        i = 0;
        for(Producto d: listaDeProductosInicial){
            if(i == 0){
                resultado +=("Productos Iniciales:\n");
                System.out.println("");
            }
            resultado += d.toString() + "\n";
            i++;
        }
        i = 0;
        for(Producto r: misProductosRetirados){
            if(i == 0){
                resultado +=("Productos Retirados:\n");
                System.out.println("");
            }
            resultado += r.toString() + "\n";
            i++;
        }
    
        return resultado;
    }
    
    public String compararXPrecioDes(){
        Collections.sort(misProductos, new CompareToXPrecioDes());
        Collections.sort(listaDeProductosInicial, new CompareToXPrecioDes());
        Collections.sort(misProductosRetirados, new CompareToXPrecioDes());
        String resultado ="";
        int i = 0;
        if(misProductos.isEmpty() && listaDeProductosInicial.isEmpty() && misProductosRetirados.isEmpty()){
            return "No hay Productos registrados";
        }
    
        for(Producto p: misProductos){
            if(i == 0){
                resultado +=("Productos Añadidos:\n");
                System.out.println("");
                }
            resultado += p.toString() + "\n";
            i++;
        }
        i = 0;
        for(Producto d: listaDeProductosInicial){
            if(i == 0){
                resultado +=("Productos Iniciales:\n");
                System.out.println("");
            }
            resultado += d.toString() + "\n";
            i++;
        }
        i = 0;
        for(Producto r: misProductosRetirados){
            if(i == 0){
                resultado +=("Productos Retirados:\n");
                System.out.println("");
            }
            resultado += r.toString() + "\n";
            i++;
        }
    
        return resultado;
    }
    
    public String compararXStockDes(){
        Collections.sort(misProductos, new CompareToXStockDes());
        Collections.sort(listaDeProductosInicial, new CompareToXStockDes());
        Collections.sort(misProductosRetirados, new CompareToXStockDes());
        String resultado ="";
        int i = 0;
        if(misProductos.isEmpty() && listaDeProductosInicial.isEmpty() && misProductosRetirados.isEmpty()){
            return "No hay Productos registrados";
        }
    
        for(Producto p: misProductos){
            if(i == 0){
                resultado +=("Productos Añadidos:\n");
                System.out.println("");
                }
            resultado += p.toString() + "\n";
            i++;
        }
        i = 0;
        for(Producto d: listaDeProductosInicial){
            if(i == 0){
                resultado +=("Productos Iniciales:\n");
                System.out.println("");
            }
            resultado += d.toString() + "\n";
            i++;
        }
        i = 0;
        for(Producto r: misProductosRetirados){
            if(i == 0){
                resultado +=("Productos Retirados:\n");
                System.out.println("");
            }
            resultado += r.toString() + "\n";
            i++;
        }
    
        return resultado;
    }
    
    
    
}
