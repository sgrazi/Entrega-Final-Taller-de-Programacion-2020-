<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

    function showMe(box) {
        var chboxs = document.getElementsByName("c1");
        var vis = "none";
        var costo = document.getElementById("labelCostoOrig").value;
        for (var i = 0; i < chboxs.length; i++) {
            if (chboxs[i].checked) {
                vis = "block";
            	costo = "0";
            	break;
            }
        }
        document.getElementById("labelCosto").innerHTML = costo;
        document.getElementById(box).style.display = vis;
    }
</script>

<t:contenedor>
    <div class="container-fluid">
        <div class="row">
			<input id="labelCostoOrig" name="labelCostoOrig" type="hidden" value="${costo}">
            <div class="container">
                <div class="py-5 text-center">
                    <h2 id="titulo">Registrarse a ${nombreFuncion}</h2>
                    <hr />
					<t:errorMessage/>
                </div>
                <form name="compra" method=POST action="./registroAFuncion">
                    <div class="custom-control custom-checkbox" style="display: flex; justify-content: flex-end">
                        <input type="checkbox" class="custom-control-input" id="c1" name="c1" onclick="showMe('div1')">
                        <label class="custom-control-label" for="c1">Usando canjes de registros previos. </label>
                    </div>
                    <hr>
                    <div class="row">
                        <div id="div1" class="col-md-4 order-md-2 mb-4" style="float: right; display:none">
                            <h4 class="d-flex justify-content-between align-items-center mb-3">
                                <span class="text-muted">Canje de Registros Previos</span>
                            </h4>
                            <ul class="list-group">
                                <li class="list-group-item justify-content-between lh-condensed">
                                    <div>
                                        <label for="registro1">Registro 1</label>
                                        <select id="registro1" name="registro1" class="form-control">
                                            <option hidden="">Elegir...</option>
                                            <c:forEach var="Registro" items="${RegistrosDelUsuario}">
                                                <option value="${Registro}">${Registro}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </li>
                                <li class="list-group-item justify-content-between lh-condensed">
                                    <div>
                                        <label for="registro2">Registro 2</label>
                                        <select id="registro2" name="registro2" class="form-control">
                                            <option hidden="">Elegir...</option>
                                            <c:forEach var="Registro" items="${RegistrosDelUsuario}">
                                                <option value="${Registro}">${Registro}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </li>
                                <li class="list-group-item justify-content-between lh-condensed">
                                    <div>
                                        <label for="registro3">Registro 3</label>
                                        <select id="registro3" name="registro3" class="form-control">
                                            <option hidden="">Elegir...</option>
                                            <c:forEach var="Registro" items="${RegistrosDelUsuario}">
                                                <option value="${Registro}">${Registro}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </li>
                            </ul>
                            <hr>
                        </div>
                        <div class="col-md-8 order-md-1">
	                        <h4 class="mb-3"></h4>
	                        <div>
	                            <img id="imagenFuncion" src=${imagen} class="img-fluid rounded p-1 border">
	                        </div>
	                        <h4 class="mb-3"></h4>
	                        <div>
	                            <div>
	                                <div>
	                                    <h3 id="nombreFuncion">${nombreFuncion}</h3>
	                                </div>
	                                <div id="fechaHoraFuncion" class="p-2 bd-highlight align-items-start mb-1">
	                                    <i class="far fa-calendar-alt"></i>
	                                    <span class="text-muted pl-1 mr-3">${fecha}</span>
	                                    <i class="far fa-clock"></i>
	                                    <span class="text-muted pl-1">${hora}</span>
	                                </div>
	
	                                <div id="artistasFuncion" class="p-2 bd-highlight mt-2">
	                                    <h4>Invitados:</h4>
	                                    <ul class="list-group list-group-flush">
	                                        <li class="list-group-item">${artistas}</li>
	                                    </ul>
	                                </div>
	                            </div>
	                        </div>
	                        <hr>
	                        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
	                            <div id="costoFuncion" class="col-md-6 mb-3">
	                                <h4 class="text-muted"><label id="labelCosto" for="costoFuncion">${costo}</label></h4>
	                            </div>
	
	                            <div class="col">
	                                <button type="submit" name="nombreFuncion" value="${nombreFuncion}" class="btn btn-success btn-lg btn-block">Comprar
	                                    Registro</button>
	                            </div>
	                        </div>
                    	</div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</t:contenedor>
