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
				<a href="${pageContext.request.contextPath}/usuario?acao=listagem"><img
					class="icone_g"
					src="${pageContext.request.contextPath}/imagens/listar-telefones.png">Listagem
					de telefones</a> <a
					href="${pageContext.request.contextPath}/usuario?acao=listagem"><img
					class="icone_g"
					src="${pageContext.request.contextPath}/imagens/listar-usuarios.png">Listagem
					de usuários</a>
			</h2>

			<h2 class="tipoTela">
				<c:if test="${telefone == null}">
            		Novo telefone
            	</c:if>
				<c:if test="${telefone != null}">
            			Editar cadastro
            	</c:if>
			</h2>
		</div>

		<div class="conteudo">
			<c:if test="${telefone == null}">
				<form
					action="${pageContext.request.contextPath}/telefone?acao=inserir"
					method="post">
			</c:if>
			<c:if test="${telefone != null}">
				<form
					action="${pageContext.request.contextPath}/telefone?acao=alterar"
					method="post">
			</c:if>

			<div class="align-items-center mt-4">
				<c:if test="${telefone != null}">
					<input type="hidden" name="telefone_id"
						value="<c:out value='${telefone.id}' />" />
					<input type="hidden" name="usuario_id"
						value="<c:out value='${telefone.usuario.id}' />" />
				</c:if>
				<c:if test="${telefone == null}">
					<input type="hidden" name="usuario_id"
						value="<c:out value='${usuario.id}' />" />
				</c:if>

				<div class="form-row">
					<div class="col-2 input-group mb-2">
						<div class="input-group-prepend">
							<span class="input-group-text" id="dddTelefone">DDD</span>
						</div>
						<input type="text" class="form-control"
							value="<c:out value='${telefone.ddd}' />"
							placeholder="Código DDD" name="ddd" id="dddTelefone"
							aria-label="DDD" aria-describedby="dddTelefone" maxlength="2"
							<c:if test="${telefone == null}">autofocus</c:if>>
					</div>

					<div class="col-3 input-group mb-2">

						<div class="input-group-prepend">
							<span class="input-group-text" id="numeroTelefone">Número</span>
						</div>
						<input type="tel" class="form-control"
							value="<c:out value='${telefone.numero}' />"
							placeholder="Número do telefone" name="numero"
							id="numeroTelefone" aria-label="Número"
							aria-describedby="numeroTelefone" maxlength="10">
					</div>

					<div class="col-3 input-group mb-2">

						<div class="input-group-prepend">
							<span class="input-group-text" id="tipoTelefone">Tipo</span>
						</div>
						<input type="text" class="form-control"
							value="<c:out value='${telefone.tipo}' />"
							placeholder="Informe o tipo do telefone" name="tipo"
							id="tipoTelefone" aria-label="Tipo"
							aria-describedby="tipoTelefone" maxlength="30">
					</div>
				</div>
			</div>
			<div class="col mt-2">
				<button type="submit" class="btn btn-primary" value="Salvar">Enviar</button>
			</div>
		</div>
		</form>
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
