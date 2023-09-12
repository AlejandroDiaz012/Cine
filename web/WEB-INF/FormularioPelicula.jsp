<%-- 
    Document   : FormularioPelicula
    Created on : 17/08/2023, 8:54:18 a. m.
    Author     : SENA
--%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Pelicula</title>
    </head>
    <jsp:useBean id="unaPelicula" class="modelo.Pelicula" scope="request" />
    <body>
        <%--<jsp:include page="jspf/menu.jspf"></jsp:include>--%>
        <h1>Formulario Pelicula</h1>
        <table border="1">
            <tr>
                <th>Titulo</th>
                <th>Estreno</th>
                <th>Genero</th>
                <th>Precio</th>
                <th></th>
            </tr>
            <c:forEach items="${unaPelicula.listar(0)}" var="laPelicula">
                <tr>
                <form action="ControladorPelicula" method="post">
                    <td><input type="hidden" name="fIdPelicula"   value="${laPelicula.idPelicula}">
                        <input type="text" name="fTituloPelicula"  value="${laPelicula.tituloPelicula}"></td>
                    <td><input type="date" name="fFechaEstreno"  value="${laPelicula.fechaEstreno}"></td>
                    <td><input type="number" name="fIdGenero"  value="${laPelicula.idGenero}"></td>
                    <td><input type="number" step="0.01" name="fPrecioPelicula"  value="${laPelicula.precioPelicula}"></td>
                    <td><input type="submit" name="fAccion"  value="Modificar">
                        <button type="submit" name="fAccion"  value="Eliminar">Eliminar</button> </td>

                </form>
            </tr>
        </c:forEach>
        <tr>
        <form action="ControladorPelicula" method="post">
            <td><input type="hidden" name="fIdPelicula"  value="0">
                <input type="text" name="fTituloPelicula" ></td>
            <td><input type="date" name="fFechaEstreno"></td>
            <td><input type="number" name="fIdGenero" ></td>
            <td><input type="number" step="0.01" name="fPrecioPelicula" step="0.01" ></td>
            <td><button type="submit" name="fAccion"  value="Insertar">Insertar</button>
                <button type="reset" name="fAccion"  value="Limpiar">Limpiar</button> </td>

        </form>
    </tr>
</table>
<c:forEach begin="1" end="5" var="i">
    <c:out value="${i}"/>

</c:forEach>

</body>
</html>
