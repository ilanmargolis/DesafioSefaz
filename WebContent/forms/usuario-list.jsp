<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/desafiosefaz.css" />" />
<title>Desafio SEFAZ</title>
</head>
<body>
	<div class="container-fluid">

		<div class="cabecalho" align="center">
			<div class="logomarca"></div>
			<h2 class="menu">
				<a href="${pageContext.request.contextPath}/usuario?acao=novo"><img
					class="icone_g"
					src="${pageContext.request.contextPath}/imagens/adicionar.png">Novo
					usuario</a>
			</h2>
		</div>

		<div class="conteudo">
			<table class="table table-bordered">
				<tr class="thead-dark">
					<th scope="col">ID</th>
					<th scope="col">Nome</th>
					<th scope="col">Email</th>
					<th scope="col">Ações</th>
				</tr>
				<c:forEach var="usuario" items="${usuarios}">
					<tr class="thead-light">
						<td scope="row"><c:out value="${usuario.id}" /></td>
						<td><c:out value="${usuario.nome}" /></td>
						<td><c:out value="${usuario.email}" /></td>
						<td><a
							href="${pageContext.request.contextPath}/conta?acao=listagem&usuario_id=<c:out value='${usuario.id}'/>"><img
								class="icone_m"
								src="${pageContext.request.contextPath}/imagens/fone.png"></a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="${pageContext.request.contextPath}/usuario?acao=editar&usuario_id=<c:out value='${usuario.id}'/>"><img
								class="icone_p"
								src="${pageContext.request.contextPath}/imagens/editar.png"></a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="${pageContext.request.contextPath}/usuario?acao=deletar&usuario_id=<c:out value='${usuario.id}'/>"><img
								class="icone_p"
								src="${pageContext.request.contextPath}/imagens/deletar.png"
								alt="Deletar"></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div class="rodape">
			<p>&copy2021 Desafio SEFAZ - Desenvolvido por Ilan Margolis</p>
		</div>
	</div>

	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

</body>
</html>
