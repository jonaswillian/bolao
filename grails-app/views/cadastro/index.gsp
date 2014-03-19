<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<!-- Inclusão do Jquery -->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" ></script>
<!-- Inclusão do Jquery Validate -->
<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.validate/1.6/jquery.validate.js" ></script>	

<script type="text/javascript">
        $(document).ready(function(){
        $('#frmUsuario').validate({
            rules:{               
                senhaConfirmada:{
                   equalTo: "#senha"
                    },                
                  },
                 messages:{                
                   senhaConfirmada:{
                	  required: "Os campos de senha devem ser iguais.",
                      equalTo: "As senhas não conferem!"
                             },                
                          }             
                     });
                  });
	    </script>
</head>
	
<body>
	
	<div id="principal" style="background:#ffffff; padding:10px 20px;">
		<p class="tituloPagina">CADASTRE-SE</p>
		<p>
			Preencha os dados abaixo e cadastre-se!
		</p>		
				
		<g:formRemote name="frmUsuario" url="[controller:'cadastro', action:'salvar']" update="msg" before="if (jQuery('#frmUsuario').valid()) {" after="}">
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
			<input type="password" name="senha" id="senha" class="campo1" style="width:400px; height:30px;" required /><br />
		</p>
		
		<p>
			<strong>CONFIRME SUA SENHA:</strong><br >
			<input type="password" name="senhaConfirmada" id="senhaConfirmada" class="campo1" style="width:400px; height:30px;" required /><br />
		</p>
		
		<input type="submit" value="Cadastrar" name="Cadastrar" class="bot" /><br /><br />
		
		<div id="msg"></div>
		</g:formRemote>
		<br/>			
			
	</div>
			
		<div class="clear"></div>
	
</body>
</html>
