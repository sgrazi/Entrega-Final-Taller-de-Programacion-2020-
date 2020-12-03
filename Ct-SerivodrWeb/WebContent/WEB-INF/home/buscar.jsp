<%@page import="java.text.SimpleDateFormat"%>
<%@page import="controladores.ListarPorPlataformaCategoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:contenedor>
	<h2>Resultado de búsqueda: ${nom}</h2>

	<div class="row">

		<c:if test="${not empty espectaculos}">
			<!-- ESPECTACULOS-->
			<c:set var="ordenAlf" value="${ orden == null || orden.equals('alfabetico')}" />

			<div class="col-12 border-bottom mb-4">
				<div class="row no-gutters align-items-center">
					<p class="h4 mt-4 mb-3 mr-5 font-weight-normal">Espectaculos:</p>
					<div class="h4 mt-4 mb-3">
						<form action="./buscar">
							<input type="hidden" name="buscar" value="${nom}">
							<select	class="custom-select my-1 mr-sm-2" name="ordenar" onchange="this.form.submit()">
								<option value="alfabetico" ${ordenAlf ? "selected" : ""}>Orden Alfabético</option>
								<option value="fecha" ${ordenAlf ? "" : "selected"}>Ordenar por Año</option>
							</select>
						</form>
					</div>
				</div>
				<ul class="nav flex-column">
					<c:forEach var="espectaculo" items="${espectaculos}">
						<div class="card bg-light mb-3 p-0">
							<div class="row no-gutters">
								<div class="col-md-8">
									<div class="card-body">
										<h4 class="card-title">
											<a href="./espectaculo?value=${ espectaculo.getNombre() }">${ espectaculo.getNombre() }</a>
										</h4>
										<p class="card-text">${ espectaculo.getDescripcion() }</p>
										<p class="card-text">
											<small class="text-bold">Artista: <a
												href="./perfilUsuario?nickPerfil=">${ espectaculo.getArtista() }</a></small>
											<br /> <small class="text-muted">Duración: ${ espectaculo.getDuracion() }
												| Costo: $${espectaculo.getCosto()}</small>
										</p>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</ul>
			</div>
		</c:if>

		<c:if test="${not empty paquetes}">
			<div class="col-12 mb-4 d-none d-md-block">
				<p class="h4 mt-4 mb-3 font-weight-normal w-100">Paquetes:</p>
				<div class="row row-cols-1 row-cols-md-4 mb-4">
					<c:forEach var="paquete" items="${paquetes}">
						<div class="col mb-4">
							<div class="card">
								<a href="./paquete?nombre=${paquete.getNombre()}"> <img
									src="${paquete.getImagen()}" class="card-img-top" alt="${paquete.getNombre()}">
									<div class="card-body d-block p-3">
										<h5 class="card-title">${paquete.getNombre()}</h5>
									</div>
									<div class="d-block text-right pr-3 pb-3">
										<span class="badge badge-secondary "
											style="font-size: 1.5rem;">${Math.round(paquete.getDescuento() * 100)}%</span>
									</div>
								</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</c:if>
		<c:if test="${ empty espectaculos && empty paquetes }">
			<h4 class="ml-4 text-muted">
				No se encontraron resultados para "<b>${nom}</b>"
			</h4>
		</c:if>
	</div>
</t:contenedor>