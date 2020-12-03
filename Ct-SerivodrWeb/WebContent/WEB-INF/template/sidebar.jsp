<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.*"%>
<%@page import="modelo.Menu"%>
<%@page import="modelo.subMenus"%>

<div class="col-2 bg-light pt-3 d-none d-md-block">
	
	<%
	//TOMO EL MENU QUE SE CARGO ANTES EN EL SERVLET
	
	Menu.generarMenu(request);
	
	Menu menu = (Menu)request.getAttribute("menu"); 
	
	if ( menu != null) {
		List<String> menus = menu.getItems();
		
		for(String item: menus){

	%>		   
	   	   <h4><%= item %></h4>		   
	   
	       <% 
		   //OBTENGO SUBMENUS
		   subMenus sMenus = menu.getSubMenu(item);
		   
	       if ( !(sMenus == null) ) {
		       if (!sMenus.getItems().isEmpty()) {
		       %>
		           <ul class="nav flex-column">	   
		       <% 
		       }
		       		       
		       for(Map.Entry<String, String> sb: sMenus.getItems().entrySet()){
		       %>
				   <li class="nav-item">
		               <a href="./<%= sb.getValue() %>" class="btn btn-light btn-block text-left" role="button" aria-pressed="true">
		               <%= sb.getKey() %>
		               </a>
		           </li>	          	   		 
		       <% 
		       } 
		       
		       if (!sMenus.getItems().isEmpty()) {
		       %>
		           </ul>	   
		       <% 
		       } //CIERRO FOR-EACH
	       } 

		}

	}
	%>
		    
    
</div>