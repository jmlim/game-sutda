<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="<c:url value="/resources/jquery/1.9/jquery-1.9.1.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/scripts/sutda.js" />"></script>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";

	$(document).ready(function() {
		new Sutda.gameService();
	});
</script>
<title>섯다게임</title>
</head>
<body>
	<div class="menu-bar">
		<button class="game-start" title="game-start">게임시작</button>
		<button class="batting check" title="check">체크</button>
		<button class="batting harf" title="harf">하프</button>
		<button class="batting call" title="call">콜</button>
		<button class="batting pping" title="pping">핑</button>
		<button class="batting die" title="die">다이</button>
	</div>
	<div class="damyo">
		<div id="player0" class="center player"></div>
		<div id="player1" class="center player"></div>
		<div id="player2" class="center player"></div>
		<div id="player3" class="center player"></div>
		<div id="card-stack" class="center">
			<img src="<c:url value="/resources/images/99.png" />" />
		</div>
	</div>
</body>
</html>



