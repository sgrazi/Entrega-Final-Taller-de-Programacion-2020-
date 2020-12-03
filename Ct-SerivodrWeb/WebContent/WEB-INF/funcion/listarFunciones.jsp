<%@page import="java.text.SimpleDateFormat"%>
<%@page import="controladores.ListarPorPlataformaCategoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:contenedor>

	<div class="row">

		<c:if test="${not empty funciones}">

			<div class="col-12 border-bottom mb-4">
				<div class="row no-gutters align-items-center">
					<p class="h4 mt-4 mb-3 mr-5 font-weight-normal">Funciones:</p>
				</div>
				<ul class="nav flex-column">
					<c:forEach var="funciones" items="${funciones}">
						<div class="card bg-light mb-3 p-0">
							<div class="row no-gutters">
								<div class="col-md-8">
									<div class="card-body">
										<h4 class="card-title">
											<a href="./consultaFuncion?nombreFuncion=${ funciones.getNombre() }">${ funciones.getNombre() }</a>
										</h4>
										<p class="card-text">
											<small class="text-bold">Espectaculo: <a
												href="./espectaculo?value=${funciones.getNombreEsp()}">${ funciones.getNombreEsp() }</a></small>
											<br /> <small class="text-muted">${funciones.getFecha().getDay()}-${funciones.getFecha().getMonth()}-${funciones.getFecha().getYear()}
												| ${funciones.getHora().getHoras()}:${funciones.getHora().getMinutos()}</small>
										</p>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</ul>
			</div>
		</c:if>

	
	</div>
</t:contenedor>