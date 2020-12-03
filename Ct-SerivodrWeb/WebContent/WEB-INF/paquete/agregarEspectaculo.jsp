<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:contenedor>
	<form action="./agregarEspectaculoPaquete" method="post" class="col-6" autocomplete="off">
		<h3 class="mb-0">Agregar espectáculo a paquete</h3>
		<t:errorMessage />
		<c:if test="${empty error_paquete}">
			<div class="form-group">
				<label for="paquete">Paquete</label> <select name="paquete"
					class="custom-select" id="select-paquete" required>
					<c:forEach var="paquete" items="${paquetes}">
						<option value="${paquete}">${paquete}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="form-group">
				<label for="plataforma">Plataforma</label> <select name="plataforma"
					class="custom-select" id="select-plataforma" required>
					<c:forEach var="plataforma" items="${plataformas}">
						<option value="${plataforma}">${plataforma}</option>
					</c:forEach>
				</select>
			</div>
	
			<button class="btn btn-primary" type="submit">Siguiente</button>
			<a class="btn btn-danger" href="./home">Cancelar</a>
		</c:if>
		<c:if test="${not empty error_paquete}">
			<h4 class="mt-3 ml-2 text-muted">${error_paquete} <a class="ml-3 btn btn-sm btn-primary" href="./home">Volver al Home</a></h4>
		</c:if>
	</form>
</t:contenedor>