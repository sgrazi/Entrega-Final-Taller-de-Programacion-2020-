<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Login - CoronaTicketsUy</title>

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        html,
        body {
            height: 100%;
        }

        body {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-align: center;
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }

        .form-signin .checkbox {
            font-weight: 400;
        }

        .form-signin .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }

        .form-signin .form-control:focus {
            z-index: 2;
        }

        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
</head>

<body class="text-center">
    <div class="container">
        <div class="row">
            <div class="col-12">

            
                <form class="form-signin" method="POST" action="./login">
                	<a href="./home">
                    	<div class="d-flex flex-column" style="align-items: center;">
                    		<img class="mb-n4" src="svg/crown-solid.svg" alt="" width="72" height="72">
                        	<img src="svg/ticket-alt-solid.svg" alt="" width="72" height="72">
                    	</div>
                    </a>
                    
                    <h1 class="h3 mb-3 font-weight-normal">Inicio de sesion</h1>

                    <label for="inputEmail" class="sr-only">Email / Nickname </label>
                    <input type="text" name="usuario" id="inputEmail" class="form-control" placeholder="Email / Nickname" required autofocus >
                    <label for="inputPassword" class="sr-only">Contraseña</label>
                    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Contraseña" required >
                    
					<div class="mb-3 custom-control custom-checkbox float-left">
			            <input type="checkbox" class="custom-control-input" id="recordarme" name="recordarme"> 
			            <label class="custom-control-label" for="recordarme">Recordarme</label>
					</div>
					
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
                    
					<c:set var="hidden" value="" />
					<c:if test="${ empty errores }">
						<c:set var="hidden" value="hidden" />
					</c:if>
	
                    <label for="error" class="${ hidden }" style="color: red; ">${ errores } </label>
                    
                    <p class="mt-3 mb-3 text-muted d-none d-sm-block">
                        <a href="./altaUsuario?action=registrarse">Registrarse</a>
                    </p>
                    <p class="text-muted">&copy; Grupo 31 - 2020</p>
                </form>
            </div>
        </div>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
</body>

</html>
