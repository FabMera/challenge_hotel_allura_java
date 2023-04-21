package com.allura.latam.hotel.controlador;

import com.allura.latam.hotel.conexion.CreaConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuespedController {

    //metodo para listar huespedes
    public List<Map<String, String>> listarHuespedes() throws SQLException {
        final Connection connection = new CreaConexion().recuperarConexion();
        try (connection) {
            final PreparedStatement statement = connection.prepareStatement("SELECT id,nombre,apellido,fecha_nacimiento,nacionalidad,telefono,id_reserva FROM huespedes");

            try (statement) {
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                List<Map<String, String>> huespedes = new ArrayList<>();
                while (resultSet.next()) {
                    Map<String, String> fila = new HashMap<>(); /*map.put(clave,valor)*/

                    fila.put("id", String.valueOf(resultSet.getInt("id")));
                    fila.put("nombre", resultSet.getString("nombre"));
                    fila.put("apellido", resultSet.getString("apellido"));

                    fila.put("fecha_nacimiento", resultSet.getDate("fecha_nacimiento").toString());
                    fila.put("nacionalidad", resultSet.getString("nacionalidad"));
                    fila.put("telefono", resultSet.getString("telefono"));
                    fila.put("id_reserva", String.valueOf(resultSet.getInt("id_reserva")));
                    huespedes.add(fila);
                }
                return huespedes;
            }
        }
    }

    //metodo para guardar huespedes
    public void guardarHuesped(Map<String, String> huesped) throws SQLException {
        String nombre = huesped.get("nombre");
        String apellido = huesped.get("apellido");
        Date fecha_nacimiento = Date.valueOf(huesped.get("fecha_nacimiento"));
        String nacionalidad = huesped.get("nacionalidad");
        String telefono = huesped.get("telefono");
        Integer id_reserva = Integer.parseInt(huesped.get("id_reserva"));
        final Connection connection = new CreaConexion().recuperarConexion();
        try (connection) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO huespedes (nombre,apellido,fecha_nacimiento,nacionalidad,telefono,id_reserva) VALUES (?,?,?,?,?,(SELECT MAX(id) FROM reservas))", Statement.RETURN_GENERATED_KEYS);
            try (statement) {
                ejecutarRegistro(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva, statement);
                connection.commit();
                System.out.println("commit");
            } catch (Exception e) {
                connection.rollback();
                System.out.println("rollback");
            }
        }
    }

    private static void ejecutarRegistro(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, Integer id_reserva, PreparedStatement statement) throws SQLException {
        statement.setString(1, nombre);
        statement.setString(2, apellido);
        statement.setDate(3, fecha_nacimiento);
        statement.setString(4, nacionalidad);
        statement.setString(5, telefono);
        statement.setInt(6, id_reserva);
        statement.execute();
        final ResultSet resultSet = statement.getGeneratedKeys();

        try (resultSet) {
            if (resultSet.next()) {
                System.out.println("Huesped registrado con exito" + resultSet.getInt(1));

            }

        }
    }


    public int modificarHuesped(String nombre, String apellido, String fecha_nacimiento, String nacionalidad, String telefono, Integer id) throws SQLException {

        final Connection connection = new CreaConexion().recuperarConexion();
        try (connection) {
            final PreparedStatement statement = connection.prepareStatement("UPDATE huespedes SET nombre = ?,apellido = ?,fecha_nacimiento = ?,nacionalidad = ?,telefono = ? WHERE id = ?");
            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setString(3, fecha_nacimiento);
                statement.setString(4, nacionalidad);
                statement.setString(5, telefono);
                statement.setInt(6, id);
                statement.execute();
                int updateCount = statement.getUpdateCount();
                return updateCount;
            }
        }
    }


}

