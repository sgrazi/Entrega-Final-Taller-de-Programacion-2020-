<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

	<head>
        <jsp:include page="/WEB-INF/template/head.jsp"/>
        <title>Alta Usuario</title>
	</head>
	
<body class="py-5">
    <form class="container col-lg-4 col-md-7 border rounded bg-light " action="./formAltaUsuario" method= "POST" enctype="multipart/form-data">
        <div class="text-center mb-3">
            <div class="d-flex flex-column pt-2" style="align-items: center;">
                <img class="mb-n4" src="svg/crown-solid.svg" alt="" width="72" height="72">
                <img src="svg/ticket-alt-solid.svg" alt="" width="72" height="72">
            </div>
            <h2>Registro de Usuario</h2>
        </div>

        <label>Tipo de Usuario</label>
        <div class="form-group">
            <div class="btn-group btn-group-toggle w-100" data-toggle="buttons">
                <label class="btn btn-info active">
                    <input type="radio" name="tipo-usuario" value="espectador" id="espectador" class="radio-tipo" checked> Espectador
                </label>
                <label class="btn btn-info">
                    <input type="radio" name="tipo-usuario" value="artista" id="artista" class="radio-tipo"> Artista
                </label>
            </div>
        </div>

        <div class="form-group">
            <label for="">Nickname</label>
            <input type="text" class="form-control" name="nicknameAlta" aria-describedby="helpId" placeholder="" required>
            <!-- <small id="helpId" class="form-text text-muted">Help text</small> -->
        </div>
        <div class="row">
            <div class="col-6">
                <div class="form-group">
                    <label for="">Nombre</label>
                    <input type="text" class="form-control" name="nombre" aria-describedby="helpId" placeholder="" required>
                    <!-- <small id="helpId" class="form-text text-muted">Help text</small> -->
                </div>
            </div>
            <div class="col-6">
                <div class="form-group">
                    <label for="">Apellido</label>
                    <input type="text" class="form-control" name="apellido" aria-describedby="helpId" placeholder="" required>
                    <!-- <small id="helpId" class="form-text text-muted">Help text</small> -->
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-6">
                <div class="form-group">
                    <label for="">Contraseña</label>
                    <input type="password" class="form-control" name="contrasena" placeholder="" required>
                </div>
            </div>
            <div class="col-6">
                <div class="form-group">
                    <label for="">Confirmar Contraseña</label>
                    <input type="password" class="form-control" name="confirmarContrasena" placeholder="" required>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="">Email</label>
            <input type="email" class="form-control" name="email" aria-describedby="emailHelpId" placeholder="" required>
            <!-- <small id="emailHelpId" class="form-text text-muted">Help text</small> -->
        </div>
        <div class="row">
            <div class="form-group col-6">
                <label for="">Fecha de nacimiento</label>
                <input type="date" class="form-control" name="fecha" aria-describedby="helpId" placeholder="" required>
                <!-- <small id="helpId" class="form-text text-muted">Help text</small> -->
            </div>
            <div class="col-6">
                <label for="inputGroupFile01">Imagen</label>
                <div class="input-group mb-3">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" name= "imagen" id="imagen"
                            aria-describedby="inputGroupFileAddon01">
                        <label class="custom-file-label" for="inputGroupFile01">Examinar...</label>
                    </div>
                </div>

            </div>
        </div>

        <div class="mb-4 d-none artista-fields">
            <hr />
            <div class="form-group">
                <label for="descripcion">Descripción</label>
                <textarea class="form-control artista-field" name="descripcion" id="descripcion" disabled
                    rows="3" required></textarea>
            </div>
            <div class="form-group">
                <label for="biografia">Biografía</label>
                <textarea class="form-control artista-field" name="biografia" id="biografia" disabled
                    rows="3"></textarea>
            </div>
            <div class="form-group">
                <label for="url">Sitio web</label>
                <input type="url" class="form-control artista-field" name="url" id="url" disabled>
            </div>
        </div>
		<button type="submit" class="btn btn-primary btn-block my-2">Confirmar</button>
        <p class="mt-2"><small class="text-muted">Ya tienes cuenta? <a href="./login">Iniciar sesión</a></small></p>

		<c:set var="hidden" value="" />
					<c:if test="${ empty errores }">
						<c:set var="hidden" value="hidden" />
					</c:if>
	
        <label for="error" class="${ hidden }" style="color: red; ">${ errores } </label>
		

		<jsp:include page="/WEB-INF/template/scripts.jsp"/>
        <script>
            $(".custom-file-input").on("change", function () {
                var fileName = $(this).val().split("\\").pop() || "Examinar...";
                $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
            });

            $('.radio-tipo').on("change", (function () {
                const show = this.id === 'artista';
                $('.artista-fields').toggleClass('d-none', !show);
                $('.artista-field').prop("disabled", !show);
            }));
        </script>
    </form>
</body>

</html>