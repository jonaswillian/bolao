<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />	
</head>
	
<body>
	<div id="principal" style="background:#ffffff; padding:10px 20px;">
		<p class="tituloPagina">FALE CONOSCO</p>
		Nosso site quer manter um di&aacute;logo aberto com todos os visitantes. Precisa de ajuda? Quer saber mais? Necessita de suporte t&eacute;nico? Fale com a gente!<br />
		<p style="border-left:8px solid #006313; padding:8px; font-size:18px; color:#006313;">
	              <strong>BIT BOL&Atilde;O</strong><br />
	              <span style="font-size:15px;">Rua Oscar Mesquita, 166 - Jd Coelho Neto<br />
	              Guaratinguet&aacute; (SP) - CEP 12514-080<br /></span>
	              (12) 3132-5454<br />
	              fale@bitbolao.com.br
		</p>
			
		<p>
		
		<div id="map_canvas" style="height:200px;"></div>
           		<script>
           			function initialize() {
						var pixby = new google.maps.LatLng(-22.802067,-45.195781);
						var myLatlng = new google.maps.LatLng(-22.802067,-45.195781);
						var mapOptions = {
							zoom: 17,
							center: myLatlng,
							mapTypeId: google.maps.MapTypeId.SATELLITE ,
							panControl: false,
							scrollwheel: false,
							zoomControlOptions: {
								style: google.maps.ZoomControlStyle.BIG,
								position: google.maps.ControlPosition.LEFT_TOP
							},
							streetViewControl: false
						}
						var map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
						var contentString = 
						'<div id="content" style="text-align: center; font-size: 12px; font-family: Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif;">'+
							'<div id="siteNotice">'+'</div>'+
							'<h4 id="firstHeading" class="firstHeading"><img src="img/logo.png" width="90"></h4>'+
							'<div id="bodyContent">'+
								'<p></p>'+
							'</div>'+
						'</div>';
					
						var infowindow = new google.maps.InfoWindow({
							content: contentString
             				});
						var marker = new google.maps.Marker({
							position: pixby,
							map: map,
							title: '',
							zIndex: 99
          					});
						
          					google.maps.event.addListener(marker, 'click', function() {
          					infowindow.open(map,marker);
      					});
             		
     				}
	            initialize();
	          	</script> 
			</p>
				
			<p style="margin-top:35px;">Voc&ecirc; pode tamb&eacute;m enviar sua mensagem pelo formul&aacute;rio, preenchendo os campos abaixo.</p>
               <form method="post" action="">
                   <strong>Seu nome:</strong><br /><input type="text" name="nome" class="campo1" style="width:700px; height:30px;" /><br /><br />
                   <strong>Seu e-mail:</strong><br /><input type="text" name="email" class="campo1" style="width:700px; height:30px;" /><br /><br />
                   <strong>Sua mensagem:</strong><br /><textarea name="msg" class="campo1" style="width:700px; height:100px;"></textarea><br /><br />
				<input type="submit" value="Enviar" name="Enviar" class="bot" />
           	</form>		
			
		</div>
			
		<div class="clear"></div>
	
</body>
</html>
