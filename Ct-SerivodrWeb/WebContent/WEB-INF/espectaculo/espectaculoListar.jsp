<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:contenedor>

		<c:if test = "${empty tipo}">
         	<h2> ${tipo} <span class="text-muted">${tipoNombre}</span></h2>
      	</c:if>
        
        
        <p class="text-muted mb-2">
        Funcionalidad de la red social Instagram, con la que los usuarios pueden transmitir vídeos en vivo.
        </p>
        <a href="https://www.instagram.com/liveoficial" class="btn btn-sm btn-outline-primary">Ir al sitio</a>
        <hr />
		
		<t:errorMessage/>
		
        <!-- MAIN -->
        <div class="row">
          <!-- IZQUIERDA -->
          <div class="col-12 border-bottom mb-4">
            
			<c:if test = "${empty espectaculos}">
				<h1>NO HAY ESPECTACULOS</h1>
			</c:if>
			
			<c:if test = "${not empty espectaculos}">
			
				<c:forEach var="espectaculo" items="${ espectaculos }">
				 
			  		<div class="card bg-light mb-3 p-0">
			            <div class="row no-gutters">
			               <div class="d-flex img-espectaculo">
			                 <img
			                   src="https://s1.eestatic.com/2019/08/07/cultura/musica/Muertes-Grupos_musicales-Musica-Musica_419719166_131785849_1706x960.jpg"
			                   class="card-img " alt="...">
			               </div>
			               <div class="col-md-8">
			                  <div class="card-body">
			                     <h4 class="card-title"><a href="./espectaculo?value=${ espectaculo.nombre }">${ espectaculo.nombre }</a></h4>
			                     <p class="card-text">
			                        ${ espectaculo.descripcion }
			                     </p>
			                     <p class="card-text">
			                        <small class="text-bold">Artista: <a href="./perfilUsuario?nickPerfil=${ espectaculo.artista }">${ espectaculo.artista }</a></small>
			                        <br />
			                        <small class="text-muted">Duración: ${ espectaculo.duracion } min | Costo: $ ${ espectaculo.costo } </small>
			                     </p>
			                  </div>
			               </div>
		              	</div>
		            </div>
		            
			  	</c:forEach>
			  	 
			</c:if>
            
        </div>
      
</t:contenedor>