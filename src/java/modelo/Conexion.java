/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

/**
 *
 * @author SENA
 */
public class Conexion {

    Connection conexion = null;

    public Statement conectar(){
        Statement st = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jpelicula");
            conexion = ds.getConnection("Alejandro", "alejandrodiaz@1234567890");
            st = (Statement) conexion.createStatement();

        } catch (NamingException ex) {
            System.out.println("Error al iniciar contexto:" + ex.getMessage());

        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la BD:" + ex.getLocalizedMessage());
        }
        return st;
    }

    public void desconectar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la BD:" + ex.getLocalizedMessage());
        }
    }
}
