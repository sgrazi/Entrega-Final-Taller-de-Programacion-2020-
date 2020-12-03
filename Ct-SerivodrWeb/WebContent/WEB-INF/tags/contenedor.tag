<%@ tag language="java" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<div class="container-fluid">
		<div class="row page-wrapper">
			<jsp:include page="/WEB-INF/template/sidebar.jsp" />
			<main class="col-12 col-md-10 px-2 py-4">
				<jsp:doBody />
			</main>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/template/scripts.jsp" />
    
</body>    
</html>