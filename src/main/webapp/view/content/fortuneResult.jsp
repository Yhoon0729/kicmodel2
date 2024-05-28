<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>운세 결과</title>
<script>
	function showFortune() {
		// 운세 목록
		var fortunes = [ "오늘은 행복한 날이 되겠어요.", "긍정적으로 생각하면 좋은 결과가 기다리고 있어요.",
				"주위 사람들과의 소통을 더욱 중요히 여겨보세요.", "일상의 작은 변화가 큰 변화를 가져올 수 있어요.",
				"좋은 일이 생길 것 같은 기분이에요." ];

		// 색 목록
		var colors = [ "행복한 주황색", "평온한 녹색", "강렬한 붉은색", "차분한 파란색", "따뜻한 핑크색" ];

		// 행동 목록
		var actions = [ "긍정적으로 생각하기", "친구와 대화하기", "새로운 도전하기", "걷기나 조깅하기",
				"좋아하는 취미 활동하기" ];

		// 랜덤으로 운세, 색, 행동 선택
		var randomFortuneIndex = Math.floor(Math.random() * fortunes.length);
		var randomColorIndex = Math.floor(Math.random() * colors.length);
		var randomActionIndex = Math.floor(Math.random() * actions.length);

		var fortune = fortunes[randomFortuneIndex];
		var color = colors[randomColorIndex];
		var action = actions[randomActionIndex];

		// 운세 출력
		document.getElementById("fortune").innerText = fortune;
		// 색 출력
		document.getElementById("color").innerText = color;
		// 행동 출력
		document.getElementById("action").innerText = action;

		// 추가 정보 영역 숨기기
		document.getElementById("additionalInfo").style.display = "none";
	}

	function showAdditionalInfo() {
		// 추가 정보 영역 표시
		document.getElementById("additionalInfo").style.display = "block";
	}
</script>
</head>
<body>
	<h1>${name}님의운세</h1>
	<button onclick="showFortune()">운세 보기</button>
	<p id="fortune"></p>


	<!-- 추가 정보 영역 -->
	<div id="additionalInfo" style="display: none;">
		<h2>오늘의 색</h2>
		<p id="color"></p>
		<h2>해야 할 행동</h2>
		<p id="action"></p>
	</div>

	<!-- 추가 정보 버튼 -->
	<button onclick="showAdditionalInfo()">추가 정보 보기</button>
</body>
</html>
