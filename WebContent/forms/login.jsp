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
		</div>

		<div class="conteudo">
			<div class="login">
				<form action="${pageContext.request.contextPath}/login?acao=logar"
					method="post">
					<div class="form-group">
						<label for="fromEmail">Endereço de email</label> <input
							type="email" class="form-control" id="fromEmail" name="email"
							placeholder=" email @exemplo.com"
							required autofocus>
					</div>
					<div class="form-group">
						<label for="formPassword">Senha</label> <input type="password"
							class="form-control" id="formPassword" name="senha"
							placeholder="Senha" required>
					</div>

					<div class="form-check">
						<input type="checkbox" class="form-check-input"
							id="dropdownCheck2"> <label class="form-check-label"
							for="dropdownCheck2"> Lembrar de mim </label>
					</div>
					<div class="container mt-3">
						<div class="row">
							<div class="col text-center">
								<button type="submit" class="btn btn-primary">Entrar</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>

		<div class="rodape">
			<p>&copy2021 Agenda de contatos - Desenvolvido por Ilan Margolis</p>
		</div>

	</div>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

</body>
</html>