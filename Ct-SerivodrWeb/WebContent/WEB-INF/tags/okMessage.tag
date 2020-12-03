<!-- SI CARGAN UNA LISTA CON EL NOMBRE errores. SE MUESTRA EL CONTENIDO DE LA MISMA -->

<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="hidden" value="" />
<c:if test="${ empty mensajes }">
	<c:set var="hidden" value="hidden" />
</c:if>

<div class="alert alert-success" role="alert" <c:out value="${ hidden }"/> />
	<p class="h4">
	  	<c:forEach var="mensaje" items="${ mensajes }"> 
	  		<li>${ mensaje } </li>
	  	</c:forEach> 
  	</p>
</div>