package com.allura.latam.hotel;


import com.allura.latam.hotel.conexion.CreaConexion;
import com.allura.latam.hotel.controlador.HuespedController;
import com.mysql.cj.xdevapi.SqlSingleResult;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Objects;

public class RegistroHuesped extends JFrame {
    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtNreserva;
    private JDateChooser txtFechaN;
    private JComboBox<Format> txtNacionalidad;
    private JLabel labelExit;
    private JLabel labelAtras;

    private final HuespedController huespedController;
    int xMouse;
    int yMouse;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                RegistroHuesped frame = new RegistroHuesped();
                frame.setVisible(true);
            } catch (Exception var2) {
                var2.printStackTrace();
            }

        });
    }

    public RegistroHuesped() {
        this.huespedController = new HuespedController();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/imagenes/lOGO-50PX.png")));
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 910, 634);

        this.contentPane = new JPanel();
        this.contentPane.setBackground(SystemColor.text);
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.contentPane.setLayout(null);

        JPanel header = new JPanel();
        header.setBounds(0, 0, 910, 36);
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                RegistroHuesped.this.headerMouseDragged(e);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                RegistroHuesped.this.headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(SystemColor.text);
        header.setOpaque(false);
        header.setBounds(0, 0, 910, 36);
        this.contentPane.add(header);


        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ReservasView reservas = new ReservasView();
                reservas.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.WHITE);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(new Color(12, 138, 199));
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        this.labelAtras = new JLabel("<");
        this.labelAtras.setHorizontalAlignment(0);
        this.labelAtras.setForeground(Color.WHITE);
        this.labelAtras.setFont(new Font("Roboto", 0, 23));
        this.labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(this.labelAtras);

        this.txtNombre = new JTextField();
        this.txtNombre.setFont(new Font("Roboto", 0, 16));
        this.txtNombre.setBounds(560, 135, 285, 33);
        this.txtNombre.setBackground(Color.WHITE);
        this.txtNombre.setColumns(10);
        this.txtNombre.setBorder(BorderFactory.createEmptyBorder());
        this.contentPane.add(this.txtNombre);

        this.txtApellido = new JTextField();
        this.txtApellido.setFont(new Font("Roboto", 0, 16));
        this.txtApellido.setBounds(560, 204, 285, 33);
        this.txtApellido.setColumns(10);
        this.txtApellido.setBackground(Color.WHITE);
        this.txtApellido.setBorder(BorderFactory.createEmptyBorder());
        this.contentPane.add(this.txtApellido);

        this.txtFechaN = new JDateChooser();
        this.txtFechaN.setBounds(560, 278, 285, 36);
        this.txtFechaN.getCalendarButton().setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/icon-reservas.png")));
        this.txtFechaN.getCalendarButton().setBackground(SystemColor.textHighlight);
        this.txtFechaN.setDateFormatString("yyyy-MM-dd");
        this.contentPane.add(this.txtFechaN);

        this.txtNacionalidad = new JComboBox();
        this.txtNacionalidad.setBounds(560, 350, 289, 36);
        this.txtNacionalidad.setBackground(SystemColor.text);
        this.txtNacionalidad.setFont(new Font("Roboto", 0, 16));
        this.txtNacionalidad.setModel(new DefaultComboBoxModel(new String[]{"afgano-afgana", "alemán-", "alemana", "árabe-árabe", "argentino-argentina", "australiano-australiana", "belga-belga", "boliviano-boliviana", "brasileño-brasileña", "camboyano-camboyana", "canadiense-canadiense", "chileno-chilena", "chino-china", "colombiano-colombiana", "coreano-coreana", "costarricense-costarricense", "cubano-cubana", "danés-danesa", "ecuatoriano-ecuatoriana", "egipcio-egipcia", "salvadoreño-salvadoreña", "escocés-escocesa", "español-española", "estadounidense-estadounidense", "estonio-estonia", "etiope-etiope", "filipino-filipina", "finlandés-finlandesa", "francés-francesa", "galés-galesa", "griego-griega", "guatemalteco-guatemalteca", "haitiano-haitiana", "holandés-holandesa", "hondureño-hondureña", "indonés-indonesa", "inglés-inglesa", "iraquí-iraquí", "iraní-iraní", "irlandés-irlandesa", "israelí-israelí", "italiano-italiana", "japonés-japonesa", "jordano-jordana", "laosiano-laosiana", "letón-letona", "letonés-letonesa", "malayo-malaya", "marroquí-marroquí", "mexicano-mexicana", "nicaragüense-nicaragüense", "noruego-noruega", "neozelandés-neozelandesa", "panameño-panameña", "paraguayo-paraguaya", "peruano-peruana", "polaco-polaca", "portugués-portuguesa", "puertorriqueño-puertorriqueño", "dominicano-dominicana", "rumano-rumana", "ruso-rusa", "sueco-sueca", "suizo-suiza", "tailandés-tailandesa", "taiwanes-taiwanesa", "turco-turca", "ucraniano-ucraniana", "uruguayo-uruguaya", "venezolano-venezolana", "vietnamita-vietnamita"}));
        this.contentPane.add(this.txtNacionalidad);

        JLabel lblNombre = new JLabel("NOMBRE");
        lblNombre.setBounds(562, 119, 253, 14);
        lblNombre.setForeground(SystemColor.textInactiveText);
        lblNombre.setFont(new Font("Roboto Black", 0, 18));
        this.contentPane.add(lblNombre);

        JLabel lblApellido = new JLabel("APELLIDO");
        lblApellido.setBounds(560, 189, 255, 14);
        lblApellido.setForeground(SystemColor.textInactiveText);
        lblApellido.setFont(new Font("Roboto Black", 0, 18));
        this.contentPane.add(lblApellido);

        JLabel lblFechaN = new JLabel("FECHA DE NACIMIENTO");
        lblFechaN.setBounds(560, 256, 255, 14);
        lblFechaN.setForeground(SystemColor.textInactiveText);
        lblFechaN.setFont(new Font("Roboto Black", 0, 18));
        this.contentPane.add(lblFechaN);

        JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
        lblNacionalidad.setBounds(560, 326, 255, 14);
        lblNacionalidad.setForeground(SystemColor.textInactiveText);
        lblNacionalidad.setFont(new Font("Roboto Black", 0, 18));
        this.contentPane.add(lblNacionalidad);


        JLabel lblTelefono = new JLabel("TELÉFONO");
        lblTelefono.setBounds(562, 406, 253, 14);
        lblTelefono.setForeground(SystemColor.textInactiveText);
        lblTelefono.setFont(new Font("Roboto Black", 0, 18));
        this.contentPane.add(lblTelefono);
        this.txtTelefono = new JTextField();
        this.txtTelefono.setFont(new Font("Roboto", 0, 16));
        this.txtTelefono.setBounds(560, 424, 285, 33);
        this.txtTelefono.setColumns(10);
        this.txtTelefono.setBackground(Color.WHITE);
        this.txtTelefono.setBorder(BorderFactory.createEmptyBorder());
        this.contentPane.add(this.txtTelefono);


        JLabel lblTitulo = new JLabel("REGISTRO HUÉSPED");
        lblTitulo.setBounds(606, 55, 234, 42);
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto Black", 0, 23));
        this.contentPane.add(lblTitulo);


        JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
        lblNumeroReserva.setBounds(560, 474, 253, 14);
        lblNumeroReserva.setForeground(SystemColor.textInactiveText);
        lblNumeroReserva.setFont(new Font("Roboto Black", 0, 18));
        this.contentPane.add(lblNumeroReserva);

        this.txtNreserva = new JTextField();
        this.txtNreserva.setFont(new Font("Roboto", 0, 16));
        this.txtNreserva.setBounds(560, 495, 285, 33);
        this.txtNreserva.setColumns(10);
        this.txtNreserva.setBackground(Color.WHITE);
        this.txtNreserva.setBorder(BorderFactory.createEmptyBorder());
        this.contentPane.add(this.txtNreserva);


        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setBounds(560, 170, 289, 2);
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        this.contentPane.add(separator_1_2);

        JSeparator separator_1_2_1 = new JSeparator();
        separator_1_2_1.setBounds(560, 240, 289, 2);
        separator_1_2_1.setForeground(new Color(12, 138, 199));
        separator_1_2_1.setBackground(new Color(12, 138, 199));
        this.contentPane.add(separator_1_2_1);

        JSeparator separator_1_2_2 = new JSeparator();
        separator_1_2_2.setBounds(560, 314, 289, 2);
        separator_1_2_2.setForeground(new Color(12, 138, 199));
        separator_1_2_2.setBackground(new Color(12, 138, 199));
        this.contentPane.add(separator_1_2_2);

        JSeparator separator_1_2_3 = new JSeparator();
        separator_1_2_3.setBounds(560, 386, 289, 2);
        separator_1_2_3.setForeground(new Color(12, 138, 199));
        separator_1_2_3.setBackground(new Color(12, 138, 199));
        this.contentPane.add(separator_1_2_3);

        JSeparator separator_1_2_4 = new JSeparator();
        separator_1_2_4.setBounds(560, 457, 289, 2);
        separator_1_2_4.setForeground(new Color(12, 138, 199));
        separator_1_2_4.setBackground(new Color(12, 138, 199));
        this.contentPane.add(separator_1_2_4);

        JSeparator separator_1_2_5 = new JSeparator();
        separator_1_2_5.setBounds(560, 529, 289, 2);
        separator_1_2_5.setForeground(new Color(12, 138, 199));
        separator_1_2_5.setBackground(new Color(12, 138, 199));
        this.contentPane.add(separator_1_2_5);


        JPanel btnguardar = new JPanel();
        btnguardar.setBounds(723, 560, 122, 35);
        btnguardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Busqueda busqueda = new Busqueda();
                busqueda.setVisible(true);
                guardarHuesped();

            }
        });
        btnguardar.setLayout((LayoutManager) null);
        btnguardar.setBackground(new Color(12, 138, 199));
        this.contentPane.add(btnguardar);
        btnguardar.setCursor(new Cursor(12));

        JLabel labelGuardar = new JLabel("GUARDAR");
        labelGuardar.setHorizontalAlignment(0);
        labelGuardar.setForeground(Color.WHITE);
        labelGuardar.setFont(new Font("Roboto", 0, 18));
        labelGuardar.setBounds(0, 0, 122, 35);
        btnguardar.add(labelGuardar);


        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 489, 634);
        panel.setBackground(new Color(12, 138, 199));
        this.contentPane.add(panel);
        panel.setLayout(null);

        JLabel imagenFondo = new JLabel("");
        imagenFondo.setBounds(0, 121, 479, 502);
        panel.add(imagenFondo);
        imagenFondo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/registro.png")));

        JLabel logo = new JLabel("");
        logo.setBounds(194, 39, 104, 107);
        panel.add(logo);
        logo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/Ha-100px.png")));


        JPanel btnexit = new JPanel();
        btnexit.setBounds(857, 0, 53, 36);
        this.contentPane.add(btnexit);
        btnexit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                MenuPrincipal principal = new MenuPrincipal();
                principal.setVisible(true);
                dispose();
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
        btnexit.setBackground(Color.white);
        this.labelExit = new JLabel("X");
        this.labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(this.labelExit);
        this.labelExit.setHorizontalAlignment(0);
        this.labelExit.setForeground(SystemColor.black);
        this.labelExit.setFont(new Font("Roboto", 0, 18));
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

    //mETODO PARA GUARDAR HUESPED,CAPTURAMOS VALORES DEL JFRAME
    public void guardarHuesped() {
        var huesped = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String fecha_nacimiento = sdf.format(txtFechaN.getDate());
        String nacionalidad = Objects.requireNonNull(txtNacionalidad.getSelectedItem()).toString();
        String telefono = txtTelefono.getText();
        Integer id_reserva = Integer.parseInt(txtNreserva.getText());

        huesped.put("nombre", nombre);
        huesped.put("apellido", apellido);
        huesped.put("fecha_nacimiento", fecha_nacimiento);
        huesped.put("nacionalidad", nacionalidad);
        huesped.put("telefono", telefono);
        huesped.put("id_reserva", String.valueOf(id_reserva));

        try {
            this.huespedController.guardarHuesped(huesped);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Huesped registrado correctamente");
    }

}
