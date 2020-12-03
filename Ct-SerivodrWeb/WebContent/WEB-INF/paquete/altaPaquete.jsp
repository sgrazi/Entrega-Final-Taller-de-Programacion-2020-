<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="controladores.Paquete"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="fechaMin" value="${Paquete.fechaMin}" />

<t:contenedor>
	<form action="./altaPaquete" method="post" class="col-7" autocomplete="off" enctype="multipart/form-data">
		<h4 class="pt-5 pb-3">Alta de paquete de espectaculos</h4>
		<t:errorMessage />
		<div class="form-group">
			<label for="nombre">Nombre</label>
			<input type="text" class="form-control" name="nombre" id="nombre" placeholder=""
				value="${empty param.nombre ? '' : param.nombre}" required>
			<!-- <div class="invalid-feedback">Valid first name is required.</div> -->
		</div>

		<div class="form-group">
			<label for="descripcion">Descripcion</label>
			<textarea class="form-control" style="resize: none;" name="descripcion" id="descripcion" rows="4"
				required>${empty param.descripcion ? '' : param.descripcion}</textarea>
		</div>

		<div class="row">
			<div class="form-group col-6">
				<label>Fecha de inicio:</label>
				<input type="date" class="form-control" id="fechaInicio" placeholder="Ingresar Fecha" name="inicio"
					value="${empty param.inicio ? '' : param.inicio}" min="${fechaMin}" required>
			</div>

			<div class="form-group col-6">
				<label>Fecha de fin:</label>
				<input type="date" class="form-control" id="fechaFin" placeholder="Ingresar Fecha" name="fin"
					value="${empty param.fin ? '' : param.fin}" min="${fechaMin}" required>
			</div>
		</div>

		<div class="form-group">
			<label for="descuento">Descuento(%)</label>
			<input class="form-control" type="number" name="descuento" id="descuento" min="0" max="100"
				value="${empty param.descuento ? '' : param.descuento}" required>
		</div>

		<div class="form-group">
			<label for="imagen">Imagen</label>
			<div class="input-group mb-3">
				<div class="custom-file">
					<input type="file" class="custom-file-input" name="imagen" id="imagen"> <label class="custom-file-label"
						for="imagen">Examinar...</label>
				</div>
			</div>
		</div>

		<hr class="mb-4">
		<button class="btn btn-primary" type="submit">Confirmar</button>
		<button class="btn btn-danger" type="button">Cancelar</button>

	</form>
</t:contenedor>