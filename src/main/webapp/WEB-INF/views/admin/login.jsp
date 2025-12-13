<%
    request.setAttribute("pageTitle", "Admin Login");
    request.setAttribute("content", "/WEB-INF/views/admin/_login-content.jsp");
    request.setAttribute("pageClass", "container narrow");
%>
<jsp:include page="/WEB-INF/views/layouts/_base.jsp"/>
