<%@page import="java.text.SimpleDateFormat"%>
<%@page import="controladores.ListarPorPlataformaCategoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:contenedor>


	<h2>Categorias: <span class="text-muted"> ${ nom }</span></h2>
	
	 <div class="row">
          <!-- ESPECTACULOS-->
          <div class="col-12 border-bottom mb-4">
            <h3 class="text-muted mb-3">Espectaculos</h3>
				<ul class="nav flex-column">
					<c:forEach var="espectaculo" items="${espectaculos}">
						   <div class="card bg-light mb-3 p-0">
				              <div class="row no-gutters">
				                <div class="col-md-8">
				                  <div class="card-body">
				                    <h4 class="card-title"><a href="./espectaculo?value=${ espectaculo.getNombre() }">${ espectaculo.getNombre() }</a></h4>
				                    <p class="card-text">
				                      ${ espectaculo.getDescripcion() }
				                    </p>
				                    <p class="card-text">
				                      <small class="text-bold">Artista: <a href="./perfilUsuario?nickPerfil=">${ espectaculo.getArtista() }</a></small>
				                      <br />
				                      <small class="text-muted">DuraciÃ³n:  ${ espectaculo.getDuracion() } | Costo: ${ espectaculo.getCosto() }</small>
				                    </p>
				                  </div>
				                </div>
				              </div>
            				</div>
					
					</c:forEach>
				
				</ul>
          </div>
         </div> 




</t:contenedor>
