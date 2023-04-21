package com.allura.latam.hotel;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exito extends JDialog {
    private final JPanel contentPanel = new JPanel();

    public static void main(String[] args) {
        try {
            Exito dialog = new Exito();
            dialog.setDefaultCloseOperation(2);
            dialog.setVisible(true);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public Exito() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(Exito.class.getResource("/imagenes/aH-40px.png")));
        this.setBounds(100, 100, 394, 226);
        this.getContentPane().setLayout(new BorderLayout());
        this.contentPanel.setBackground(SystemColor.control);
        this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.getContentPane().add(this.contentPanel, "Center");
        this.setLocationRelativeTo((Component)null);
        this.contentPanel.setLayout((LayoutManager)null);
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(Exito.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_1.setBounds(123, 11, 100, 100);
        this.contentPanel.add(lblNewLabel_1);
        lblNewLabel_1 = new JLabel("Datos guardados satisfactoriamente");
        lblNewLabel_1.setForeground(new Color(12, 138, 199));
        lblNewLabel_1.setFont(new Font("Arial", 1, 18));
        lblNewLabel_1.setBounds(27, 122, 322, 21);
        this.contentPanel.add(lblNewLabel_1);
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(2));
        this.getContentPane().add(buttonPane, "South");
        JButton cancelButton = new JButton("OK");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Exito.this.dispose();
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
            }
        });
        cancelButton.setActionCommand("OK");
        buttonPane.add(cancelButton);
        this.getRootPane().setDefaultButton(cancelButton);
        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
    }
}
