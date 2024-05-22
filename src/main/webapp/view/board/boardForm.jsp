<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판 등록</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
body {
	min-height: 100vh;
	background: -webkit-gradient(linear, left bottom, right top, from(#92b5db),
		to(#1d466c));
	background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
}

.input-form {
	max-width: 680px;
	margin-top: 80px;
	padding: 32px;
	background: #fff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
}
</style>
<script>
function chkpass(f) {
	let chk = f.pass.value==f.pass2.value
	if(!chk) {
		alert("비밀번호 확인이 틀렸습니다.")
		f.pass2.focus()
		return chk;
	} else {
		return chk;
	}
}
</script>
</head>

<body>
	<div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<h4 class="mb-3">게시판 등록</h4>
				<form class="validation-form" action="boardPro" method="post"
					onsubmit="return chkpass(this)">

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">작성자</label> <input type="text"
								class="form-control" id="name" name="name" value="" required>
							<div class="invalid-feedback">아이디을 입력해주세요.</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="pass">비밀번호</label> <input type="password"
								class="form-control" id="pass" name="pass" placeholder=""
								value="" required>
							<div class="invalid-feedback">비밀번호을 입력해주세요.</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="pass2">비밀번호확인</label> <input type="password"
								class="form-control" id="pass2" name="pass2" value="" required>
							<div class="invalid-feedback">비밀번호확인을 입력해주세요.</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="subject">제목</label> <input type="text"
								class="form-control" id="subject" name="subject" value=""
								required>
							<div class="invalid-feedback">아이디을 입력해주세요.</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="content">내용</label>
							<textarea cols="" rows="" class="form-control" id="content"
								style="overflow-y:scroll" name="content" required></textarea>
						</div>
					</div>

					<div class="row">
						<div class="col-md-9 mb-3">
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="file1">파일 업로드</label> <input type="file"
										class="form-control" id="file1" name="file1">
									<div class="invalid-feedback">아이디을 입력해주세요.</div>
								</div>
							</div>
						</div>
					</div>

					<button class="btn btn-primary btn-lg btn-block" type="submit">저장</button>
				</form>
			</div>
		</div>

	</div>
	<script> window.addEventListener('load', () => { const forms = document.getElementsByClassName('validation-form'); Array.prototype.filter.call(forms, (form) => { form.addEventListener('submit', function (event) { if (form.checkValidity() === false) { event.preventDefault(); event.stopPropagation(); } form.classList.add('was-validated'); }, false); }); }, false); </script>
</body>
</html>