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
	Object a =  request.getAttribute("message01");
	float message01 = (float) a ;
	
	Object b =  request.getAttribute("message02");
	float message02 = (float) b ;
	
	Object c =  request.getAttribute("message03");
	float message03 = (float) c ;

	Optional<String> string = 
	Optional.ofNullable((String) request.getAttribute("string"));

%>

<body>
<H1>Sentiment　分析結果</H1>
<H3>文章：<%= string.orElse("ERROR") %></H3>
<H3>結果:<%= "ネガティブ:" + message01 %></H3>
		<H3><%= "ニュートラル:"+ message02 %><BR></H3>
		<H3><%= "ポジティブ:" +  message03 %></H3>
</body>
</html>