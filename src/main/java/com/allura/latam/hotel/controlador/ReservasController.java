package com.allura.latam.hotel.controlador;

import com.allura.latam.hotel.conexion.CreaConexion;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservasController {
    //metodo para listar reservas (JDBC)
    public List<Map<String, String>> listarReservas() throws SQLException {
        final Connection connection = new CreaConexion().recuperarConexion();
        try (connection) {
            final PreparedStatement statement = connection.prepareStatement("SELECT id,fecha_entrada,fecha_salida,valor,forma_pago FROM reservas");

            try (statement) {
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                List<Map<String, String>> reservas = new ArrayList<>();
                while (resultSet.next()) {
                    Map<String, String> fila = new HashMap<>(); /*map.put(clave,valor)*/
                    fila.put("id", String.valueOf(resultSet.getInt("id")));
                    fila.put("fecha_entrada", resultSet.getDate("fecha_entrada").toString());
                    fila.put("fecha_salida", resultSet.getDate("fecha_salida").toString());
                    fila.put("valor", resultSet.getString("valor"));
                    fila.put("forma_pago", resultSet.getString("forma_pago"));
                    reservas.add(fila);
                }
                return reservas;
            }
        }
    }


    //Metodo para buscar reservas por id de reserva en busqueda (JDBC)
    public List<Map<String, String>> listarReservasPorId(Integer id) throws SQLException {
        final Connection connection = new CreaConexion().recuperarConexion();
        try (connection) {
            final PreparedStatement statement = connection.prepareStatement("SELECT id,fecha_entrada,fecha_salida,valor,forma_pago FROM reservas WHERE id=?;");

            try (statement) {

                statement.setInt( 1,id);
                ResultSet resultSet = statement.executeQuery();

                List<Map<String, String>> resultadoPorId = new ArrayList<>();

                while (resultSet.next()) {
                    Map<String, String> fila = new HashMap<>(); /*map.put(clave,valor)*/

                    fila.put("id", String.valueOf(resultSet.getInt("id")));
                    fila.put("fecha_entrada", resultSet.getDate("fecha_entrada").toString());
                    fila.put("fecha_salida", resultSet.getDate("fecha_salida").toString());
                    fila.put("valor", resultSet.getString("valor"));
                    fila.put("forma_pago", resultSet.getString("forma_pago"));
                    resultadoPorId.add(fila);
                }
                return resultadoPorId;
            }
        }
    }


    //Metodo para guardar reservas (JDBC)
    public void guardarReserva(Map<String, String> reserva) throws SQLException, ParseException {

        Date fecha_entrada = Date.valueOf(reserva.get("fecha_entrada"));
        Date fecha_salida = Date.valueOf(reserva.get("fecha_salida"));
        String valor = reserva.get("valor");
        String forma_pago = reserva.get("forma_pago");

        final Connection connection = new CreaConexion().recuperarConexion();
        try (connection) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO reservas (fecha_entrada,fecha_salida,valor,forma_pago) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            try (statement) {
                ejecutarRegistro(fecha_entrada, fecha_salida, valor, forma_pago, statement);
                connection.commit();
                System.out.println("commit");
            } catch (Exception e) {
                connection.rollback();
                System.out.println("rollback");
            }
        }
    }

    //Metodo para ejecutar la reserva y mostrar el numero de reserva generado (JDBC)
    private static void ejecutarRegistro(Date fecha_entrada, Date fecha_salida, String valor, String forma_pago, PreparedStatement statement) throws SQLException {

        statement.setDate(1, fecha_entrada);
        statement.setDate(2, fecha_salida);
        statement.setString(3, valor);
        statement.setString(4, forma_pago);
        statement.execute();
        final ResultSet resultSet = statement.getGeneratedKeys();
        try (resultSet) {
            if (resultSet.next()) {
                System.out.println(String.format("Se ha creado la reserva con el id: " + resultSet.getInt(1)));
                JOptionPane.showMessageDialog(null, "TU NUMERO DE RESERVA es :  " + resultSet.getInt(1));
                System.out.println(fecha_entrada.getClass().getSimpleName() + " " + fecha_salida.getClass().getSimpleName() + " " + valor.getClass().getSimpleName() + " " + forma_pago.getClass().getSimpleName());
            }

        }

    }


    //metodo para eliminar reservas (JDBC)
    public int eliminarReserva(Integer id) throws SQLException {
        final Connection connection = new CreaConexion().recuperarConexion();
        try (connection) {
            final PreparedStatement statement = connection.prepareStatement("DELETE FROM reservas WHERE id = ?");
            try (statement) {
                statement.setInt(1, id);
                statement.execute();
                return statement.getUpdateCount();
            }
        }
    }



    //metodo para modificar reservas (JDBC)
    public int modificarReserva(String fecha_entrada, String fecha_salida, String valor, String forma_pago, Integer id) throws SQLException {
        final Connection connection = new CreaConexion().recuperarConexion();
        try (connection) {
            final PreparedStatement statement = connection.prepareStatement("UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_pago = ? WHERE id = ?");
            try (statement) {
                statement.setString(1, fecha_entrada);
                statement.setString(2, fecha_salida);
                statement.setString(3, valor);
                statement.setString(4, forma_pago);
                statement.setInt(5, id);
                statement.execute();
                return statement.getUpdateCount();
            }
        }
    }

    //Metodo para buscar reservas (JDBC)
    public <Int> List<Map<String, String>> buscarReserva(Integer id) throws SQLException {
        final Connection connection = new CreaConexion().recuperarConexion();
        try (connection) {
            final PreparedStatement statement = connection.prepareStatement("SELECT id,fecha_entrada,fecha_salida,valor,forma_pago FROM reserva WHERE id = ?");
            try (statement) {
                statement.setInt(1, id);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                List<Map<String, String>> reservas = new ArrayList<>();
                while (resultSet.next()) {
                    Map<String, String> fila = new HashMap<>(); /*map.put(clave,valor)*/
                    fila.put("id", String.valueOf(resultSet.getInt("id")));
                    fila.put("fecha_entrada", resultSet.getDate("fecha_ingreso").toString());
                    fila.put("fecha_salida", resultSet.getDate("fecha_salida").toString());
                    fila.put("valor", resultSet.getString("valor"));
                    fila.put("forma_pago", resultSet.getString("forma_pago"));

                }
                return reservas;
            }
        }
    }


}
