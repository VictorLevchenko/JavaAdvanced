<!-- Вам нужно написать JSP страницу, которая выводит информацию из произвольного csv файла в табличной форме. -->
<!-- Например, такого: -->
<!-- name,price,count -->
<!-- computer,100,20 -->
<!-- phone,50,30 -->
<!-- car, 10000,2 -->
<!-- При открытии JSP страницы она загружает файл и выводит его содержимое как таблицу. -->
<%@page import="java.io.IOException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.nio.file.Paths"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table, th, td {
	border: 1px solid black;
}

th, td {
	padding: 15px;
}
</style>
</head>
<body>
	<%
		String pathParam = request.getParameter("path");

		Path path = Paths.get(pathParam);

		try (InputStream in = Files.newInputStream(path);) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			out.println("<table >");
			int count = 0;
			while ((line = reader.readLine()) != null) {
				String[] csv = line.split(",");
				out.println("<tr>");
				for (String val : csv) {
					if (count == 0) {
						out.println("<th>" + val + "</th>");
					} else {
						out.println("<td>" + val + "</td>");
					}
				}
				out.println("</tr>");
			}
			out.println("</table>");
		} catch(IOException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
		}
	%>
</body>
</html>