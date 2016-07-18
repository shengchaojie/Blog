<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<h2>Hello World!${sessionScope.username}</h2>
<a href="${pageContext.request.contextPath}/user/view/login">login</a><br/>
<a href="${pageContext.request.contextPath}/user/view/register">register</a>
</body>
</html>
