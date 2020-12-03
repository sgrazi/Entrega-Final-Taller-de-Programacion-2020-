<%@page import="java.text.SimpleDateFormat"%>
<%@page import="controladores.ListarPorPlataformaCategoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:contenedor>

	<h2>Usuarios:</h2>
	
 	 <div class="row">
          

      <div class="col-12 border-bottom mb-4">
		<ul class="nav flex-column">
          <c:forEach var="usuario" items="${ usuarios }">
          	<div class="card bg-light mb-3 p-0">
	              <div class="row no-gutters">
	                <div class="col-md-8">
	                  <div class="card-body">
	                    <h4 class="card-title"><a href="./perfilUsuario?nickPerfil=${ usuario.getNickname() }">${ usuario.getNickname() }</a></h4>
	                    <p class="card-text">
	                      ${ usuario.getCorreoElectronico() }
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
