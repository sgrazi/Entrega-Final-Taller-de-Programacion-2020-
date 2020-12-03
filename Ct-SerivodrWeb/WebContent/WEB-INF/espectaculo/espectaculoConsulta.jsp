<%@page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="publicar.*"%>

<style>

	.borderleft {
			border-top: 1px solid white;
	}
		
	@media (min-width: 768px) {
		
		.borderleft {
			border-left: 1px solid white;
			border-top: 0px;
		}
		
	}
		
</style>

<t:contenedor>

	<div class="row">
	<!-- 		************************ -->
	<!-- 		DATOS					 -->
	<!-- 		************************ -->
		<div class="col-12 col-md-9">
			<div class="card mb-3" style="max-width: 100%; border:0px;" >
			
				<div class="row no-gutters">
					<div class="col-12 col-md-4">
						<img src="${ espectaculo.getImagen() }" class="card-image rounded p-1 border w-100" style="max-width: 100%;">
						<p class="text-muted small p-2">
							<c:if test="${not empty espectaculo.categorias}">
	
								<c:set var="pipe" value="" />
	
								<c:forEach var="categoria" items="${ espectaculo.categorias }">
	
									<c:out value="${ pipe }" /> ${ categoria }
	
									<c:set var="pipe" value="|" />
	
								</c:forEach>
	
							</c:if>
						
						</p>
					</div>
					
					<div class="col-md-7">
						
						<div class="card-body pt-0">
							<p class="card-title mb-1">
								<div class="d-flex justify-content-between">
									<h4>${ espectaculo.getNombre() } 
										<small class="font-italic" style="font-size:0.9rem;">
											<a href="#" class="badge badge-dark px-2 py-1">${ espectaculo.getPlataforma() }</a>
											<a href="${ espectaculo.getUrl() }" class="badge badge-dark px-2 py-1">URL</a> 
										</small>
									</h4>
									
									<c:choose>
										<c:when test="${ esEspectador }">
												<c:choose>
													<c:when test="${  esFavorito }">
													<div class="btn btn-outline-danger ml-2">
														<a class="text-danger"
															href="./fav?usr=${usuarioLog.getNickname()}&espUnFav=${ espectaculo.getNombre() }" title="Quitar de favoritos">
															<i class="fa fa-heart" style="font-size:1.2rem;"> 
																${ espectaculo.getCantfavoritos() }
															</i>
														</a>
													</div>
													</c:when>
													<c:otherwise>
													<div class="btn btn-outline-dark ml-2">
														<a class="text-dark"
															href="./unfav?usr=${usuarioLog.getNickname()}&espFav=${ espectaculo.getNombre() }" title="Marcar como favorito">
															<i class="fa fa-heart" style="font-size:1.2rem;"> 
																${ espectaculo.getCantfavoritos() }
															</i>
														</a>
													</div>
													</c:otherwise>
												</c:choose>
										</c:when>
										<c:otherwise>
											<div class="text-danger ml-2">
												<i class="fa fa-heart" style="font-size:1.8rem;"> 
													${ espectaculo.getCantfavoritos() }
												</i>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</p>
							<c:if test="${esEspectador && registrado}">
							<p class="card-subtitle">
								<div class="small"><t:valoracion /></div>
							</p>
							</c:if>
							
							<p class="card-text">
								<i class="far fa-calendar-alt"></i>
								<span class="text-muted pl-1 mr-3">
									<fmt:formatDate value="${espectaculo.getFechaDeRegistro().toGregorianCalendar().time}"
										pattern="dd/MM/yyyy" />
								</span>
								<i class="far fa-clock"></i>
								<span class="text-muted pl-1">${ espectaculo.getDuracion() } minutos</span>
							</p>
							<p class="card-text">
								<i class="fas fa-user"></i>
								<span class="text-muted">
									${ espectaculo.getCantMinEspectadores() } / ${ espectaculo.getCantMaxEspectadores() }
								</span>
							</p>
							
							<div class="card-text d-flex justify-content-between">
								<div class="h2 font-weight-bold text-accent">$${ espectaculo.getCosto() }</div>
								
								<c:if test="${ artistaPuedeFinalizar }">
									<div> 
										<form action="./espectaculo?action=FINALIZAR" method="POST" class="form-inline mb-0 flex-grow-1 d-flex mx-3" >
											<input name="espectaculo" class="form-control" type="text" hidden value="${ espectaculo.getNombre() }" />
											<button type="submit" class="btn btn-outline-danger">Finalizar</button>
										</form>
									</div>
								</c:if>
								
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="card-body pt-0">
							<label class="lead">Descripcion</label>
							<p class="card-text">
								${ espectaculo.descripcion }
							</p>
						</div>
					</div>
				</div>
				
				<c:if test="${not empty espectaculo.getVideoUrl()}">
		
					<div class="col-12 mt-2">
						<p class="lead">Video</p>
						<div class="embed-responsive embed-responsive-16by9">
							<iframe class="embed-responsive-item" src="${ espectaculo.getVideoUrl() }"></iframe>
						</div>
					</div>
		
				</c:if>
		
				<!-- ESTO ESTA WIP -->
			<div class="col-12 col-md-8 col-lg-5 mb-0 mt-4 mx-auto">
				<div class="bg-light border rounded">
				  <h5 class="mb-0 text-center text-muted">Valoraciones de Usuarios</h5>
				  <hr class="my-1">
				  <div class="d-flex flex-column-reverse px-2">
					<c:forEach var="val" items="${resumenVal}" varStatus="s">
					  <c:choose>
						<c:when test="${cantVals > 0}">
						  <c:set var="perc" value="${(val * 100) / cantVals}" />
						</c:when>
						<c:otherwise>
						  <c:set var="perc" value="${0}" />
						</c:otherwise>
					  </c:choose>
					  <div class="d-flex align-items-center">
						<span class="pr-1">${s.index + 1}:</span>
						<div class="progress flex-grow-1">
						  <div class="progress-bar" role="progressbar" style="width: ${perc}%" aria-valuenow="${perc}"
							aria-valuemin="0" aria-valuemax="100"></div>
						</div>
						<span class="pl-1 text-right" style="width: 60px;">${val}</span>
					  </div>
					</c:forEach>
					<span class="ml-3 text-muted">Promedio: ${espectaculo.getPromedioVal()}</span>
				  </div>
				  
				</div>
			  </div>
			</div>
		</div>
		
