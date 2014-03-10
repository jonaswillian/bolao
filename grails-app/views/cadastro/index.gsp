<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<!-- Inclusão do Jquery -->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" ></script>
<!-- Inclusão do Jquery Validate -->
<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.validate/1.6/jquery.validate.js" ></script>	
</head>
	
<body>
	
	<div id="principal" style="background:#ffffff; padding:10px 20px;">
		<p class="tituloPagina">CADASTRE-SE</p>
		<p>
			Preencha os dados abaixo e cadastre-se!
		</p>		
				
		<g:formRemote name="frmUsuario" url="[controller:'cadastro', action:'salvar']" update="status">
		<p>
			<strong>SEU NOME:</strong><br >
			<input type="text" name="nome" class="campo1" style="width:700px; height:30px;" required autofocus /><br />	
		</p>
		
		<p>
			<strong>SEU E-MAIL:</strong><br >
			<input type="email" name="email" class="campo1" style="width:700px; height:30px;" required /><br />
		</p>
		<p>
			<strong>SEU LOGIN:</strong><br >
			<input type="text" name="login" class="campo1" style="width:700px; height:30px;" required  /><br />	
		</p>
		
		<p>
			<strong>SENHA:</strong><br >
			<input type="password" name="senha" class="campo1" style="width:400px; height:30px;" required /><br />
		</p>
		
		<p>
			<strong>CONFIRME SUA SENHA:</strong><br >
			<input type="password" name="senhaConfirmada" class="campo1" style="width:400px; height:30px;" required /><br />
		</p>
		
		<input type="submit" value="Cadastrar" name="Cadastrar" class="bot" /><br /><br />
		</g:formRemote>
		<br/>
		
		<g:if test="${flash.error}">
			<div id="status">${flash.error}</div>
		</g:if>
	
		<g:if test="${flash.message}">
			<div id="status">${flash.message}</div>
		</g:if>				
		
	</div>
			
		<div class="clear"></div>
	
</body>
</html>
