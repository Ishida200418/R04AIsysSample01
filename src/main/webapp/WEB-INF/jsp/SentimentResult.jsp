<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sentiment結果表示</title>
</head>
<%
	Optional<String> message = 
		Optional.ofNullable((String) request.getAttribute("message"));
Optional<String> string = 
	Optional.ofNullable((String) request.getAttribute("string"));

%>

<body>
<H1>Sentiment　分析結果</H1>
<H3>文章：<%= string.orElse("ERROR") %></H3>
<H3>結果：<%= message.orElse("ERROR") %></H3>
</body>
</html>