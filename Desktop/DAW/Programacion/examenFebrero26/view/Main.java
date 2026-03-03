package view;
import  controller.*;
import java.util.Scanner;


/**
 * Descripción del examen:
 * 
 * Hay que terminar un prototipo para gestión del stock de productos de un almacén
 * El proyecto tiene una serie de clases incompletas añadidas, y tendrás que crear también 
 * una clase ProductoPerecedero que será un producto que tendrá fecha de caducidad (AAAAMMDD)
 * Tu objetivo: implementar el prototipo hasta hacer funcionales todas las opciones del menú, 
 * teniendo en cuenta que:
 *  - El controlador tendrá una colección List de productos, donde estarán todos los productos
 *  activos
 *  - El controlador tendrá otra colección List de productos con los productos retirados (por 
 *  obsoletos, caducados, o lo que sea)
 *  - Cuando añadimos un producto puede ser normal o perecedero
 *  - Cuando modificamos el stock de un producto, se solicita el código del producto y el stock
 *  - Cuando queremos retirar un producto, pedimos su código
 *  - Cuando queremos mostrar productos entre dos precios pedimos el mínimo y el máximo precio
 *  que usaremos para obtener de la lista de productos activos, los que cumplan con el filtro
 *  - En las demás opciones realizamos lo solicitado sin necesidad de obtener ningún dato por 
 *  teclado
 *  - El controlador es de tipo Singleton
 *  - La vista NO usa objetos ni clases del modelo. El proyecto es MVC con capas puras
 *  - Hay que implementar los listados como tablas, con una cabecera y los datos en forma de filas
 *  pero sin tener la información de los atributos. Ejemplo:
 *  Clase,Código Producto,Descripción,Precio,Stock,Caducidad
    Producto,TECL5678X,Teclado mecánico RGB con switches rojos,89.99,45
    Producto,RATN9012K,Ratón inalámbrico ergonómico con 5 botones,34.50,67
    Producto,AURC3456L,Auriculares inalámbricos con cancelación de ruido,129.99,23
    Producto,WEBC7890P,Webcam Full HD 1080p con micrófono integrado,59.90,32
    Producto,HUBB2345M,Hub USB 3.0 de 4 puertos con alimentación,24.75,56
    Producto,INK55665F,Toner b/w genérico HP 8750,79.99,18,20260713
    
    - En el ejemplo anterior, la última línea es una producto perecedero
    
 *  - Podéis reutilizar código del Taller mecánico, el que queráis
 *  - El examen dura unas 2 horas y media (se cerrará la entrega a las 10:55)
 *  
 *  - Antes de esa hora, hay que entregar el proyecto en ZIP en la classroom
 *  - Después, se puede seguir trabajando en el proyecto, que completo, se defenderá
 *  a la vuelta de semana blanca.
 *  
 *  Evaluación: 
 *  1ª Oportunidad: ZIP entregado en la classroom
 *  2ª Oportunidad: Repositorio depués de semana blanca
 *  
 *  Cada opción de menú: 1.25 puntos. Total: 10p. Aprobar: 5p.
 *  
 */
public class Main
{
    

    // Clase principal de la vista

    public static void main(String[] args) {
        //Poner aquí el bucle para ejecutar mostrar menú, escoger opción y ejecutarla la opción
        //Hasta pulsar opción 0 que es salir
        Controlador controlador = Controlador.getSingleton();
        boolean continuar = true;
        
        do{
            mostrarMenu();
            int opcion = getOpcionMenu();
            if(opcion!=0){
                realizarOpcion(opcion,controlador);
            }else 
                continuar = false;
        } while(continuar);
    }
    
    public static int getOpcionMenu(){
        return leerEntero("Opcion:");
    }
    
    public static int leerEntero(String info){
        System.out.print(info);
        return (new Scanner(System.in)).nextInt();
    }
    
