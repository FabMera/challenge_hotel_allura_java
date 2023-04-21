package com.allura.latam.hotel;

import com.allura.latam.hotel.controlador.HuespedController;
import com.allura.latam.hotel.controlador.ReservasController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.Optional;

public class Busqueda extends JFrame {
    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modelo; //tabla de reservas
    private DefaultTableModel modeloHuesped; //tabla de huespedes
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;

    private final ReservasController reservasController;
    private final HuespedController huespedesController;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Busqueda frame = new Busqueda();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Busqueda() {
        //inicializar controladores
        reservasController = new ReservasController();
        huespedesController = new HuespedController();
        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);



        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);


        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);


        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
        modeloHuesped.addColumn("Número de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");
        //cargarTablaHuespedes();
        //cargarTablaReservas();
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cargarTablaResultadosPorBusquedaReservas();
            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                modificarUnaReserva();
            }
        });

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminarUnaReserva();
            }
        });

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);
    }

    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    //metodos para cargar los datos de la tabla huespedes y CRUD
    private void cargarTablaHuespedes() {
        try {
            var huesped = this.huespedesController.listarHuespedes();

            try {
                huesped.forEach(h ->
                        modeloHuesped.addRow(new Object[]{
                                h.get("id"),
                                h.get("nombre"),
                                h.get("apellido"),
                                h.get("fecha_nacimiento"),
                                h.get("nacionalidad"),
                                h.get("telefono"),
                                h.get("id_reserva")
                        }));

            } catch (Exception e) {
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Metodos boolean para saber si hay una fila seleccionada en tbhuesped o tbreservas
    private boolean tieneFilaSeleccionadaHuesped() {
        return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
    }
    private boolean tieneFilaSeleccionadaReservas() {
        return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
    }

    //Metodo para modiiicar un huesped de la tabla
    private void modificarUnHuesped() {
        if (tieneFilaSeleccionadaHuesped()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un huesped");
            return;
        }
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn())).ifPresentOrElse(fila -> {
            Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
            String nombre = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
            String apellido = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
            String telefono = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString();
            String fecha_nacimiento = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
            String nacionalidad = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();

            int cantidaModificada;

            try {
                cantidaModificada = this.huespedesController.modificarHuesped(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            JOptionPane.showMessageDialog(this, "Se modificaron " + cantidaModificada + " huespedes");
        }, () -> JOptionPane.showMessageDialog(this, "Debe seleccionar un huesped"));

    }


    //metodos para cargar los datos de la tabla reservas y CRUD
    private void cargarTablaReservas() {

        try {
            var reserva = this.reservasController.listarReservas();

            try {
                reserva.forEach(r ->
                        modelo.addRow(new Object[]{
                                r.get("id"),
                                r.get("fecha_entrada"),
                                r.get("fecha_salida"),
                                r.get("valor"),
                                r.get("forma_pago"),
                        }));

            } catch (Exception e) {
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //METODO para cargar datos y mostrarlos en la tabla de reservas por busqueda
    private void cargarTablaResultadosPorBusquedaReservas() {
        int id = Integer.parseInt(txtBuscar.getText());
        try {
            var resultReservas = this.reservasController.listarReservasPorId(id);
            try {

                resultReservas.forEach(product -> modelo.addRow(new Object[]{
                        product.get("id"),
                        product.get("fecha_entrada"),
                        product.get("fecha_salida"),
                        product.get("valor"),
                        product.get("forma_pago"),
                }));
            } catch (Exception e) {
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    //Metodo para eliminar una reserva
    private void eliminarUnaReserva() {
        if (tieneFilaSeleccionadaReservas()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una reserva");
            return;
        }
        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn())).ifPresentOrElse(fila -> {
            Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
            int cantidadEliminada;
            try {
                cantidadEliminada = this.reservasController.eliminarReserva(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            modelo.removeRow(tbReservas.getSelectedRow());
            JOptionPane.showMessageDialog(this, "Se eliminaron " + cantidadEliminada + " reservas");
        }, () -> JOptionPane.showMessageDialog(this, "Debe seleccionar una reserva"));
    }



    //Metodo para modificar una reserva
    private void modificarUnaReserva() {
        if (tieneFilaSeleccionadaReservas()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una reserva");
            return;
        }
        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn())).ifPresentOrElse(fila -> {
            Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());

            String fecha_entrada = modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString();
            String fecha_salida = modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString();
            String valor = modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString();
            String forma_pago = modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString();

            int cantidaModificada;

            try {
                cantidaModificada = this.reservasController.modificarReserva(fecha_entrada, fecha_salida, valor, forma_pago, id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            JOptionPane.showMessageDialog(this, "Se modificaron " + cantidaModificada + " reservas");
        }, () -> JOptionPane.showMessageDialog(this, "Debe seleccionar una reserva"));

    }

}
