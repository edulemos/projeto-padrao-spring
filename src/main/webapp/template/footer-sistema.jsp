
 </div>
 
<c:if test="${error != null}">
	<script>
	    console.log('${error}');
	    var erro = '${error}'; 
		var html = "<div  class=\"alert alert-danger\" style=\"font-weight: bold; font-size: medium;text-transform:uppercase;\" id=\"div_msg_erro\" onclick=\"fechar('div_msg_erro');\"><div style=\"padding:3px;margin-left: 5px;margin-right: 5px;\"></div> &nbsp;<span class=\"glyphicon glyphicon-info-sign\"></span>&nbsp;"+ erro +"</div>";
		$(html).insertAfter("legend");
	</script>
</c:if>	

<c:if test="${msg != null}">
	<script>
	    var msg = '${msg}';
		var html = "<div  class=\"alert alert-info\" style=\"font-weight: bold; font-size: medium;text-transform:uppercase;\" id=\"div_msg_erro\" onclick=\"fechar('div_msg_erro');\"><div style=\"padding:3px;margin-left: 5px;margin-right: 5px;\"></div> &nbsp;<span class=\"glyphicon glyphicon-info-sign\"></span>&nbsp;"+ msg +"</div>";
		$(html).insertAfter("legend");
	</script>
</c:if>
             
<footer>
    <p class="pull-right">2016 <a href="edulemos.github.io" target="_blank">EDUARDO LEMOS</a></p>
</footer>


    
</body>

</html>