<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:contenedor>
	<form action="./agregarEspectaculoPaquete" method="post" class="col-6" autocomplete="off">
		<input type="hidden" name="paquete" value="${paquete}">
		<input type="hidden" name="plataforma" value="${plataforma}">
		<h3 class="mb-0">Agregar espectáculo al paquete <b>${paquete}:</b></h3>
		<p class="mb-2"><small>Plataforma: <b>${plataforma}</b></small></p>
		<t:errorMessage />
		<div class="form-group mb-5">
			<label for="espectaculo">Espectáculo</label> <select name="espectaculo"
				class="custom-select" id="select-espectaculo" required>
				<c:forEach var="espectaculo" items="${espectaculos}">
					<option value="${espectaculo.getNombre()}">${espectaculo.getNombre()}</option>
				</c:forEach>
			</select>
		</div>

		<button class="btn btn-primary" type="submit">Siguiente</button>
		<a class="btn btn-danger" href="./agregarEspectaculoPaquete">Volver</a>
	</form>
</t:contenedor>