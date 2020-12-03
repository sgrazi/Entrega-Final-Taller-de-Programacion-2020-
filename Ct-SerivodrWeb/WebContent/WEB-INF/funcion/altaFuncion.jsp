<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var expanded = false;
    function showCheckboxes() {
        var checkboxes = document.getElementById("checkboxes");
        if (!expanded) {
            checkboxes.style.display = "block";
            expanded = true;
        } else {
            checkboxes.style.display = "none";
            expanded = false;
        }
    }
</script>

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

   <!-- <title>Alta Funcion de Espectaculo | CoronaTickets</title> --> 

        <h2>Alta Funcion de Espectaculo: </h2>
        
        <hr />
		<t:errorMessage/>
		
        <!-- MAIN -->
                <form id="alta" class="form-horizontal" action="./altaFuncion" method="POST" enctype="multipart/form-data">
					
					<div class="form-group col-md-5" style="padding:10px">
		                <label class="control-label col-sm-2" for="espectaculo">Espectaculo</label>
		                <br>
		                <select id="espectaculo" name="espectaculo" class="form-control" required>
		                    <option hidden="" value="">Seleccionar</option>
		                                        
		                    <c:set var="selected" value=""/>
		                    
								<c:forEach var="EspectaculosAceptados" items="${ EspectaculosAceptados }">
									
							        <option value="${ EspectaculosAceptados }" <c:out value="${ selected }"/> >${ EspectaculosAceptados }</option>
							        
						  	    </c:forEach>
		                </select>
		            </div>

                    <div class="form-group col-md-5" style="padding:10px">

                        <label class="control-label col-sm-2" for="nombre">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresar Nombre" value="${nombreFuncion}"
                            required>
                    </div>

                    <div class="form-group col-md-5" style="padding:10px">
                        <label class="control-label col-sm-2" for="fecha">Fecha:</label>
                        <input type="date" class="form-control" id="fecha" name="fecha" value="${fecha}" required>
                    </div>

                    <div class="form-group col-md-5" style="padding:10px">
                        <label class="control-label col-sm-2" for="hora">Hora:</label>
                        <input type="time" class="form-control" id="hora" name="hora" value="${hora}" required>
                    </div>

                    <div class="form-group col-md-5" style="padding:10px">

                        <label class="control-label col-sm-5" for="artistas">Artistas Invitados:</label>
                        
				            <div class="multiselect">
				                <div class="selectBox" onclick="showCheckboxes()">
				                    <select>
				                        <option hidden="">Seleccione los artistas invitados.</option>
				                    </select>
				                    <div class="overSelect"></div>
				                </div>
				                <div id="checkboxes">
				                    <c:forEach var="artistas" items="${artistas}">
				                    	<label>
                        					<input type="checkbox" name="seleccionado" value="${artistas}"/> ${artistas}</label>
						            </c:forEach>
				                </div>
				            </div>
                    </div>

					<div class="form-group col-md-5" style="padding:10px">
					  <label class="control-label col-sm-5" for="nombre">Imagen</label>
					  <div class="custom-file">
					    <input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01" name="inputGroupFile01">
					    <label class="custom-file-label" for="inputGroupFile01">Seleccionar</label>
					  </div>
				    </div>

                    <div class="form-group col-6">
                        <label for=""></label>
                        <button type="submit" class="btn btn-primary btn-block my-2">Crear Funcion</button>
                    </div>

                </form>

        <!-- FIN MAIN -->
    
</t:contenedor>