
package interfazGrafica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Oscar Gomez
 */
public class BaseDatosProductos{
    /**
     * HashMap listaProductos
     */
    private Map<Integer,Producto> listaProductos= new HashMap<>();
    
    /**
     * Constructor
     */
    public BaseDatosProductos(){
        this.listaProductos.put(1,new Producto(1,"Mango",7000.0,99));
        this.listaProductos.put(2,new Producto(2,"Limones",3600.0,95));
        this.listaProductos.put(3,new Producto(3,"Peras",2700.0,85));
        this.listaProductos.put(4,new Producto(4,"Arandanos",8300.0,74));
        this.listaProductos.put(5,new Producto(5,"Tomates",8400.0,44));
        this.listaProductos.put(6,new Producto(6,"Fresas",7100.0,99));
        this.listaProductos.put(7,new Producto(7,"Helado",4400.0,98));
        this.listaProductos.put(8,new Producto(8,"Galletas",400.0,99));
        this.listaProductos.put(9,new Producto(9,"Chocolates",4500.0,90));
        this.listaProductos.put(10,new Producto(10,"Jamon",17000.0,89));
    }
    
    /**
     * Metodo getter del hashmap
     * @return listaProductos
     */
    public Map<Integer, Producto> getListaProductos() {
        return listaProductos;
    }

    
    /**
     * Metodo para verificar si una key existe
     * @param producto
     * @return un booleano
     */
    public boolean verificarExistencia (Producto producto) {
	return this.listaProductos.containsKey(producto.getCodigo());
    }
    
    /**
     * Metodo para verificar si un producto existe recibiendo como parametro el nombre del producto
     * @param nombre
     * @return un booleano
     */
    public boolean validarExistencia(String nombre) {

        boolean valido = false;
        for (Producto p : this.listaProductos.values()) {
            if (p.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
                valido = true;
            }
        }
        return valido;
    }
     
    /**
     * Metodo para obtener un producto 
     * @param codigo
     * @return un objeto de la clase producto
     */
    public Producto obtenerProducto(int codigo) {
        return this.listaProductos.get(codigo);
    }
    
    /**
     * Metodo para agregar un producto
     * @param producto 
     */
    public void agregar (Producto producto) {
	if (verificarExistencia(producto)) {
            System.out.print("ERROR");
	}else {
            
            this.listaProductos.put(producto.getCodigo(),producto);
            generarInforme();
        }
    }
    /**
     * Metodo para agregar un producto desde la interfaz
     * @param producto 
     */
    public void agregarDeInterfaz(Producto producto){
        this.listaProductos.put(producto.getCodigo(),producto);
    }
    
    
    /**
     * Metodo para borrar un producto
     * @param p 
     */
    public void borrar(Producto p){
        if(verificarExistencia(p)){
            listaProductos.remove(p.getCodigo());
            generarInforme();
        }
        else{
            System.out.print("ERROR");
        }
        
    }
    
    
    /**
     * Metodo para actualizar un producto
     * @param producto 
     */
    public void actualizar (Producto producto){
        if (verificarExistencia(producto)) {
            this.listaProductos.replace(producto.getCodigo(),producto);
            generarInforme();
	}else {
            System.out.print("ERROR");
        }
        
    }
    
    /**
     * Metodo para actualizar un producto desde la interfaz grafica
     */
    public void actualizarDeInterfaz(Producto producto){
        this.listaProductos.replace(producto.getCodigo(),producto);
    }
    
    /**
     * Metodo para encontrar los productos con mayor precio
     * @return una lista con los productos con mayor precio
     */
    private List<Producto> extraerMayores() {
        List<Producto> lista = new ArrayList<>(this.listaProductos.values());
        List<Producto> listaMayores = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Producto p=new Producto();
            for (Producto pTem : lista) {
                if (pTem.getPrecio() > p.getPrecio()) {
                    p=pTem;
                }
            }
            listaMayores.add(p);
            lista.remove(p);
        }
        return listaMayores;
    }
    
    /**
     * Permite mostrar un informe
     */
    public void generarInforme() {
        List<Producto> listaMayores = extraerMayores();
        System.out.print(listaMayores.get(0).getNombre() + " " + listaMayores.get(1).getNombre() + " " + listaMayores.get(2).getNombre());
    }
    
    /**
     * Metodo para generar un informe para la interfaz grafica
     * @return nombres de los productos mayores
     */
    public String generarInformeGUI() {
        List<Producto> listaMayores = extraerMayores();
        return listaMayores.get(0).getNombre()+ ",  " + listaMayores.get(1).getNombre()+ ",  " + listaMayores.get(2).getNombre();
    }
    
    
    /**
     * Metodo para borrar desde la interfaz grafica
     * @param id 
     */
    public void borrarInterfaz(int id){
        this.listaProductos.remove(id);
    }
    
}
    

