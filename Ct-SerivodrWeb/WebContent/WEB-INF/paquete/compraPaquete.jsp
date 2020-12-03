<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="controladores.Paquete"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="dateFormat" value="${Paquete.dateFormatEs}" />

<t:contenedor>
	<form action="./compraPaquete" method="post" class="col-6" autocomplete="off">
		<t:errorMessage />
		<div class="form-group">
			<label for="paquete">Paquete</label>
			<select name="paquete" class="custom-select" id="select-paquete" required>
				<c:forEach var="paquete" items="${paquetes}" varStatus="s">
					<option class="opcion" id="p${s.index}" value="${paquete.getNombre()}">${paquete.getNombre()}</option>
				</c:forEach>
			</select>
		</div>
		
		<div class="border rounded p-2">
			<c:forEach var="paquete" items="${paquetes}" varStatus="s">
				<div id="paq-p${s.index}" class="${s.index != 0 ? 'd-none' : ''} paq-info row">
					<div class="col-6">
						<p><c:out value="${paquete.getNombre()}" /></p>
						<p class="m-0"><c:out value="${paquete.getDescripcion()}" /></p>
					</div>
					<div class="col-6 border-left">
						<p>	Inicio:
							<c:out value="${dateFormat.format(paquete.getInicio().toGregorianCalendar().getTime())}" /><br>
							Fin:
							<c:out value="${dateFormat.format(paquete.getFin().toGregorianCalendar().getTime())}" />
						</p>
						<p>Descuento: <c:out value="${Math.round(paquete.getDescuento() * 100)}%" /></p>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<hr class="mb-4">
		
		<button class="btn btn-primary" type="submit">Confirmar</button>
		<button class="btn btn-danger" type="button">Cancelar</button>
	</form>
	
</t:contenedor>
<script>
	$('#select-paquete').change(function(){
	    const paquete = $(this).children("option:selected").attr("id");
	    $('.paq-info').addClass('d-none');
	    $('#paq-' + paquete).removeClass('d-none');
	})
</script>