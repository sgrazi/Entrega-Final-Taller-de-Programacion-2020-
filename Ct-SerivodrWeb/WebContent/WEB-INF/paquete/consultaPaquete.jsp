<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="controladores.Paquete"%>
<%@page import="publicar.DtPaquete"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="dateFormat" value="${Paquete.dateFormatEs}" />

<t:contenedor>
<c:choose>
	<c:when test="${not empty paquete}">
		<div class="row">
			<div class="col-12 col-sm-9">
				<div class="row">
					<div class="col-12">
						<div class="row">
							<div class="col-7 col-md-8 d-flex col-img-paquete">
								<img src="${paquete.getImagen()}" class="img-fluid rounded p-1 border">
							</div>
							<div class="col-5 col-md-4 col-info-paquete">
								<div class="d-flex flex-column bd-highlight">
									<div class="px-2 py-1 py-sm-1 bd-highlight mb-1">
										<div class="h5 font-weight-normal text-muted mb-1 mb-md-2">Inicio</div>
										<i class="far fa-calendar-alt"></i> <span class="pl-1 mr-3">
											<c:out value="${dateFormat.format(paquete.getInicio().toGregorianCalendar().getTime())}" />
										</span>
									</div>
									<div class="px-2 py-1 py-sm-1 bd-highlight mb-0 mb-md-3">
										<div class="h5 font-weight-normal text-muted mb-1 mb-md-2">Fin</div>
										<i class="far fa-calendar-alt"></i> <span class="pl-1 mr-3">
											<c:out value="${dateFormat.format(paquete.getFin().toGregorianCalendar().getTime())}" />
										</span>
									</div>
									<div class="px-2 py-1 py-sm-1 bd-highlight mb-0 mb-md-5">
										<span class="h5 font-weight-normal text-muted">Descuento:
										</span> <span class="h3 font-weight-bold"> <c:out
												value="${Math.round(paquete.getDescuento() * 100)}" />%
										</span>
									</div>
									<a class="btn btn-sm  btn-outline-primary" href="./paquete">Listar paquetes</a>
								</div>
	
							</div>
						</div>
	
					</div>
					<div class="col-12 mt-3">
						<h3 class="mb-3">
							<c:out value="${paquete.getNombre()}" />
						</h3>
						<p class="text-muted small">
							<c:set var="pipe" value="" />
							<c:forEach var="categoria" items="${paquete.getCategorias()}">
								${pipe}${categoria}
								<c:set var="pipe" value=" | " />
							</c:forEach>
						</p>
						<p class="lead">
							<c:out value="${paquete.getDescripcion()}" />
						</p>
					</div>
				</div>
			</div>
			<hr class="col-12 d-sm-none">
			<div class="col-12 col-sm-3 bg-white border-left px-4 px-lg-5 mx-sm-auto">
	
				<div class="row">
					<div class="col-12">
					<h4>Espectaculos</h4>
					<ul class="nav flex-column">
						<c:forEach var="espectaculo" items="${paquete.getEspectaculos()}">
							<li class="nav-item my-1"><a class="nav-link"
								href="./espectaculo?value=${espectaculo}"> <span class="d-block">${espectaculo}</span>
							</a></li>
						</c:forEach>
						<!-- <img src="https://s1.eestatic.com/2019/08/07/cultura/musica/Muertes-Grupos_musicales-Musica-Musica_419719166_131785849_1706x960.jpg" class="rounded col-8 d-block" > -->
					</ul>
					</div>
				</div>
	
			</div>
		</div>
	</c:when>
	<c:when test="${not empty error}">
		<div class="d-flex align-items-baseline">
			<h4 class="mt-3 mr-5">${error}</h4>
			<a class="btn btn-primary" href="./paquete">Listar Paquetes</a>
		</div>
	</c:when>
</c:choose>
</t:contenedor>