    public static void mostrarMenu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("-                                                      -");
        System.out.println("- Menú del proyecto: Almacén de productos              -");
        System.out.println("-                                                      -");
        System.out.println("- 0. Salir                                             -");
        System.out.println("- 1. Añadir nuevo producto                             -");
        System.out.println("- 2. Añadir/quitar stock a un producto                 -");
        System.out.println("- 3. Listar todo (productos ordenados por descripción) -");
        System.out.println("- 4. Retirar un producto y su stock                    -");
        System.out.println("- 5. Mostrar productos con stock 0                     -");
        System.out.println("- 6. Mostrar productos caducados                       -");
        System.out.println("- 7. Mostrar productos entre dos precios               -");
        System.out.println("- 8. Mostrar productos retirados                       -");
        System.out.println("-                                                      -");
        System.out.println("-                                                      -");
        System.out.println("--------------------------------------------------------");
    }
    
    
    public static void realizarOpcion(int opcion,Controlador controlador){
        switch(opcion){
            case 1: addProducto(controlador);
            break;
            case 2: addQuitarStock(controlador);
            break;
            case 3: listarProducto(controlador);
            break;
            case 4: retirarProducto(controlador);
            break;
            case 5: listarStock0(controlador);
            break;
            case 6: listarCaducados(controlador);
            break;
            case 7: mostarEntrePrecio(controlador);
            break;
            case 8: listarRetirados(controlador);
            break;
            
        }
    }
    
    public static void addProducto(Controlador controlador){
        Scanner sc = new Scanner(System.in);
        String datos =   "";
        String respuesta;
        System.out.println("¿Tu producto es perecedero?(si/no)");
        respuesta = sc.nextLine();
        if(respuesta.equalsIgnoreCase("no")){
            System.out.println("Dime los datos del producto que quieres añadir \n");
            datos += leerDatoProducto("el código del producto:",sc) + leerDatoProducto("La descripción del producto:",sc) + leerDatoProducto("el precio del producto:",sc) + leerDatoProducto("el Stock del producto:",sc);
            
        }
        if(respuesta.equalsIgnoreCase("si")){
            System.out.println("Dime los datos del producto que quieres añadir \n");
            datos += leerDatoProducto("el código del producto:",sc) + leerDatoProducto("La descripción del producto:",sc) + leerDatoProducto("el precio del producto:",sc) + leerDatoProducto("el Stock del producto:",sc) + añadirFecha();;
        }
        controlador.addProducto(datos);
    }
    
    public static String leerDatoProducto(String palabra,Scanner sc){
        
        String dato;
        System.out .println("Dime " + palabra);
        dato = sc.nextLine();
        dato += ";";
        return dato;
    }
    
    public static void addQuitarStock(Controlador controlador){
        Scanner sc = new Scanner(System.in);
        String codProducto;
        int newStock = 0;
        System.out.println("Dime el codigo del Producto que quieras modificar");
        codProducto = sc.nextLine();
        System.out.println("Dime el nuevo stock del Producto que quieras modificar");
        newStock = sc.nextInt();
        controlador.addStock(codProducto,newStock);
    }
    
    public static void listarProducto(Controlador controlador){
        System.out.println(controlador.listarProductos());
    }
    
    public static void retirarProducto(Controlador controlador){
        Scanner sc = new Scanner(System.in);
        String codProducto;
        System.out.println("Dime el codigo del Producto que quieras modificar");
        codProducto = sc.nextLine();
        boolean borrado = controlador.deleteProducto(codProducto);
        if(borrado == false){ System.out.println("No se ha podido retirar Producto");}
        if(borrado != false){ System.out.println("Se ha podido retirar Producto");}
        
    }
    
    public static void listarStock0(Controlador controlador){
        System.out.println(controlador.listarProductos0Stock());
    }
    
    public static void listarCaducados(Controlador controlador){
        Scanner sc = new Scanner(System.in);
        boolean dateTrue = false;
        int dia;
        int mes;
        int año;
        do{
            System.out.println("Dime la fecha para comprobar si esta caducado (En Formato YYYY-MM-DD), si dia es 0 se saldra del programa");
            String date = sc.nextLine();
            String [] fecha = date.split("-");
            año = Integer.parseInt(fecha[0]);
            mes = Integer.parseInt(fecha[1]);
            dia = Integer.parseInt(fecha[2]);
            if(mes > 0 && mes < 13){
                switch(mes){
                    case 1,3,5,7,8,10,12 :
                        if (dia > 0 && dia <= 31){
                            System.out.println("Fecha correcta");
                            dateTrue = true;
                        }
                    break;
                    case 2:
                        if (dia > 0 && dia <= 28){
                            System.out.println("Fecha correcta");
                            dateTrue = true;
                        }
                    break;
                    case 4,6,9,11:
                        if (dia > 0 && dia <= 30){
                            System.out.println("Fecha correcta");
                            dateTrue = true;
                        }
                    break;
                }
                if(dia == 0){
                    dateTrue = true;
                }
            }
        }while(!dateTrue);
        
        if(dia == 0){
             System.out.println("Saliendo de la función");
             return;
        }
        System.out.println(controlador.listarCaducados(dia,mes,año));
    }
    
    public static void mostarEntrePrecio(Controlador controlador){
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime el precio Mínimo de los productos que quieres buscar");
        double min = sc.nextDouble();
        System.out.println("Dime el precio Máximo de los productos que quieres buscar");
        double max = sc.nextDouble();
        System.out.println(controlador.mostrarEntrePrecio(min,max));
    }
    
    public static void listarRetirados(Controlador controlador){
        System.out.println(controlador.listarRetirados());
    }
    
    public static String añadirFecha(){
        Scanner sc = new Scanner(System.in);
        boolean dateTrue = false;
        int dia;
        int mes;
        int año;
        String date = "";
        do{
            System.out.println("Dime la fecha del producto (En Formato YYYY-MM-DD), si dia es 0 se saldra del programa");
            date = sc.nextLine();
            String [] fecha = date.split("-");
            año = Integer.parseInt(fecha[0]);
            mes = Integer.parseInt(fecha[1]);
            dia = Integer.parseInt(fecha[2]);
            if(mes > 0 && mes < 13){
                switch(mes){
                    case 1,3,5,7,8,10,12 :
                        if (dia > 0 && dia <= 31){
                            System.out.println("Fecha correcta");
                            dateTrue = true;
                        }
                    break;
                    case 2:
                        if (dia > 0 && dia <= 28){
                            System.out.println("Fecha correcta");
                            dateTrue = true;
                        }
                    break;
                    case 4,6,9,11:
                        if (dia > 0 && dia <= 30){
                            System.out.println("Fecha correcta");
                            dateTrue = true;
                        }
                    break;
                }
                if(dia == 0){
                    return null;
                }
            }
        }while(!dateTrue);
        return date + ";";
    } 
    
}