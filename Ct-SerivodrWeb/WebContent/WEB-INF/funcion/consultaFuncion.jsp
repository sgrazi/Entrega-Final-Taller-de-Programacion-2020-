<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:contenedor>

    <div style="width: 100%;">
		<div class="d-none d-md-block" style="width: 100%;">
		  
	      <!-- CENTRO DE DATOS -->
	      <div  class="col-12" >
				<c:if test="${finalizada}">
			        <div class="alert alert-warning" style="width: 100%;">
			       	  <p style="text-align:center">La funcion que estas visualizando ya ha terminado</p>
				    </div>
				</c:if>
	          <div style="width: 100%;">
				<c:if test="${sorteado}">
		          <div >
		          	<div class="alert alert-success" role="alert">
					  Sorteado exitosamente!
					  
					  Felicitaciones a:
					  <c:forEach var="ganador" items="${ganadores}">
                      	<option value="${ganador}">${ganador}</option>
                      </c:forEach>
					</div>
		          </div>
		        </c:if> 
		        
	            <div class="row">
	            
	              <div class="col-3">
	                <img src="${imagen}" class="card-image rounded p-1 border w-100" style="max-width: 100%;">
	              </div>
				<div class="col-9">
	                <div class="d-flex flex-column">
	                  <div style="padding-top: 10px;">
	                    <h3>
	                      ${nombreFuncion}
	
	                      <a href="${url}">
	                        <button type="button" class="btn btn-sm ml-3" style="color: #1da1f2;">
	                          <i class="fab fa-twitter"></i>
	                          ${nombrePlataforma}
	                        </button>
	                      </a>
	                    </h3>
	                  </div>
	                  <div class="p-2 bd-highlight align-items-start mb-1">
	                    <i class="far fa-calendar-alt"></i>
	                    <span class="text-muted pl-1 mr-3">${fecha}</span>
	                    <i class="far fa-clock"></i>
	                    <span class="text-muted pl-1">${hora}</span>
	                  </div>
	
	                  <div style="padding-left: 8px;">
	                      <p>Invitados: ${artistas}</p>
	                  </div>
	              <div style="padding-left: 8px;">
		              <c:if test="${noRegistrado}">
					        <form action="./registroAFuncion" method="GET">
					          <input type="hidden" name="nombreFuncion" value="${nombreFuncion}" />
					          <button class="btn btn-success my-2 my-sm-0" name="botonReg" type="submit">Registrarse</button>
					        </form>
					  </c:if>
					  <c:if test="${finalizada && artistaOrganizador && !sorteado}">
					        <form action="./consultaFuncion" method="POST">
					          <input type="hidden" name="nombreFuncion" value="${nombreFuncion}"/>
					          <button class="btn btn-success my-2 my-sm-0" name="botonSorteo" type="submit">Sortear</button>
					        </form>
					  </c:if>
		          </div>
	                </div>
	
	              </div>
	            </div>
	
	          </div>
	
	      </div>
	      
	      
	      
	      
      </div>
      
      <!-- MOVIL -->
      <div class="d-md-none">
	      <c:if test="${finalizada}">
		       <div class="alert alert-warning" style="width: 100%;">
		       	 <p style="text-align:center">Funcion finalizada.</p>
			   </div>
		  </c:if> 
	      <section class="jumbotron text-center">
		    <div class="container">
		      <h1>${nombreFuncion}</h1>
		      <img src="${imagen}" class="img-fluid rounded p-1 border">
		    </div>
		  </section>
	      <div class="container">
	      <div class="row">
		      <div class="col-md-4">
		        <h2>Información</h2>
		        <div class="p-2 bd-highlight align-items-start mb-1">
	              <i class="far fa-calendar-alt"></i>
	              <span class="text-muted pl-1 mr-3">${fecha}</span>
	              <i class="far fa-clock"></i>
	              <span class="text-muted pl-1">${hora}</span>
	            </div>
		        <p>Invitados: ${artistas}</p>
		      </div>
		    </div>
		
		  </div>
      </div>
      

	</div>
	
</t:contenedor>