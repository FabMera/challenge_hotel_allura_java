package com.allura.latam.hotel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuUsuario extends JFrame {
    private JPanel contentPane;
    int xMouse;
    int yMouse;
    private JLabel labelExit;
    private JLabel labelRegistro;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuUsuario frame = new MenuUsuario();
                    frame.setVisible(true);
                } catch (Exception var2) {
                    var2.printStackTrace();
                }

            }
        });
    }

    public MenuUsuario() {

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(MenuUsuario.class.getResource("/imagenes/aH-40px.png")));
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 944, 609);
        this.contentPane = new JPanel();
        this.contentPane.setBackground(Color.WHITE);
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout((LayoutManager) null);
        this.setResizable(false);
        this.setLocationRelativeTo((Component) null);
        this.setUndecorated(true);
        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                MenuUsuario.this.headerMouseDragged(e);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                MenuUsuario.this.headerMousePressed(e);
            }
        });
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(12, 138, 199));
        panelMenu.setBounds(0, 0, 257, 609);
        this.contentPane.add(panelMenu);
        panelMenu.setLayout(null);


        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBounds(50, 58, 150, 150);
        panelMenu.add(lblNewLabel_2);
        lblNewLabel_2.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/aH-150px.png")));

        final JPanel btnRegistro = new JPanel();
        btnRegistro.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnRegistro.setBackground(new Color(118, 187, 223));
            }

            public void mouseExited(MouseEvent e) {
                btnRegistro.setBackground(new Color(12, 138, 199));
            }

            //Al hacer click lo envia a la vista de registro de reservas
            public void mouseClicked(MouseEvent e) {
                ReservasView reservas = new ReservasView();
                reservas.setVisible(true);
                MenuUsuario.this.dispose();
            }
        });
        btnRegistro.setBounds(0, 255, 257, 56);
        btnRegistro.setBackground(new Color(12, 138, 199));
        panelMenu.add(btnRegistro);
        btnRegistro.setLayout(null);

        this.labelRegistro = new JLabel("Registro de reservas");
        this.labelRegistro.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/reservado.png")));
        this.labelRegistro.setForeground(SystemColor.text);
        this.labelRegistro.setBounds(25, 11, 205, 34);
        this.labelRegistro.setFont(new Font("Roboto", 0, 18));
        this.labelRegistro.setHorizontalAlignment(2);
        btnRegistro.add(this.labelRegistro);

        JPanel btnBusqueda = new JPanel();
        btnBusqueda.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnBusqueda.setBackground(new Color(118, 187, 223));
            }

            public void mouseExited(MouseEvent e) {
                btnBusqueda.setBackground(new Color(12, 138, 199));
            }

            //Al hacer click lo envia a la vista de busqueda de reservas y huespedes
            public void mouseClicked(MouseEvent e) {
                Busqueda busqueda = new Busqueda();
                busqueda.setVisible(true);
                dispose();

            }
        });
        btnBusqueda.setBounds(0, 312, 257, 56);
        btnBusqueda.setBackground(new Color(12, 138, 199));
        panelMenu.add(btnBusqueda);
        btnBusqueda.setLayout(null);

        //Metodo configurar Acciones botones Menu Usuario


        JLabel lblBusquedaDeReservas = new JLabel("Búsqueda");
        lblBusquedaDeReservas.setIcon(new ImageIcon(MenuUsuario.class.getResource("/imagenes/pessoas.png")));
        lblBusquedaDeReservas.setBounds(27, 11, 200, 34);
        lblBusquedaDeReservas.setHorizontalAlignment(2);
        lblBusquedaDeReservas.setForeground(Color.WHITE);
        lblBusquedaDeReservas.setFont(new Font("Roboto", 0, 18));
        btnBusqueda.add(lblBusquedaDeReservas);


        JSeparator separator = new JSeparator();
        separator.setBounds(26, 219, 201, 2);
        panelMenu.add(separator);
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 944, 36);
        this.contentPane.add(header);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            public void mouseEntered(MouseEvent e) {
                btnexit.setBackground(Color.RED);
                labelExit.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                btnexit.setBackground(Color.WHITE);
                labelExit.setForeground(Color.BLACK);
            }

        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(891, 0, 53, 36);
        header.add(btnexit);
        this.labelExit = new JLabel("X");
        this.labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(this.labelExit);
        this.labelExit.setHorizontalAlignment(0);
        this.labelExit.setFont(new Font("Roboto", 0, 18));


        JPanel panelFecha = new JPanel();
        panelFecha.setBackground(new Color(118, 187, 223));
        panelFecha.setBounds(256, 84, 688, 121);
        this.contentPane.add(panelFecha);
        panelFecha.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Sistema de reservas Hotel Alura");
        lblNewLabel_1.setBounds(180, 11, 356, 42);
        panelFecha.add(lblNewLabel_1);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Roboto", 0, 24));

        JLabel labelFecha = new JLabel("New label");
        labelFecha.setBounds(35, 64, 294, 36);
        panelFecha.add(labelFecha);
        labelFecha.setForeground(Color.WHITE);
        labelFecha.setFont(new Font("Roboto", 0, 33));
        Date fechaActual = new Date();
        String fecha = (new SimpleDateFormat("dd/MM/yyyy")).format(fechaActual);
        labelFecha.setText("Hoy es " + fecha);


        JLabel lblNewLabel = new JLabel("Bienvenido");
        lblNewLabel.setFont(new Font("Roboto", 1, 24));
        lblNewLabel.setBounds(302, 234, 147, 46);
        this.contentPane.add(lblNewLabel);
        String textoDescripcion = "<html><body>Sistema de reserva de hotel. Controle y administre de forma óptima y fácil <br> el flujo de reservas y de huespédes del hotel   </body></html>";
        JLabel labelDescripcion = new JLabel(textoDescripcion);
        labelDescripcion.setFont(new Font("Roboto", 0, 17));
        labelDescripcion.setBounds(312, 291, 598, 66);
        this.contentPane.add(labelDescripcion);
        String textoDescripcion1 = "<html><body> Esta herramienta le permitirá llevar un control completo y detallado de sus reservas y huéspedes, tendrá acceso a herramientas especiales para tareas específicas como lo son:</body></html>";


        JLabel labelDescripcion_1 = new JLabel(textoDescripcion1);
        labelDescripcion_1.setFont(new Font("Roboto", 0, 17));
        labelDescripcion_1.setBounds(311, 345, 569, 88);
        this.contentPane.add(labelDescripcion_1);

        JLabel lblNewLabel_3 = new JLabel("- Registro de Reservas y Huéspedes");
        lblNewLabel_3.setFont(new Font("Roboto", 0, 17));
        lblNewLabel_3.setBounds(312, 444, 295, 27);
        this.contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_3_1 = new JLabel("- Edición de Reservas y Huéspedes existentes");
        lblNewLabel_3_1.setFont(new Font("Roboto", 0, 17));
        lblNewLabel_3_1.setBounds(312, 482, 355, 27);
        this.contentPane.add(lblNewLabel_3_1);

        JLabel lblNewLabel_3_2 = new JLabel("- Eliminar todo tipo de registros");
        lblNewLabel_3_2.setFont(new Font("Roboto", 0, 17));
        lblNewLabel_3_2.setBounds(312, 520, 295, 27);
        this.contentPane.add(lblNewLabel_3_2);
    }

    private void headerMousePressed(MouseEvent evt) {
        this.xMouse = evt.getX();
        this.yMouse = evt.getY();
    }

    private void headerMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - this.xMouse, y - this.yMouse);
    }

}