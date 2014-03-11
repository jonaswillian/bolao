<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
</head>
<body>		
	<div id="principal" style="background:#ffffff; padding:10px 20px;">
	<p class="tituloPagina">JOGOS</p>
		Confira todos os jogos da Copa do Mundo FIFA 2014!<br />
		<g:each in="${datasEJogos}" var="dataJogo">
			<p class="data">${dataJogo.key.format('dd/MM')}</p>
			<g:each in="${dataJogo.value}" var="jogo">
				<g:formRemote url="[controller:'jogo', action: 'salvarPalpite']" name="frmJogo" update="msg${jogo.id}">
				<div style="padding:10px; width:90%;" id="${jogo.id}">
					<img src="${resource(dir: '/images/flags', file: jogo.time1.bandeira)}" align="absmiddle" />&nbsp; ${jogo.time1.nome}
					&nbsp;
					<input type="text" name="golsTime1" class="campo1" style="width:30px;" required maxlenght="2" />
					&nbsp;
					x
					&nbsp;
					<input type="text" name="golsTime2" class="campo1" style="width:30px;" required maxlenght="2" />
					&nbsp;
					${jogo.time2.nome}&nbsp;<img src="${resource(dir: '/images/flags', file: jogo.time2.bandeira)}" align="absmiddle" />
					
					<input type="hidden" value="${jogo.id}" name="idJogo"/>
					<input type="submit" value="Salvar Palpite" />
					<div id="msg${jogo.id}"></div>					
				</div>
				
				</g:formRemote>
			</g:each>
		</g:each>
	</div>
</body>
</html>




