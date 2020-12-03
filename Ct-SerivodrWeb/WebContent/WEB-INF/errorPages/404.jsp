<%@page contentType="text/html" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<style type="text/css">
* {
	transition: all 0.6s;
}

html {
	height: 100%;
}

body {
	font-family: 'Lato', sans-serif;
	color: black;
	margin: 0;
}

#main {
	display: table;
	width: 100%;
	height: 100vh;
	text-align: center;
}

.fof {
	display: table-cell;
	vertical-align: middle;
}

.fof h1 {
	font-size: 50px;
	display: inline-block;
	padding-right: 12px;
	animation: type .5s alternate infinite;
}

@
keyframes type {
	from {box-shadow: inset -3px 0px 0px #888;
}

to {
	box-shadow: inset -3px 0px 0px transparent;
}

}
.center {
	margin: auto;
	width: 60%;
	padding: 10px;
	position: absolute;
	left: 50%;
	top: 40%;
	transform: translate(-50%, -50%);
}

@media ( max-width : 575px) {
	.center {
		width: 90%;
	}
}
</style>


<HTML>
<HEAD>
	<!--  para el responsive en moviles -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<TITLE>Página no encontrada</TITLE>
</HEAD>
<BODY id="error_page">
	  <div id="main" class="container">
	    <div class="center">
	      <div class="fof center">
	        <h1>404</h1>
	        <h2>HMMMM, POR ACA NO ERA</h2>
	        <p>Esta pagina no existe :)
	        </p>
	        <a href="./home"><button  type="button" class="btn btn-outline-warning">HOME</button></a>
	      </div>
	    </div>
	  </div>
</BODY>
</HTML>
