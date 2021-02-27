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
					src="${pageContext.request.contextPath}/imagens/listar-usuarios.png">Listagem
					de usuários</a>
			</h2>

			<h2 class="tipoTela">
				<c:if test="${usuario != null}">
            			Editar cadastro
            		</c:if>
				<c:if test="${usuario == null}">
            			Novo usuário
            		</c:if>
			</h2>
		</div>
		<div class="conteudo">
			<c:if test="${usuario != null}">
				<form
					action="${pageContext.request.contextPath}/usuario?acao=alterar"
					method="post">
			</c:if>
			<c:if test="${usuario == null}">
				<form
					action="${pageContext.request.contextPath}/usuario?acao=inserir"
					method="post">
			</c:if>
			<div class="align-items-center mt-4">
				<c:if test="${usuario != null}">
					<input type="hidden" name="usuario_id"
						value="<c:out value='${usuario.id}' />" />
					<input type="hidden" name="senha"
						value="<c:out value='${usuario.senha}' />" />
				</c:if>
				
				<div class="form-row">
					<div class="col-9 input-group mb-2">
						<div class="input-group-prepend">
							<span class="input-group-text" id="nomeUsuario">Nome do
								usuário</span>
						</div>
						<input type="text" class="form-control"
							value="<c:out value='${usuario.nome}' />"
							placeholder="Digite o nome do usuário" name="nome"
							aria-label="Usuário" aria-describedby="nomeUsuario"
							onfocus="var temp_value=this.value; this.value=''; this.value=temp_value"
							autofocus required>
					</div>
				</div>

				<div class="form-row">
					<div class="col-6 input-group mb-2">

						<div class="input-group-prepend">
							<span class="input-group-text" id="emailUsuario">E-mail</span>
						</div>
						<input type="email" class="form-control"
							value="<c:out value='${usuario.email}' />"
							placeholder="Digite o e-mail do usuário" name="email"
							id="emailUsuario" aria-label="Usuário"
							aria-describedby="emailUsuario" required>
					</div>

					<c:if test="${usuario == null}">
						<div class="col-4 input-group mb-2">

							<div class="input-group-prepend">
								<span class="input-group-text" id="senhaUsuario">Senha</span>
							</div>
							<input type="password" class="form-control"
								value="<c:out value='${usuario.senha}' />"
								placeholder="Digite a senha do usuário" name="senha"
								id="senhaUsuario" aria-label="Senha"
								aria-describedby="senhaUsuario" maxlength="15" required>
						</div>
					</c:if>
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
