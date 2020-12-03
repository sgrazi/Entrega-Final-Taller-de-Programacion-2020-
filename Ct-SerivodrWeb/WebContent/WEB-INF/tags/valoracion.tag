<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="promedio" required="false" %>

<c:choose>
<c:when test="${ empty promedio }">
	<c:set var="puntaje" value="0"/>
	<c:if test="${(not empty valoracion) && valoracion > 0}">
		<c:set var="puntaje" value="${valoracion}"/>
	</c:if>
	
	<c:forEach var = "i" begin = "1" end = "5">
		<c:set var="link" value="espectaculo=${espectaculo.getNombre()}&nickEspectador=${usuarioLog.getNickname()}&puntaje=${i}" />
		
		<a href="./valorarEsp?${link}" class="text-decoration-none">
		<c:choose>
		 <c:when test="${ puntaje >= i }">
		     <i class="fas fa-star"> </i>	 
		 </c:when>    
		 <c:otherwise>
		     <i class="far fa-star"> </i>
		 </c:otherwise>
		</c:choose>
		</a>
	</c:forEach>
</c:when>
<c:otherwise>
	<c:forEach var = "i" begin = "1" end = "5">
	<c:choose>
		 <c:when test="${ promedio >= i }">
		     <i class="fas fa-star"> </i>	 
		 </c:when>    
		 <c:otherwise>
		     <i class="far fa-star"> </i>
		 </c:otherwise>
	</c:choose>
	</c:forEach>
</c:otherwise>
</c:choose>