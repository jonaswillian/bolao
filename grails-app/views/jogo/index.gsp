<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
</head>
<body>		
	<div id="principal" style="background:#ffffff; padding:10px 20px;">
	<p class="tituloPagina">JOGOS</p>
		Confira todos os jogos da Copa do Mundo FIFA 2014!<br />
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
				
				listaPalpites.each{
					if(it.jogo.id == jogo.id){
						golsTime01 = it.golsTime1.toString() 
						golsTime02 = it.golsTime2.toString()
					}
				}
				
				 %>
				<g:formRemote url="[controller:'jogo', action: 'salvarPalpite']" name="frmJogo" update="msg${jogo.id}">
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
					<g:if test="${dataJogo.key.format('yyyy-MM-dd') > dataAtual}">
					<input type="submit" value="Salvar Palpite" class="salvarPalpite"/>
					</g:if>
					<g:else>					
					&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 Placar final: &nbsp;&nbsp;&nbsp;${jogo.time1.nome} ${jogo.golsTime1} x ${jogo.golsTime2} ${jogo.time2.nome}
					 <% if((jogo.golsTime1.toString().equals(golsTime01)) && (jogo.golsTime2.toString().equals(golsTime02))){ %>
					 &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <img src="${resource(dir: '/images/', file: 'acerto.png')}" align="absmiddle" />
					<% } else{ %>
					&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img src="${resource(dir: '/images/', file: 'erro2.png')}" align="absmiddle" />
					<% } %>						
					</g:else>
					<div id="msg${jogo.id}"></div>					
				</div>
				
				</g:formRemote>
			</g:each>
		</g:each>
	</div>
</body>
</html>




