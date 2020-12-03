<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    div.multiselect {
        width: 300px;
    }

    div.selectBox {
        position: relative;
    }

    div.selectBox select {
        width: 100%;
    }

    div.overSelect {
        position: absolute;
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
    }

    #checkboxes {
        display: none;
        border: 1px #dadada solid;
    }

    #checkboxes label {
        display: block;
    }

    #checkboxes label:hover {
        background-color: #1e90ff;
    }
</style>

<t:contenedor>
	
        <h3>Alta Espectaculo</h3>
        <hr />
		        		
		<t:errorMessage/>
		<t:okMessage/>
		
		<!-- MAIN -->
        
        <form class="form-row" action="./espectaculo" method="POST" enctype="multipart/form-data">
              
              <div class="form-group col-md-5">
                <label class="control-label" for="nombre" >Nombre</label>
                <input type="text" class="form-control" id="nombre" placeholder="Ingresar Nombre" name="nombre" value="${ nombre }" required>
              </div>
              
              <div class="form-group col-md-5">
                <label class="control-label" for="nombre">Plataforma</label>
                <br>
                <select id="plataforma" name="plataforma" class="form-control" required>
                    <option hidden="" value="">Seleccionar</option>
                                        
                    <c:set var="selected" value=""/>
                    
                    <c:if test="${not empty plataformas }">
						<c:forEach var="plat" items="${ plataformas }">
					    	
<%-- 					    	<c:if test="${ not empty plataforma } )"> --%>
						    	
<%-- 						    	<c:if test="${ plat eq plataforma } )"> --%>
<%-- 									<c:set var="selected" value="selected"/>	 --%>
<%-- 								</c:if> --%>
								    
<%-- 							</c:if> --%>
							
					        <option value="${ plat }" <c:out value="${ selected }"/> >${ plat }</option>
					        
				  	    </c:forEach>
			  	    </c:if>
                </select>
              </div>

			  <div class="form-group col-md-10">
                <label class="control-label" for="nombre">Descripcion</label>
                <input type="text" class="form-control" id="descripcion" placeholder="Ingresar Descripcion" name="descripcion" value="${ descripcion }">
              </div>
              
              <div class="form-group col-md-5">
                <label class="control-label" for="nombre">URL</label>
                <input type="text" class="form-control" id="url" placeholder="Ingresar URL del Espectaculo" name="url" value="${ url }">
              </div>
              
              <div class="form-group col-md-5">
                <label class="control-label" for="nombre">URL de video</label>
                <input type="text" class="form-control" id="video" placeholder="Ingresar URL del video" name="video" value="${ video }">
              </div>
              
              <div class="form-group col-md-5">
				  <label class="control-label" for="nombre">Imagen</label>
				  <div class="custom-file">
				    <input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01" name="inputGroupFile01">
				    <label class="custom-file-label" for="inputGroupFile01">Seleccionar</label>
				  </div>
			  </div>
			  
              <div class="form-group col-12">
	              <label class="control-label" for="nombre">Categorias</label>
	              <br>
	                
				  <div class="multiselect mb-2">
		                <div class="selectBox" onclick="showCheckboxes()">
		                    <select class="form-control" required>
		                        <option hidden="">Seleccionar</option>
		                    </select>
		                    <div class="overSelect"></div>
		                </div>
		                <div id="checkboxes">
		                	<c:if test="${not empty categorias }">
			                    <c:forEach var="categoria" items="${categorias}">
			                    	<label>
			                    		<input type="checkbox" name="categorias" value="${categoria}"/> ${categoria} 
			                    	</label>
					            </c:forEach>
					        </c:if>
		                </div>
		          </div>
			  </div>
			  	
              <div class="form-group col-md-2">
                <label class="control-label" for="nombre">Costo</label>
                <input type="number" class="form-control" id="costo" placeholder="Ingresar costo" name="costo" value="${ costo }" required min=1>
              </div>

              <div class="form-group col-md-2">
                <label class="control-label" for="nombre">Duracion</label>
                <input type="number" class="form-control" id="duracion" placeholder="Ingresar duracion (en minutos)" name="duracion" value="${ costo }" required min=1>
              </div>

              <div class="form-group col-md-2 offset-md-1">
                <label class="control-label" for="nombre">Capacidad minima</label>
                <input type="number" class="form-control" id="minimo" placeholder="Ingresar capacidad minima" name="minimo" value="${ minimo }" required min=1>
              </div>

              <div class="form-group col-md-2">
                <label class="control-label" for="nombre">Capacidad maxima</label>
                <input type="number" class="form-control" id="maximo" placeholder="Capacidad Maxima" name="maximo" value="${ maximo }" required min=1>
              </div>
		      
			  <div class="form-group col-md-5">
                <label class="control-label" for="nombre">Premio del sorteo</label>
                <input type="text" class="form-control" id="premio" placeholder="Ingresar descripcion del premio" name="premio" value="${ premio }">
              </div>
              
		      <div class="form-group col-md-2">
                <label class="control-label" for="nombre">Cantidad por funcion</label>
                <input type="number" class="form-control" id="cantSorteos" placeholder="Cantidad de sorteos" name="cantSorteos" value="${ cantSorteos }" min=0>
              </div>
              		
              <div class="form-group col-12">
                <div class="col-sm-offset-5 ">
                  <button type="submit" class="btn btn-primary">Aceptar</button>
                </div>
              </div>
            </form>

      
      
</t:contenedor>