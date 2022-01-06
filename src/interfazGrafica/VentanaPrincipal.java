package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oscar Gomez
 */
public class VentanaPrincipal extends JFrame {

    private JLabel titulo, nombre, precio, inventario;
    private JTextField textoNombre, textoPrecio, textoInventario;
    private JButton botonAgregar, botonBorrar, botonActualizar, botonInforme;
    private JTable tablaProductos;
    private DefaultTableModel tablaModelo;
    private JScrollPane scrollPane;
    private JPanel panelSur, panelNorte, panelCentro, panelAgregar, panelBoton;
    private BaseDatosProductos baseDatos;
    private LineBorder bordeLinea;
    private TitledBorder bordeTitulo;
    private JOptionPane jop;

    /**
     * Constructor VentanaPrincipal
     */
    public VentanaPrincipal() {
        baseDatos = new BaseDatosProductos();
        iniciarGUI();
    }

    /**
     * Metodo diseño interfaz grafica
     */
    private void iniciarGUI() {

        //setTitle("RETO3 INVENTARIO");
        // Borde de linea y titulo
        bordeLinea = new LineBorder(Color.GRAY, 2);
        LineBorder bordeLinea1 = new LineBorder(Color.DARK_GRAY, 1);
        bordeTitulo = BorderFactory.createTitledBorder(bordeLinea, "Agregar Productos");

        // Jlabel
        titulo = new JLabel("¡Bienvenido a la APP de inventario!");
        nombre = new JLabel("Nombre   ");
        precio = new JLabel("Precio      ");
        inventario = new JLabel("Inventario");

        // JTextField
        textoNombre = new JTextField("");
        textoPrecio = new JTextField("");
        textoInventario = new JTextField("");

        // JButton
        botonAgregar = new JButton("Agregar");
        botonBorrar = new JButton("Borrar");
        botonActualizar = new JButton("Actualizar");
        botonInforme = new JButton("Informe");

        // JTable
        tablaProductos = new JTable();

        // DefaultTableModel
        tablaModelo = new DefaultTableModel();

        // JScrollPane
        scrollPane = new JScrollPane();

        // configurar la tabla
        getContentPane().add(scrollPane);
        scrollPane.setBounds(50, 280, 380, 240);
        actualizarTabla();

        //JOption
        jop = new JOptionPane();

        // JPanel
        panelSur = new JPanel();
        panelNorte = new JPanel();
        panelCentro = new JPanel();
        panelAgregar = new JPanel();
        panelBoton = new JPanel();

        // Configurar los paneles
        panelSur.setPreferredSize(new Dimension(500, 60));
        panelNorte.setPreferredSize(new Dimension(500, 70));
        panelAgregar.setPreferredSize(new Dimension(320, 150));
        panelBoton.setPreferredSize(new Dimension(320, 45));

        // Configurar los botones
        botonAgregar.setPreferredSize(new Dimension(120, 35));
        botonBorrar.setPreferredSize(new Dimension(110, 32));
        botonInforme.setPreferredSize(new Dimension(114, 32));
        botonActualizar.setPreferredSize(new Dimension(128, 32));

        // Poner borde de linea a los botones y tabla
        botonBorrar.setBorder(bordeLinea1);
        botonActualizar.setBorder(bordeLinea1);
        botonInforme.setBorder(bordeLinea1);
        botonAgregar.setBorder(bordeLinea1);
        scrollPane.setBorder(bordeLinea1);

        // Configurar los labels
        Font tipoDeLetra = new Font("Ink Free", Font.BOLD, 26);
        titulo.setFont(tipoDeLetra);
        nombre.setHorizontalAlignment(JLabel.RIGHT);
        precio.setHorizontalAlignment(JLabel.RIGHT);
        inventario.setHorizontalAlignment(JLabel.RIGHT);

        // Agregar cosas a los paneles
        panelAgregar.setBorder(bordeTitulo);
        panelCentro.add(panelAgregar);
        panelCentro.add(panelBoton);
        panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
        panelNorte.add(titulo);
        panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 1));
        panelSur.add(botonBorrar);
        panelSur.add(botonActualizar);
        panelSur.add(botonInforme);
        panelBoton.add(botonAgregar);
        panelAgregar.setLayout(new GridLayout(3, 2, 40, 5));
        panelAgregar.add(nombre);
        panelAgregar.add(textoNombre);
        panelAgregar.add(precio);
        panelAgregar.add(textoPrecio);
        panelAgregar.add(inventario);
        panelAgregar.add(textoInventario);

        // Poner iconos a los botones
        try {
            URL rutaIcono = VentanaPrincipal.class.getResource("/iconos/borrar.png").toURI().toURL();
            ImageIcon iconoBorrar = new ImageIcon(rutaIcono);
            botonBorrar.setIcon(iconoBorrar);
            URL rutaIcono1 = VentanaPrincipal.class.getResource("/iconos/report-icon.png").toURI().toURL();
            ImageIcon iconoInforme = new ImageIcon(rutaIcono1);
            botonInforme.setIcon(iconoInforme);
            URL rutaIcono2 = VentanaPrincipal.class.getResource("/iconos/add1.png").toURI().toURL();
            ImageIcon iconoAdd = new ImageIcon(rutaIcono2);
            botonAgregar.setIcon(iconoAdd);
            URL rutaIcono3 = VentanaPrincipal.class.getResource("/iconos/up.png").toURI().toURL();
            ImageIcon iconoUpdate = new ImageIcon(rutaIcono3);
            botonActualizar.setIcon(iconoUpdate);
        } catch (Exception error) {
        }

        // ActionListener y Eventos
        ActionListener abrirVentanaActualizar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaActualizar(e);
            }

        };
        botonActualizar.addActionListener(abrirVentanaActualizar);

        ActionListener agregarNuevoProducto = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNuevoProducto(e);
            }

        };
        botonAgregar.addActionListener(agregarNuevoProducto);

        ActionListener informeProductos = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informeProductos(e);
            }

        };
        botonInforme.addActionListener(informeProductos);

        ActionListener borrar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrar(e);
            }

        };
        botonBorrar.addActionListener(borrar);

        // Agregar paneles a la ventana
        add(panelSur, BorderLayout.SOUTH);
        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro);

        // Dimension Ventana
        setSize(500, 670);
    }

    /**
     * Metodo para cargar datos a la tabla
     */
    private void actualizarTabla() {
        String titulo[] = {"Codigo", "Nombre", "Precio", "Inventario"};
        tablaModelo = new DefaultTableModel(titulo, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        for (Producto p : baseDatos.getListaProductos().values()) {
            tablaModelo.addRow(new Object[]{p.getCodigo(), p.getNombre(), p.getPrecio(), p.getInventario()});
        }
        tablaProductos.setModel(tablaModelo);
        tablaProductos.setPreferredSize(new Dimension(350, tablaModelo.getRowCount() * 16));
        scrollPane.setViewportView(tablaProductos);
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
     * Metodo para mostrar un mensaje de advertencia
     * @param str
     */
    private void mostrarAdvertencia(String str) {
        jop.showMessageDialog(this, str, "Advertencia", jop.WARNING_MESSAGE);
    }

    /**
     * Metodo para mostrar un mensaje de informacion
     * @param str
     */
    private void mostrarInfo(String str) {
        Icon icono = new ImageIcon(getClass().getResource("/iconos/check-1-icon.png"));
        jop.showMessageDialog(null, str, "Informacion", JOptionPane.PLAIN_MESSAGE, icono);
    }

    /**
     * Metodo para limpiar las cajas de texto
     */
    private void limpiarCampos() {
        textoNombre.setText("");
        textoPrecio.setText("");
        textoInventario.setText("");
    }

    /**
     * Metodo para validar lo ingresado en las cajas de texto
     * @param str
     * @param expresion
     * @return
     */
    private boolean validador(String str, String expresion) {
        Pattern pat = Pattern.compile(expresion);
        Matcher mat = pat.matcher(str);
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
        boolean retorno = true;

        if (!validador(nombre, "^\\D*$")) {
            mostrarAdvertencia("¡El campo nombre debe ser solo alfabetico!");
            textoNombre.grabFocus();
            retorno = false;
        } else if (!validador(precio, "\\d+|\\d+\\.\\d+")) {
            mostrarAdvertencia("¡El campo precio debe ser un numero entero o con decimal!");
            textoPrecio.grabFocus();
            retorno = false;
        } else if (!validador(inventario, "[0-9]*")) {
            mostrarAdvertencia("¡El campo inventario debe ser un numero entero!");
            textoInventario.grabFocus();
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo para agregar un producto
     */
    private void eventoAgregar() {
        String nombre = textoNombre.getText();
        String precio = textoPrecio.getText();
        String inventario = textoInventario.getText();
        if (nombre.isEmpty() || precio.isEmpty() || inventario.isEmpty()) {
            mostrarAdvertencia("¡Todos los campos son obligatorios!");
            textoNombre.grabFocus();
        } else {
            if (validarCampos(nombre, precio, inventario)) {

                if (baseDatos.validarExistencia(nombre)) {
                    mostrarAdvertencia("¡El Producto ya existe!");
                    textoNombre.grabFocus();
                } else {
                    int llaveMayor = Collections.max(baseDatos.getListaProductos().keySet()) + 1;

                    Producto producto = new Producto(llaveMayor, nombre, Double.parseDouble(precio),
                            Integer.parseInt(inventario));
                    baseDatos.agregarDeInterfaz(producto);
                    mostrarInfo("¡El Producto se agrego correctamente!");
                    limpiarCampos();
                    textoNombre.grabFocus();
                    actualizarTabla();   
                }
            }
        }
    }

    /**
     * Metodo borrar un producto
     */
    private void botonBorrar() {
        String valoresTabla;
        int id;
        int filaTabla = tablaProductos.getSelectedRow();
        if (filaTabla == -1) {
            mostrarAdvertencia("¡Seleccione en la tabla el producto que desea eliminar!");
        } else {
            valoresTabla = tablaProductos.getValueAt(filaTabla, 0).toString();
            id = Integer.parseInt(valoresTabla);
            baseDatos.borrarInterfaz(id);
            mostrarInfo("¡El producto se elimino correctamente!");
            actualizarTabla();
        }
    }

    /**
     * Metodo abrir ventana actualizar
     */
    private void botonActualizar() {
        String codigoTabla;
        int id;
        int filaTabla = tablaProductos.getSelectedRow();
        if (filaTabla == -1) {
            mostrarAdvertencia("¡Seleccione en la tabla el producto que desea actualizar!");
        } else {
            codigoTabla = tablaProductos.getValueAt(filaTabla, 0).toString();
            id = Integer.parseInt(codigoTabla);
            Producto p = baseDatos.obtenerProducto(id);
            VentanaActualizar ventanaActualizar = new VentanaActualizar(baseDatos, tablaProductos, p);
            ventanaActualizar.setLocationRelativeTo(this);
            ventanaActualizar.setVisible(true);
        }
    }

    /**
     * ActionEvent boton actualizar
     * @param e
     */
    private void abrirVentanaActualizar(ActionEvent e) {
        botonActualizar();
    }

    /**
     * ActionEvent boton agregar
     * @param e
     */
    private void agregarNuevoProducto(ActionEvent e) {
        eventoAgregar();
    }

    /**
     * ActionEvent boton informe
     * @param e
     */
    private void informeProductos(ActionEvent e) {
        //mostrarInfo(baseDatos.generarInformeGUI());
        String mensaje= baseDatos.generarInformeGUI();
        //Icon icono = new ImageIcon(getClass().getResource("/icono/check-1-icon.png"));
        jop.showMessageDialog(null, mensaje, "Productos con mayor precio",JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * ActionEvent boton borrar
     * @param e
     */
    private void borrar(ActionEvent e) {
        botonBorrar();
    }
}
