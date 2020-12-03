<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="modelo.*"%>
<%@page import="publicar.*"%>
<%@page import="controladores.Login"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
	<jsp:include page="/WEB-INF/template/head.jsp"/>
	<title>Modificar Datos</title>
   </head>

<body>

  <!-- HEADER / NAV -->
  <jsp:include page="/WEB-INF/template/header.jsp"/>
  <!-- TERMINA EL HEADER -->
  
  <div class="container mb-5">
  <%
  	DtUsuario dtusr;
  		try {
  			dtusr = Login.getUsuarioLogueado(request);
  		} catch(Exception ex){
  			dtusr = null;
  		}
  		
  		if(dtusr != null) {
  %>
    <div class="py-5 text-center">
      <img class="d-block mx-auto mb-4" src="<%= dtusr.getImagen() %>"
        alt="" width="72" height="72">
      <h2><%= dtusr.getNickname() %></h2>
      <p class="lead"><%= dtusr.getCorreoElectronico() %></p>
    </div>


	<!-- EMPIEZA EL FORMULARIO -->
	
	<form action="formModificarDatos" method="POST">
    <div class="row">
      <div class="col-md order-md-1">
        <h4 class="mb-3">Mis datos:</h4>
        
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="firstName">Nombre</label>
              <input type="text" class="form-control" name="nombreModificar" placeholder="" value="<%= dtusr.getNombre() %>" required>
              
            </div>
            <div class="col-md-6 mb-3">
              <label for="lastName">Apellido</label>
              <input type="text" class="form-control" name="apellido" placeholder="" value="<%= dtusr.getApellido() %>" required>
              
            </div>
          </div>
        

        <div class="row mb-3">
          <div class="col-md-8 mb-3">
            <label for="imagen">Imagen</label>
            <input type="text" class="form-control" name="imagen" placeholder="" value="<%= dtusr.getImagen() %>">
          </div>
        </div>
        
        <% //if(usr instanceof DtArtista)
        if(dtusr instanceof DtArtista)
        
        {%>
        <div class="row mb-3">
          <div class="col-md-8 mb-3">
            <label for="imagen">Descripcion</label>
            <textarea class="form-control" name="descripcion" id="descripcion"  rows="3"><%= ((DtArtista) dtusr).getDescripcion() %></textarea>
          </div>
        </div>
        
        <div class="row mb-3">
          <div class="col-md-8 mb-3">
            <label for="imagen">Biografia</label>
            <textarea class="form-control" name="biografia" id="biografia"  rows="3"><%= ((DtArtista) dtusr).getBiografia() %></textarea>
          </div>
        </div>
        
        <div class="row mb-3">
          <div class="col-md-8 mb-3">
            <label for="imagen">Sitio web</label>
            <input type="text" class="form-control" name="url" placeholder="" value="<%= ((DtArtista) dtusr).getUrl() %>" >
          </div>
        </div>
        <%}%>

        <div class="mb-5">
        
        <%  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  %>
          
            <label>Fecha de nacimiento:</label>
            <div class="col-sm-10">
              <input type="date" class="form-control" name="fecha" placeholder="Ingresar Fecha" value="<%= dateFormat.format(dtusr.getFechaDeNacimiento().toGregorianCalendar().getTime()) %>" required="">
            </div>
          
        </div>

        <hr class="mb-2">

        
        
        <div class="custom-control custom-checkbox py-5">
             <input type="checkbox" class="custom-control-input" id="c1" name="c1" onclick="showMe('div1')">
             <label class="custom-control-label h4" for="c1">Cambiar Contraseña</label>
         </div>
        
        
          <div class="row" id="div1" style="display:none">
            <div class="col-md-7 mb-3">
              <label for="firstName">Contraseña anterior</label>
              <input type="password" name="contrasenaAnterior" class="form-control" placeholder="">
              
            </div>
            <div class="col-md-7 mb-3">
              <label for="firstName">Nueva contraseña</label>
              <input type="password" name="nuevaContrasena1" class="form-control" placeholder="">
              
            </div>
            <div class="col-md-7 mb-3">
              <label for="firstName">Repita nueva contraseña</label>
              <input type="password" name="nuevaContrasena2" class="form-control" placeholder="">
              
            </div>
          </div>
        
        <button type="submit" class="btn btn-primary btn-lg btn-block-6">Guardar modificaciones</button>
        <a class="btn btn-danger btn-lg btn-block-6 " href="./home">Cancelar</a>
        
        <c:set var="hidden" value="" />
					<c:if test="${ empty errores }">
						<c:set var="hidden" value="hidden" />
					</c:if>
	
        <label for="error" class="${ hidden }" style="color: red; ">${ errores } </label>
        
      </div>
    </div>
    </form>
    <!-- TERMINA EL FORMULARIO -->
    
   <%}%>
  </div>
  <!--container-->
<script type="text/javascript">

    function showMe(box) {
        var chboxs = document.getElementsByName("c1");
        var vis = "none";
        for (var i = 0; i < chboxs.length; i++) {
            if (chboxs[i].checked) {
                vis = "block";
            	break;
            }
        }
        document.getElementById(box).style.display = vis;
    }
</script>
<jsp:include page="/WEB-INF/template/scripts.jsp"/>
</body>
</html>