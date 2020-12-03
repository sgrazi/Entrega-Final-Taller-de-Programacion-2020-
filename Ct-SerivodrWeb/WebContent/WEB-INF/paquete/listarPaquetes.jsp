<%@page import="java.net.URLEncoder" %>
<%@page import="publicar.DtPaquete"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="logueado" value="${ es_espectador }" />

<t:contenedor>
	<div class="d-flex align-items-baseline justify-content-between pr-5">
		<h2>Paquetes de Espectáculos</h2>
		<c:if test="${logueado && !empty paquetes}">
			<a class="btn btn-primary" href="./compraPaquete">Comprar Paquetes</a>
		</c:if>
	</div>

	<hr />
	<c:forEach var="paquete" items="${paquetes}">
		<div class="card bg-light mb-3 p-0">
			<div class="row no-gutters">
				<div class="d-flex" style="width: 170px;">
					<img
						src="${paquete.getImagen()}"
						class="card-img" alt="${paquete.getNombre()}">
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<h4 class="card-title">
							<a href="./paquete?nombre=${paquete.getNombre()}">${paquete.getNombre()}</a>
						</h4>
						<p class="card-text">${paquete.getDescripcion()}</p>
						<p class="card-text">
							<span class="badge badge-warning">${Math.round(paquete.getDescuento() * 100)}%</span>
						</p>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<c:if test="${ empty paquetes }">
		<h4 class="pl-3 text-muted">Aún no hay paquetes creados</h4>
	</c:if>
</t:contenedor>