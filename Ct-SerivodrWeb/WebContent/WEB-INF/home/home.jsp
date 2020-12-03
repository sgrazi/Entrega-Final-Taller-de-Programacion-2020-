<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="publicar.DtUsuario"%>

<t:contenedor>

        <p class="h1">CoronaTickets</p>
        
        <p class="small text-muted">Bienvenido! Disfruta del espectaculo que mas te guste <i class="far fa-smile-beam"></i></p>
        <hr />

        <!-- ******************************
        ******** ESPECTACULOS ********
        ****************************** -->
        
        <p class="h4 mt-4 mb-3 font-weight-normal">Espectaculos</p>
        <div class="row row-cols-1 row-cols-md-4 mb-4">
           <c:forEach var="espectaculo" items="${espectaculos}">
            <div class="col mb-4">
                <div class="card">                
						<div class="py-auto card-header bg-transparent d-flex justify-content-between">
                       		<em>${ espectaculo.getPlataforma() }</em>
							
                       		<c:choose>
                            	<c:when test="${ esEspectador }">
	                            	
	                            	<c:choose>
                                        <c:when test="${  favNombres.contains( espectaculo.getNombre() ) }">
                                        	<a href="./fav?usr=${usuarioLog.getNickname()}&espUnFav=${ espectaculo.getNombre() }" title="Quitar de favoritos">
	                            				<i class="fa fa-heart" style="color:red;"></i>
	                            			</a>	
	                            		</c:when>
	                            		<c:otherwise>
	                            			<a href="./unfav?usr=${usuarioLog.getNickname()}&espFav=${ espectaculo.getNombre() }" title="Marcar como favorito"> 
	                            				<i class="far fa-heart" style="color:red;"></i>
	                            			</a>
	                            		</c:otherwise>
	                            		
	                            	</c:choose>

                            	</c:when>
                            	
                            </c:choose>
                            
                        </div>
                        <div class="card-body p-3">
                        		
	                        <a href="./espectaculo?value=${espectaculo.getNombre()}" class="text-decoration-none">
		                        <div class="card-title h4 text-dark pt-2">${espectaculo.getNombre()}</div>
								<div class="card-subtitle text-muted mb-4" style="font-size: 0.8rem;">
									<t:valoracion promedio="${espectaculo.getPromedioVal()}" />
								</div>
	                        </a>
	                        
                           	<div class="card-text mb-2">
                                 <i class="far fa-clock"></i>
                                 <em>${espectaculo.getDuracion()} minutos</em>
                           	</div>
                           	<div class="card-text">
	                        	<i class="far fa-heart text-danger"> </i> ${ espectaculo.getCantfavoritos() } espectadores lo han marcado como favorito
	                        </div>
	                        
                            <div class="text-right mt-5">
                                <span style="font-size: 1.6rem; color: black">$${espectaculo.getCosto()}</span>
                            </div>
							
                        </div>
                    
                </div>
            </div>
            </c:forEach>
        </div>
        
        <!-- ******************************
        ******** PAQUETES ********
        ****************************** -->
        <div class="d-none d-sm-block">
	        <hr />

	        <p class="h4 mt-4 mb-3 font-weight-normal">Paquetes</p>
	        <div class="row row-cols-1 row-cols-md-4 mb-4">
	        <c:forEach var="paquete" items="${paquetes}">
	          <div class="col mb-4">
	              <div class="card">
	                  <a href="./paquete?nombre=${paquete.getNombre()}">
	                      <img src="${paquete.getImagen()}" class="card-img-top" alt="${paquete.getNombre()}">
	                      <div class="card-body d-block p-3">
	                          <h5 class="card-title">${paquete.getNombre()}</h5>
	                      </div>
	                      <div class="d-block text-right pr-3 pb-3">
	                          <span class="badge badge-secondary " style="font-size: 1.5rem;">${Math.round(paquete.getDescuento() * 100)}%</span>
	                      </div>
	                  </a>
	              </div>
	          </div>
	         </c:forEach>    
	        </div>
        </div>
      
</t:contenedor>