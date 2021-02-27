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
				<a
					href="${pageContext.request.contextPath}/telefone?acao=listagem&usuario_id=<c:out value='${usuario.id}' />"><img
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
							onfocus="var temp_value=this.value; this.value=''; this.value=temp_value"
							required autofocus>

					</div>

					<div class="col-4 input-group mb-2">

						<div class="input-group-prepend">
							<span class="input-group-text" id="numeroTelefone">Número</span>
						</div>
						<input type="text" class="form-control"
							value="<c:out value='${telefone.numero}' />"
							onblur="mascaraFone(this)" onfocus="limpaFone(this)"
							placeholder="9999-9999 ou 9.9999-9999" name="numero"
							id="numeroTelefone" aria-label="Número"
							aria-describedby="numeroTelefone" minlenght="8" maxlength="11" required>
					</div>

					<div class="col-3 input-group mb-2">

						<div class="input-group-prepend">
							<span class="input-group-text" id="tipoTelefone">Tipo</span>
						</div>
						<input type="text" class="form-control"
							value="<c:out value='${telefone.tipo}' />"
							placeholder="Informe o tipo do telefone" name="tipo"
							id="tipoTelefone" aria-label="Tipo"
							aria-describedby="tipoTelefone" maxlength="30" required>
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

	<script>
		function limpaFone(telefone) {
			const foneAtual = telefone.value;
			const foneAjustado = foneAtual.replace(/\-/g, ''); // usando expressão regular

			telefone.value = foneAjustado;
		}
		
		function mascaraFone(telefone) {
			const foneAtual = telefone.value;
			const isCelular = foneAtual.length === 9;

			let foneAjustado;
			
			if (isCelular) {
				const parte1 = foneAtual.slice(0, 1);
				const parte2 = foneAtual.slice(1, 5);
				const parte3 = foneAtual.slice(5, 9);


				foneAjustado = parte1 + '.' + parte2 + '-' + parte3
			} else {
				const parte1 = foneAtual.slice(0, 4);
				const parte2 = foneAtual.slice(4, 8);

				foneAjustado = parte1 + '-' + parte2
			}
			
			telefone.value = foneAjustado;
		}
	</script>

	<script type="text/javascript" src="/js/desafiosefaz.js"></script>

	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

</body>
</html>
