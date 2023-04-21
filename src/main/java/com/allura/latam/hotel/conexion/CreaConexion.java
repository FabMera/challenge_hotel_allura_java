package com.allura.latam.hotel.conexion;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class CreaConexion {
/*    private final DataSource dataSource;*/

    public CreaConexion() {
/*
        var pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel?useTimeZone=true&ServerTimeZone=UTC");
        pooledDataSource.setUser("root");
        pooledDataSource.setPassword("12345");

        this.dataSource = pooledDataSource;*/
    }

    public Connection recuperarConexion() throws SQLException {
        //Se utiliza el pool en ves del Driver para crear la conexion a la base de datos y no se cierra la conexion  hasta que se termine de ejecutar el programa
       return DriverManager.getConnection("jdbc:mariadb://localhost:3306/hotel?useTimeZone=true&ServerTimeZone=UTC", "root", "12345");
        //return this.dataSource.getConnection();
    }
}
