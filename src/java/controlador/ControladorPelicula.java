/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import modelo.Pelicula;

/**
 *
 * @author SENA
 */
@WebServlet(name = "ControladorPelicula", urlPatterns = {"/ControladorPelicula"})
public class ControladorPelicula extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorPelicula</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorPelicula at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("fIdPelicula");
        String nombre = request.getParameter("fTituloPelicula");
        String fecha = request.getParameter("fFechaEstreno");
        String genero = request.getParameter("fIdGenero");
        String precio = request.getParameter("fPrecioPelicula");
        String accion = request.getParameter("fAccion");
        
        int idPelicula=0;
        try{
            idPelicula = Integer.parseInt(id);
        }catch(NumberFormatException nfe){
            
        }
        LocalDate fechaEstreno = LocalDate.now();
        try{
            fechaEstreno = LocalDate.parse(fecha);
        }catch(DateTimeParseException dtpe){
            
        }
        int idGenero = 0;
        try{
            idGenero = Integer.parseInt(genero);
        }catch (NumberFormatException nfe){
            
        }
        float precioPelicula = 0.0f;
        try{
            precioPelicula = Float.parseFloat(precio);
        }catch(NumberFormatException nfe){
            
        }
        Pelicula  unaPelicula = new Pelicula();
        unaPelicula.setIdPelicula(idPelicula);
        unaPelicula.setTituloPelicula(nombre);
        unaPelicula.setFechaEstreno(fechaEstreno);
        unaPelicula.setIdGenero(idGenero);
        unaPelicula.setPrecioPelicula(precioPelicula);
    
        String mensaje="";
        switch(accion.toLowerCase()){
            case "insertar" -> unaPelicula.insertar();
            case "modificar" -> unaPelicula.modirficar();
            case "eliminar" -> unaPelicula.eliminar();
        }
        request.getRequestDispatcher("/WEB-INF/FormularioPelicula.jsp?msj="+mensaje).forward(request, response);
    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
