<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
</head>
<body>		
	<div id="principal" style="background:#ffffff; padding:10px 20px;">
	<p class="tituloPagina">Pagina de Administração dos Resultados</p>
		Jogos da Copa do Mundo FIFA 2014!<br />
		<% def dataAtual =new Date().format("yyyy-MM-dd") %>		
		<g:each in="${datasEJogos}" var="dataJogo">
			<g:if test="${dataJogo.key.format('yyyy-MM-dd') <= dataAtual}">
				<p class="data">${dataJogo.key.format('dd/MM')} - Resultados</p> 				 
			</g:if>
			<g:else>
				<p class="data">${dataJogo.key.format('dd/MM')}</p>
			</g:else>
								
			<g:each in="${dataJogo.value}" var="jogo">
				<% 
				String golsTime01=""
				String golsTime02=""
				
				listaResultados.each{
					if(it.id == jogo.id){
						if(it.golsTime1.toString() != "-1"){
							golsTime01 = it.golsTime1.toString() 
							golsTime02 = it.golsTime2.toString()
						}
					}
				}
				
				 %>
				<g:formRemote url="[controller:'admin', action: 'aFazer']" name="frmJogo" update="msg${jogo.id}">
				<div style="padding:10px; width:90%;" id="${jogo.id}">
					<img src="${resource(dir: '/images/flags', file: jogo.time1.bandeira)}" align="absmiddle" />&nbsp; ${jogo.time1.nome}
					&nbsp;
					<input type="text" value="<% println golsTime01 %>" name="golsTime1" class="campo1" style="width:30px;" required maxlenght="2" />
					&nbsp;
					x
					&nbsp;
					<input type="text" value="<% println golsTime02 %>" name="golsTime2" class="campo1" style="width:30px;" required maxlenght="2" />
					&nbsp;
					${jogo.time2.nome}&nbsp;<img src="${resource(dir: '/images/flags', file: jogo.time2.bandeira)}" align="absmiddle" />
					
					<input type="hidden" value="${jogo.id}" name="idJogo"/>
					
					<input type="submit" value="Salvar Resultado" class="salvarResultado"/>
					
					<div id="msg${jogo.id}"></div>					
				</div>
				
				</g:formRemote>
			</g:each>
		</g:each>
	</div>
</body>
</html>