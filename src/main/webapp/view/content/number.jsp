<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>숫자 맞추기 게임</title>
</head>
<body>
    <h1>숫자 맞추기 게임</h1>
    <form action="guessNumberPro" method="post">
        <label for="guess">숫자를 입력하세요:</label>
        <input type="text" id="guess" name="guess">
        <input type="submit" value="제출">
    </form>
    <p>${message}</p>
</body>
</html>

