
package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Oscar Gomez
 */
public class VentanaActualizar extends JDialog {
    private JLabel titulo ,nombre, precio, inventario;
    private JTextField textoNombre, textoPrecio, textoInventario;
    private JPanel panelSur, panelCentro, panelActualizar, panelNorte;  
    private JButton botonActualizar;
    private DefaultTableModel tablaModelo;
    private int codigo;
    private BaseDatosProductos baseDatosProductos;
    private JTable tablaProductos;
    private JOptionPane jop;
  
    /**
     * Constructor con parametros
     * @param bdp
     * @param tablaRecivida
     * @param productoRecibido 
     */
    public VentanaActualizar(BaseDatosProductos bdp,JTable tablaRecivida, Producto productoRecibido){
        diseñarVentana();
        this.baseDatosProductos = bdp;
        this.tablaProductos=tablaRecivida;
        codigo=productoRecibido.getCodigo();
        textoNombre.setText(productoRecibido.getNombre());
        textoPrecio.setText(""+productoRecibido.getPrecio());
        textoInventario.setText("" + productoRecibido.getInventario());
    }
    
    /**
     * Metodo diseñar ventana
     */
    private void diseñarVentana(){
        setTitle("Actualizar Producto");
        
        // JLabel
        titulo= new JLabel("Ingresar los nuevos datos");
        nombre= new JLabel("Nombre   ");
        precio= new JLabel("Precio      ");
        inventario= new JLabel("Inventario");
        
        // JTextfield
        textoNombre= new JTextField();
        textoPrecio= new JTextField();
        textoInventario= new JTextField();
        
        //JOption
        jop= new JOptionPane();
        
        // JJButton
        botonActualizar= new JButton("Actualizar Producto");
        
        // JPanel
        panelSur= new JPanel();
        panelCentro= new JPanel();
        panelNorte= new JPanel();
        panelActualizar= new JPanel();
        
        // ActionListener y Eventos
        ActionListener agregarNuevoProducto= new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNuevoProducto(e);
            }
        
        };
        botonActualizar.addActionListener(agregarNuevoProducto);
        
        // Poner iconos a los botones
        try{
            URL rutaIcono= VentanaActualizar.class.getResource("/iconos/up.png").toURI().toURL();
            ImageIcon iconoActualizar= new ImageIcon(rutaIcono);
            botonActualizar.setIcon(iconoActualizar);
        }
        catch(Exception error){
        }
        
        // Configurar los labels
        Font tipoDeLetra = new Font("Arial", Font.BOLD, 16);
        titulo.setFont(tipoDeLetra);
        nombre.setHorizontalAlignment(JLabel.RIGHT);
        precio.setHorizontalAlignment(JLabel.RIGHT);
        inventario.setHorizontalAlignment(JLabel.RIGHT);
        
        // Configurar los botones
        botonActualizar.setPreferredSize(new Dimension(200,32));
        
        // Configurar los paneles
        panelSur.setPreferredSize(new Dimension(400,55));
        panelNorte.setPreferredSize(new Dimension(400,60));
        panelActualizar.setPreferredSize(new Dimension(300,100));
        
        // Agregar cosas a los paneles
        panelCentro.add(panelActualizar);
        panelActualizar.setLayout(new GridLayout(3,2,60,5));
        panelActualizar.add(nombre);
        panelActualizar.add(textoNombre);
        panelActualizar.add(precio);
        panelActualizar.add(textoPrecio);
        panelActualizar.add(inventario);
        panelActualizar.add(textoInventario);
        panelSur.setLayout(new FlowLayout(FlowLayout.CENTER,70,0));
        panelSur.add(botonActualizar);
        panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER,5,25));
        panelNorte.add(titulo);
        
        // Agregar los paneles a la ventana
        add(panelSur,BorderLayout.SOUTH);
        add(panelNorte,BorderLayout.NORTH);
        add(panelCentro);
        
        // Configuarar ventana
        setSize(400, 300);
    }
    
    /**
     * Metodo para mostrar un mensaje de advertencia
     * @param str 
     */
    private void mostrarAdvertencia(String str) {
        jop.showMessageDialog(this, str, "Advertencia", jop.WARNING_MESSAGE);
    }
    
    /**
     * Metodo para validar lo ingresado en las cajas de texto
     * @param str
     * @param expresion
     * @return 
     */
    private boolean validador(String str, String expresion) {
        Pattern pat= Pattern.compile(expresion);
        Matcher mat= pat.matcher(str);
        return mat.matches();
    }
    
    /**
     * Metodo validar campos de texto
     * @param nombre
     * @param precio
     * @param inventario
     * @return 
     */
    private boolean validarCampos(String nombre, String precio, String inventario) {
        boolean retorno= true;
        
        if (!validador(nombre, "^\\D*$")) {
            mostrarAdvertencia("¡El campo nombre debe ser solo alfabetico!");// "\\d+\\.\\d+"
            retorno= false;
        }
        else if (!validador(precio,"\\d+|\\d+\\.\\d+")) {
            mostrarAdvertencia("¡El campo precio debe ser un numero entero o con decimal!");
            retorno= false;
        }
        else if (!validador(inventario, "[0-9]*")) {
            mostrarAdvertencia("¡El campo inventario debe ser un numero entero!");
            retorno= false;
        }
        
        return retorno;
    }
    
    /**
     * Metodo limpiar campos de texto
     */
    private void limpiarCampos() {
        textoNombre.setText("");
        textoPrecio.setText("");
        textoInventario.setText("");
    }
    
    /**
     * Metodo para refrescar la tabla despues de actualizar un producto
     */
    private void refrescarTabla(){
        String titulo[] = {"Codigo","Nombre", "Precio", "Inventario"};
        tablaModelo = new DefaultTableModel(titulo, 0){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        for(Producto p : this.baseDatosProductos.getListaProductos().values()) {
            tablaModelo.addRow(new Object[]{p.getCodigo(),p.getNombre(), p.getPrecio(), p.getInventario()});
        }
        tablaProductos.setModel(tablaModelo);
        ocultarCodigo();
    }
    
    /**
     * Metodo para ocultar la columna del codigo
     */
    public void ocultarCodigo() {
        tablaProductos.getColumnModel().getColumn(0).setMinWidth(0);
        tablaProductos.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaProductos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        tablaProductos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
    }
    
    /**
     * Metodo boton actualizar producto
     */
    private void eventoActualizar(){
        if (textoNombre.getText().isEmpty() || textoPrecio.getText().isEmpty() || textoInventario.getText().isEmpty()) {
            mostrarAdvertencia("¡Todos los campos son obligatorios!");
        } 
        else{ 
            if (validarCampos(textoNombre.getText(), textoPrecio.getText(), textoInventario.getText())){
                Producto productoActualizado= new Producto(codigo, textoNombre.getText(), 
                         Double.parseDouble(textoPrecio.getText()), Integer.parseInt(textoInventario.getText()));
                   
                baseDatosProductos.actualizarDeInterfaz(productoActualizado);
                Icon icono = new ImageIcon(getClass().getResource("/iconos/check-1-icon.png")); 
                JOptionPane.showMessageDialog(null, "¡El producto se actualizo correctamente!","Informacion",JOptionPane.PLAIN_MESSAGE, icono);
                setVisible(false);
                refrescarTabla();
            }
        }
        limpiarCampos();
    }
    
    /**
     * ActionEvent boton actualizar
     * @param e 
     */
    private void agregarNuevoProducto(ActionEvent e){
        eventoActualizar();
    }
}
