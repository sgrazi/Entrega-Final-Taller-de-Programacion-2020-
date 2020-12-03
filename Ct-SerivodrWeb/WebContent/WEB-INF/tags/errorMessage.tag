<!-- SI CARGAN UNA LISTA CON EL NOMBRE errores. SE MUESTRA EL CONTENIDO DE LA MISMA -->

<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="hidden" value="" />
<c:if test="${ empty errores }">
	<c:set var="hidden" value="hidden" />
</c:if>

<div class="alert alert-danger" role="alert" <c:out value="${ hidden }"/> />
  Ocurrieron los siguientes errores:
  <ul>
  	<c:forEach var="error" items="${ errores }"> 
  		<li>${ error } </li>
  	</c:forEach> 
  </ul>
</div>