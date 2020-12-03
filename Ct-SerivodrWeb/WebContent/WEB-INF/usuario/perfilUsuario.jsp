<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Iterator" %>
<%@page import="modelo.*"%>
<%@page import="publicar.*"%>
<%@page import="controladores.Login"%>
<%@page import="controladores.UsuarioServ"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<!-- EMPIEZA EL HEAD -->

    <head>

        <jsp:include page="/WEB-INF/template/head.jsp"/>
        <title>Perfil Usuario</title>
    </head>
    <!-- TERMINA EL HEAD -->
    
    <body>
    	<!-- EMPIEZA EL HEADER -->
      <jsp:include page="/WEB-INF/template/header.jsp"/>
      <!-- TERMINA EL HEADER -->
		<% DtUsuario dtusr = (DtUsuario) request.getAttribute("usuarioPerfil");
		
		
		//Fabrica f = Fabrica.getInstance();
		//IControladorUsuario cu = f.getIControladorUsuario();
		//ManejadorUsuario mu = ManejadorUsuario.getInstance();
		//Usuario pruebaLog = mu.obtenerUsuarioPorNickName("watson");
		DtUsuario dtusrlog;
		try{
			dtusrlog = (DtUsuario) request.getAttribute("usuarioLogeado");
		}catch(Exception e){
			dtusrlog = null;
		}
		boolean esPerfilPropio = (dtusrlog != null) && (dtusr.getCorreoElectronico().equals(dtusrlog.getCorreoElectronico()));
		
		List<DtUsuario> seguidores = (List<DtUsuario>) request.getAttribute("seguidores");
		List<DtUsuario> seguidos = (List<DtUsuario>) request.getAttribute("seguidos");
		List<DtEspectaculo> espectaculos = (List<DtEspectaculo>) request.getAttribute("espectaculos");
		
		List<DtUsuario> seguidosLogeado = (List<DtUsuario>) request.getAttribute("seguidosLogeado");
		
		if(!esPerfilPropio && (dtusrlog != null)) {
			Iterator<DtUsuario> itr = seguidosLogeado.iterator();
            boolean contain = false;
           
            while (itr.hasNext() && !contain) {
                DtUsuario temp = itr.next();
                if (temp.getNickname().equals(dtusr.getNickname())) contain = true;
            }
            
			if(!contain) {%>
      <!--esto lo hace propio-->
      <div class="py-3 text-right col-md">
        <a class="btn btn-outline-success" href="./follow?usrlog=<%= dtusrlog.getNickname() %>&usrFollow=<%= dtusr.getNickname() %>">Seguir</a>
      </div>
      
		<% } else { %> <!--usrlog sigue a usr-->
		<div class="py-3 text-right col-md">
        <a class="btn btn-outline-success" href="./unfollow?usrlog=<%= dtusrlog.getNickname() %>&usrUnfollow=<%= dtusr.getNickname() %>">Dejar de seguir</a>
      	</div>
		<% }} else if ((dtusrlog != null) && esPerfilPropio) { %>
		
		<div class="py-3 text-right col-md">
        <a class="btn btn-outline-success" href="./modificarDatos?action=editar">Editar perfil</a>
      	</div>
      
      <% } %>

      <div class="container mb-5">   <!--el mb-5 lo pongo para que el pie de la pagina no quede muy abajo-->

        <!--informacion general-->
        <div class="py-5 text-center">
          <img class="d-block mx-auto mb-4" src="<%= dtusr.getImagen() %>" alt="" width="72" height="72">
          <h2><%= dtusr.getNickname() %></h2>
          <p class="lead"><%= dtusr.getCorreoElectronico() %></p>
          <div class="row">
            <div class="col-md-6 mb-3">
              <p>SEGUIDORES</p>
              <h1><%= seguidores.size() %></h1>
            </div>
            <div class="col-md-6 mb-3">
              <p>SEGUIDOS</p>
              <h1><%= seguidos.size() %></h1>
            </div>
          </div> 
        </div>
        <!--hasta aca-->

        <div class="row"> 
          <ul class="nav nav-pills col-md-12" id="myTab" role="tablist">
            <li class="nav-item  text-center active">
              <a class="nav-link active" href="#general-tab"data-toggle="tab">GENERAL</a>
            </li>
            <li class="nav-item  text-center <% if(dtusr instanceof DtArtista) {%> d-none <%}%>">  <!--le pongo d-none para que desaparezca-->
              <a class="nav-link" href="#registros-tab" data-toggle="tab">REGISTROS</a>
            </li>
            <li class="nav-item  text-center <% if(dtusr instanceof DtArtista) {%> d-none <%}%>">
              <a class="nav-link " href="#paquetes-tab" data-toggle="tab" >PAQUETES</a>
            </li>
            <li class="nav-item  text-center">
              <a class="nav-link" href="#seguidores-tab" data-toggle="tab" >SEGUIDORES</a>
            </li>
            <li class="nav-item  text-center">
              <a class="nav-link" href="#seguidos-tab" data-toggle="tab"  >SEGUIDOS</a>
            </li>
            <li class="nav-item  text-center <% if(!((dtusrlog != null) && esPerfilPropio) ||  dtusr instanceof DtArtista) {%> d-none <%}%>">
              <a class="nav-link " href="#favoritos-tab" data-toggle="tab" >FAVORITOS</a>
            </li>
            <li class="nav-item  text-center <% if(!((dtusrlog != null) && esPerfilPropio) ||  dtusr instanceof DtArtista) {%> d-none <%}%>">
              <a class="nav-link " href="#premios-tab" data-toggle="tab" >PREMIOS</a>
            </li>

            <!--si no es artista pongo "disabled" al lado de nav-link-->
            <li class="nav-item  text-center <% if(dtusr instanceof DtEspectador) {%> d-none <%}%>">
              <a class="nav-link" href="#espectaculos-tab" data-toggle="tab"  >ESPECTACULOS</a>
            </li>
            <li class="nav-item  text-center <% if(!esPerfilPropio || (dtusr instanceof DtEspectador)) {%> d-none <%}%>">
              <a class="nav-link" href="#espectaculosfin-tab" data-toggle="tab"  >ESPECTACULOS FINALIZADOS</a>
            </li>
            <!--hasta aca-->
          </ul>
          
          <div class="tab-content col-12" id="myTabContent">

            <!--general-->
            <div class="tab-pane active" id="general-tab">
              <hr>
              <p>Nickname:</p>
              <p class="lead"><%= dtusr.getNickname() %></p>
              <p>Correo electronico:</p>
              <p class="lead"><%= dtusr.getCorreoElectronico() %></p>
              <p>Perfil:</p>
              <p class="lead">Artista</p>
              <p>Nombre:</p>
              <p class="lead"><%= dtusr.getNombre() %></p>
              <p>Apellido:</p>
              <p class="lead"><%= dtusr.getApellido() %></p>
              <% if(dtusr instanceof DtArtista) {%>
              	<p>Descripcion:</p>
              	<p class="lead"><%= ((DtArtista) dtusr).getDescripcion() %></p>
              	<p>Biografia:</p>
              	<p class="lead"><%= ((DtArtista) dtusr).getBiografia() %></p>
              	<p>Sitio Web:</p>
              	<p class="lead"><%= ((DtArtista) dtusr).getUrl() %></p>
              <%}%>
              <p>Fecha de nacimiento:</p>
              <p class="lead"><%= 
						new SimpleDateFormat("dd/MM/yyyy").format(dtusr.getFechaDeNacimiento().toGregorianCalendar().getTime())
					%></p>
            </div>

            <!--registros-->
            <% if(dtusr instanceof DtEspectador) {%>
            <div class="tab-pane" id="registros-tab">
              <hr>
              
            <c:forEach var="f" items="${funciones}">
              <div class="my-5">
                <a class="nav-link" href="./consultaFuncion?nombreFuncion=${ f.getNombre() }" style="font-size:20px;padding-left: 0px;">${ f.getNombre() }</a>
                <div class="row">
                    <img src="${ f.getImagen() }" class="rounded col-3" alt="...">
                    <p class="col-8">
                        ${ f.getNombre() }
                    </p>				<!-- revisar si dejar solo nombre -->
                </div>  
              </div>
              </c:forEach>
            </div>
            
            
            <!--paquetes-->
            <div class="tab-pane" id="paquetes-tab">
              <hr>
              
              <c:forEach var="p" items="${paquetes}">
              <div class="my-5">
                <a class="nav-link" href="./paquete?nombre=${ p.getNombre() }" style="font-size:20px;padding-left: 0px;">${ p.getNombre() }</a>
                <div class="row">
                    <img src="${p.getImagen() }" class="rounded col-3" alt="...">
                    <p class="col-8">
                        ${ p.getDescripcion() }
                    </p>
                </div>  
            </div>
            </c:forEach>
            </div>
            
            <!--FAVORITOS-->
            <% if(esPerfilPropio) { %>
            <div class="tab-pane" id="favoritos-tab">
              <hr>

            
            <c:forEach var="fav" items="${favoritos}">
			<div class="my-5">
                <a class="nav-link" href="./espectaculo?value=${fav.getNombre() }" style="font-size:20px;padding-left: 0px;">${ fav.getNombre() }</a>
                <div class="row">
                    <img src="${ fav.getImagen() }" class="rounded col-3" alt="...">
                    <div class="col-8">
                      <p>
                        ${ fav.getDescripcion() }
                      </p>
                      
							<h4>
                        	<i class="fa fa-heart" style="font-size:1rem; color:red;">  ${ fav.getCantfavoritos() }</i>
                         	</h4>	
                         	
                         	
              		</div>
                  </div> 
              </div>
			
			</c:forEach>
            </div>
            	<%}%>
            	
            <!--PREMIOS-->
            <% if(esPerfilPropio) { %>
            <div class="tab-pane" id="premios-tab">
            <hr>
            
            <c:if test="${ not empty premios }">
            
	            <table class="table">
	            <thead>
			    	<tr>
			    		<th scope="col">Fecha</th>
				      	<th scope="col">Premio</th>
				      	<th scope="col">Espectaculo</th>
				      	<th scope="col">Funcion</th>
				      	<th scope="col">Imprimir</th>
			    	</tr>
			  	</thead>
			  	<tbody>
		            <c:forEach var="premio" items="${ premios }">
		            <c:set var="fecha" value="${ UsuarioServ.dateFormat2.format( premio.getFechaSorteo().toGregorianCalendar().time) }" />
		            <c:set var="link" value="fecha=${ fecha }&nom=${ usuarioLogeado.getNombre() }&ape=${ usuarioLogeado.getApellido() }
		            						&funcion=${ premio.getFun() }&espectaculo=${ premio.getEsp() }&premio=${ premio.getDescripcion() }" />
		            <tr>
				      	<td><c:out value="${ fecha }" /></td>
				      	<td >${ premio.getDescripcion() }</td>
				      	<td>${ premio.getEsp() }</td>
				      	<td>${ premio.getFun() }</td>
				      	<td>
				      		<form action="./PDF" method="POST" target="_blank">
								<input name="fecha" class="form-control" type="text" hidden value="${ fecha }" />
								<input name="nom" class="form-control" type="text" hidden value="${ usuarioLogeado.getNombre() }" />
								<input name="ape" class="form-control" type="text" hidden value="${ usuarioLogeado.getApellido() }" />
								<input name="funcion" class="form-control" type="text" hidden value="${ premio.getFun() }" />
								<input name="espectaculo" class="form-control" type="text" hidden value="${ premio.getEsp() }" />
								<input name="premio" class="form-control" type="text" hidden value="${ premio.getDescripcion() }" />
								<button type="submit" class="btn btn-outline-dark btn-sm"><i class="fas fa-print"></i></button>
							</form>
				      	</td>
				    </tr>
										
					</c:forEach>
				</tbody>
				</table>
				
			</c:if>
            </div>
            	<%}%>	
            <%}%>
            
            <!--seguidores-->
            <div class="tab-pane" id="seguidores-tab">
              <hr>
              <div class="row">
               
               <c:forEach var="segres" items="${seguidores}">
                <div class="col-12">
                <div class="row">
                  <div class="col-4">
                    <img class="d-block mx-auto mb-4" src="${ segres.getImagen() }" alt="" width="72" height="72">
                  </div>
                  <div class="col-6">
                    <a class="label lead" href="./usuarios?nickPerfil=${ segres.getNickname() }" type="submit">${ segres.getNickname() }</a>
                  </div>
                  </div>
                </div>
                </c:forEach>
              </div> 
            </div>
            
            <!--seguidos-->
            <div class="tab-pane" id="seguidos-tab">
              <hr>
              <div class="row">
				
				<c:forEach var="temp" items="${seguidos}">
                <div class="row col-12">
                  <div class="col-4">
                    <img class="d-block mx-auto mb-4" src="${ temp.getImagen() }" alt="" width="72" height="72">
                  </div>
                  <div class="col-6">
                    <a class="label lead" href="./usuarios?nickPerfil=${ temp.getNickname() }" type="submit">${ temp.getNickname() }</a>
                  </div>
                </div>
				</c:forEach>
              </div>
            </div>
			
			<% if(dtusr instanceof DtArtista) {%>
            <!--espectaculos-->
            <div class="tab-pane" id="espectaculos-tab">
              <hr>

            <%if (esPerfilPropio) {%>
            <c:forEach var="esp" items="${espectaculos}">
			<div class="my-5">
                <a class="nav-link" href="./espectaculo?value=${esp.getNombre() }" style="font-size:20px;padding-left: 0px;">${ esp.getNombre() }</a>
                <div class="row">
                    <img src="${ esp.getImagen() }" class="rounded col-3" alt="...">
                    <div class="col-8">
                      <p>
                        ${ esp.getDescripcion() }
                      </p>
							<h4>
                        	<span class="badge badge-warning">${esp.getEstado() }</span>
                         	</h4>	
                         	
              		</div>
                  </div> 
              </div>
			
			</c:forEach>
			
			<%} else { %>
			
			<c:forEach var="esp" items="${espectaculosAceptados}">
			<div class="my-5">
                <a class="nav-link" href="./espectaculo?value=${esp.getNombre() }" style="font-size:20px;padding-left: 0px;">${ esp.getNombre() }</a>
                <div class="row">
                    <img src="${ esp.getImagen() }" class="rounded col-3" alt="...">
                    <div class="col-8">
                      <p>
                        ${ esp.getDescripcion() }
                      </p>
              		</div>
                  </div> 
              </div>
			
			</c:forEach>
			<%} %>
            </div>
            
            
            <!--espectaculos finalizados-->
            <% if(esPerfilPropio) { %>
            <div class="tab-pane" id="espectaculosfin-tab">
              <hr>

            
            <c:forEach var="esp" items="${espectaculos}">

            <c:if test="${ esp.getEstado().equals('FINALIZADO') }">
			<div class="my-5">
                <a class="nav-link" href="./espectaculo?value=${esp.getNombre() }" style="font-size:20px;padding-left: 0px;">${ esp.getNombre() }</a>
                <div class="row">
                    <img src="${ esp.getImagen() }" class="rounded col-3" alt="...">
                    <div class="col-8">
                      <p>
                        ${ esp.getDescripcion() }
                      </p>
							<h4>
                        	<span class="badge badge-warning">${esp.getEstado() }</span>
                         	</h4>	
  	
              		</div>
                  </div> 
              </div>
			</c:if>
			</c:forEach>
			</div>
           	 <%}%>
            <%}%>
          </div>
          <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        </div>  
    	</div>
  </body>
</html>