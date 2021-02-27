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
				<a href="${pageContext.request.contextPath}/telefone?acao=novo&usuario_id=<c:out value='${usuario.id}'/>"><img
					class="icone_g"
					src="${pageContext.request.contextPath}/imagens/inserir-telefone.png">Novo
					telefone</a>
				<a href="${pageContext.request.contextPath}/usuario?acao=listagem"><img
					class="icone_g"
					src="${pageContext.request.contextPath}/imagens/listar-usuarios.png">Listagem
					de usuários</a>
			</h2>
			<div class="textoDestaque">Telefones de ${usuario.nome}</div>
		</div>

		
		<div class="conteudo">
			<table class="table table-bordered">
				<tr class="thead-dark">
					<th scope="col">ID</th>
					<th scope="col">DDD</th>
					<th scope="col">Número</th>
					<th scope="col">Tipo</th>
					<th scope="col">Ações</th>
				</tr>
		
				<c:forEach var="telefone" items="${telefoneList}">
					<tr class="thead-light">
						<td scope="row"><c:out value="${telefone.id}" /></td>
						<td><c:out value="${telefone.ddd}" /></td>
						<td><c:out value="${telefone.numero}" /></td>
						<td><c:out value="${telefone.tipo}" /></td>
						<td><a
							href="${pageContext.request.contextPath}/telefone?acao=editar&telefone_id=<c:out value='${telefone.id}'/>"><img
								class="icone_p"
								src="${pageContext.request.contextPath}/imagens/editar.png"></a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="${pageContext.request.contextPath}/telefone?acao=deletar&telefone_id=<c:out value='${telefone.id}'/>"><img
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
