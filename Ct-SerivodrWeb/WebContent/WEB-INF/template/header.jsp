<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="publicar.DtUsuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav id="header" class="navbar navbar-light bg-warning sticky-top">
	<div class="navbar-brand d-none d-md-flex flex-column py-0"
		style="align-items: center;">
		<img class="mb-n2" src="svg/crown-solid.svg" alt="" width="30"
			height="30"> <img src="svg/ticket-alt-solid.svg" alt=""
			width="30" height="30">
	</div>
	<%
		String nombre;
		DtUsuario usr;
		try {
			usr = (DtUsuario) request.getSession().getAttribute("usuario_logueado");

			nombre = usr.getCorreoElectronico() + request.getParameter("usuario_logueado");

		} catch (Exception ex) {
			usr = null;
		}
	%>

	<div class="dropdown d-md-none">
	  <a class="btn btn-secondary" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		<i class="fa fa-bars"></i>
	  </a>
		<c:set var="user" value="<%= usr %>" />
		<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
		    <a class="dropdown-item" href="./buscar?buscar=">Ver Espectaculos</a>
		    <a class="dropdown-item" href="./listarFunciones">Ver Funciones</a>
		    <div class="dropdown-divider my-1"></div>
		    <c:choose>
		    	<c:when test="${not empty user}">
		    		<a class="dropdown-item text-danger" href="./logout">Salir</a>
		    	</c:when>
		    	<c:otherwise>
			    	<a class="dropdown-item text-primary" href="./login">Iniciar sesion</a>
		    	</c:otherwise>
		    </c:choose>
	    </div>
	</div>
	
	<a href="./home" class="navbar-brand"> CoronaTickets </a>

	<form action="./buscar" class="form-inline mb-0 flex-grow-1 d-none d-sm-flex mx-3"
		 method=GET>
		
		<input name="buscar" class="barra-busqueda form-control mr-sm-2" type="search"
			placeholder="Buscar espectaculos o paquetes">
		<button class="btn btn-success my-2 my-sm-0" type="submit">Buscar</button>
	</form>


	<%
		if (usr != null) {
	%>
	<div class="dropdown mr-5 d-none d-md-block">
		<button class="btn btn-secondary dropdown-toggle" type="button"
			id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false">
			<%=usr.getNombre()%>
			<%=usr.getApellido()%>
		</button>
		<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			<a class="dropdown-item" href="./perfilUsuario?nickPerfil=<%= usr.getNickname() %>">Perfil</a> 
			<a class="dropdown-item" href="./logout">Cerrar sesion</a>
		</div>
	</div>
	
	<% } else { %>
	
	<div class="d-none d-md-block">
		
			<a class="btn btn-light" href="./login">Iniciar sesion</a>
			<a class="btn btn-light" href="./altaUsuario?action=registrarse">Registro</a>

	</div>
	<% } %>
</nav>
