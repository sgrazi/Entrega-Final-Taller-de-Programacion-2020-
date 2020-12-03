<%@page import="java.text.SimpleDateFormat"%>
<%@page import="controladores.ListarPorPlataformaCategoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:contenedor>

 	<h2>Plataforma: <span class="text-muted"> ${ plataforma.getNombre() }</span></h2>
	 <p class="text-muted mb-2">
		${ plataforma.getDescripcion() }
        </p>
        
        
        <div class="row">
          <!-- ESPECTACULOS-->
          <div class="col-12 border-bottom mb-4">
            <h3 class="text-muted mb-3">EspectÃ¡culos</h3>
				<ul class="nav flex-column">
					<c:forEach var="espectaculo" items="${plataforma.getEspectaculos()}">
						   <div class="card bg-light mb-3 p-0">
				              <div class="row no-gutters">
				                <div class="col-md-8">
				                  <div class="card-body">
				                    <h4 class="card-title"><a href="./espectaculo?value=${ espectaculo.getNombre() }">${ espectaculo.getNombre() }</a></h4>
				                    <p class="card-text">
				                      ${ espectaculo.getDescripcion() }
				                    </p>
				                    <p class="card-text">
				                      <small class="text-bold">Artista: <a href="./perfilUsuario?nickPerfil=${ espectaculo.getArtista() }">${ espectaculo.getArtista() }</a></small>
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