/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Date;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author SENA
 */
public class Pelicula {

    private int idPelicula;
    private String tituloPelicula;
    private LocalDate fechaEstreno;
    private int idGenero;
    private float precioPelicula;
    private int paginacion = 0;

    public Pelicula() {
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public float getPrecioPelicula() {
        return precioPelicula;
    }

    public void setPrecioPelicula(float precioPelicula) {
        this.precioPelicula = precioPelicula;
    }

    public int getPaginacion() {
        return paginacion;
    }

    public void setPaginacion(int paginacion) {
        this.paginacion = paginacion;
    }

    public ArrayList listar(int pagina) {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        ArrayList listaPeliculas = new ArrayList();
        Pelicula laPelicula;
        String listado = "SELECT * FROM " + this.getClass().getSimpleName() + " ORDER BY idPelicula";

        if (pagina > 0) {
            int paginacionMax = pagina * this.paginacion;
            int paginacionMin = paginacionMax - this.paginacion;
            listado = "SELECT * FROM " + this.getClass().getSimpleName() + " ORDER BY idPelicula LIMIT " + paginacionMin + "," + paginacionMax;
        }
        try {
            ResultSet rs = st.executeQuery(listado);
            while (rs.next()) {
                laPelicula = new Pelicula();
                laPelicula.setIdPelicula(rs.getInt("idPelicula"));
                laPelicula.setTituloPelicula(rs.getString("tituloPelicula"));
                laPelicula.setFechaEstreno(rs.getDate("fechaEstreno").toLocalDate());
                laPelicula.setIdGenero(rs.getInt("idGenero"));
                laPelicula.setPrecioPelicula(rs.getFloat("precioPelicula"));
                listaPeliculas.add(laPelicula);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar Pelicula:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
        return listaPeliculas;
    }

    public void insertar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("INSERT INTO Pelicula( idPelicula,tituloPelicula, fechaEstreno, idGenero, precioPelicula)" + "VALUES(" + null + ",'" + getTituloPelicula() + "','" + getFechaEstreno() + "'," + getIdGenero() + "," + getPrecioPelicula() + ")");
        } catch (SQLException ex) {
            System.out.println("Error al insertar pelicula:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public void modirficar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            java.sql.Date d = Date.valueOf(fechaEstreno);
            System.out.println("fecha1111 "+d);
            String consulta = "UPDATE Pelicula SET tituloPelicula='" + getTituloPelicula() + "',fechaEstreno='" + d + "', idGenero=" + getIdGenero() + ", precioPelicula=" + getPrecioPelicula() + " WHERE idPelicula=" + getIdPelicula(); 
            System.out.println("esta es la c ----"+consulta);
            st.executeUpdate(consulta);
        } catch (SQLException ex) {
            System.out.println("Error al modificar pelicula:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public void eliminar() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        try {
            st.executeUpdate("DELETE FROM Pelicula WHERE idPelicula =" + getIdPelicula());
        } catch (SQLException ex) {
            System.out.println("Error al eliminar pelicula:" + ex.getLocalizedMessage());
        }
        conexion.desconectar();
    }

    public int cantidadPaginas() {
        Conexion conexion = new Conexion();
        Statement st = conexion.conectar();
        int cantidadDeBloques = 0;
        try {
            ResultSet rs = st.executeQuery("SELECT CEIL(COUNT(idPelicula)/" + this.paginacion + ") AS cantidad FROM " + this.getClass().getSimpleName());
            if (rs.next()) {
                cantidadDeBloques = rs.getInt("cantidad");
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener la cantidad de paginas pelicula" + ex.getLocalizedMessage());
        }
        return cantidadDeBloques;
    }

}
