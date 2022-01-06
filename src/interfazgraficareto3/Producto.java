
package interfazgraficareto3;

/**
 *
 * @author Oscar Gomez
 */
class Producto {
    /**
     * Representa el codigo de un producto
     */
    private int codigo;
    
    /**
     * Representa el nombre de un producto
     */
    private String nombre;
    
    /**
     * Representa el precio de un producto
     */
    private double precio;
    
    /**
     * Representa la cantidad que hay de un producto
     */
    private int inventario;
    
    /**
     * Constructor vacio
     */
    public Producto() {
    }
    
    /**
     * Crea un producto recibiendo codigo, nombre, precio e inventario
     * @param codigo
     * @param nombre
     * @param precio
     * @param inventario 
     */
    public Producto(int codigo, String nombre, double precio, int inventario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
    }
    
    /**
     * Metodo getCodigo
     * @return devuleve codigo del producto
     */
    public int getCodigo() {
        return codigo;
    }
    
    /**
     * Metodo setCodigo
     * @param codigo 
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    /**
     * Metodo getNombre
     * @return devuelve nombre del producto
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Metodo setNombre
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Metodo getPrecio
     * @return devuelve precio del producto
     */
    public double getPrecio() {
        return precio;
    }
    
    /**
     * Metodo setPrecio
     * @param precio 
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
     * Metodo getInventario
     * @return devuelve la cantidad que existe de un producto
     */
    public int getInventario() {
        return inventario;
    }
    
    /**
     * Metodo setInventario
     * @param inventario 
     */
    public void setInventario(int inventario) {
        this.inventario = inventario;
    }
}