<!-- 		************************ -->
<!-- 		FUNCIONES Y PAQUETES -->
<!-- 		************************ -->
		<div class="col-12 col-md-3 px-5 borderleft borderTop">

			<div class="row">
				<div class="col-12 border-bottom mb-4 pb-4">
					<h4>Funciones</h4>
					<c:if test="${not empty espectaculo.funciones}">
						<ul class="nav flex-column">
							<c:forEach var="funcion" items="${ espectaculo.funciones }">
								<li class="nav-item my-1">
									<a class="nav-link"
										href="./consultaFuncion?nombreFuncion=${ funcion.getNombre() }">${
										funcion.getNombre() }</a>
								</li>

							</c:forEach>

						</ul>

					</c:if>

				</div>
				<div class="col-12">
					<h4>Paquetes</h4>
					<c:if test="${not empty espectaculo.paquetes}">
						<ul class="nav flex-column">

							<c:forEach var="paquete" items="${ espectaculo.paquetes }">
								<li class="nav-item my-1">
									<span class="nav-link d-sm-none">${ paquete }</span>
									<a class="d-none d-sm-block nav-link" href="./paquete?nombre=${ paquete }">${ paquete }</a>

								</li>
							</c:forEach>

						</ul>

					</c:if>

				</div>
			</div>

		</div>
	</div>
  	
  	
</t:contenedor>
