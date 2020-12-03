<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<jsp:include page="/WEB-INF/template/head.jsp" />
</head>

<body>
	<div class="container pt-4">
		<h1>Registros de acceso:</h1>
		<table class="table table-bordered table-sm">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">IP</th>
					<th scope="col">URL</th>
					<th scope="col">BROWSER</th>
					<th scope="col">OS</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${registros}" var="registro" varStatus="s">
				<tr>
					<th scope="row">${s.index + 1}</th>
					<c:forEach items="${registro}" var="item" varStatus="si">
						<td>
						<c:choose>
							<c:when test="${si.index == 1}">
								<a href="${item}">${item}</a>
							</c:when>
							<c:otherwise>
								${item}
							</c:otherwise>
						</c:choose>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

	<jsp:include page="/WEB-INF/template/scripts.jsp" />
</body>
</html>