<%
    request.setAttribute("pageTitle", "Login");
    request.setAttribute("content", "/WEB-INF/views/user/_login-content.jsp");
    request.setAttribute("pageClass", "container page-login");
%>
<jsp:include page="/WEB-INF/views/layouts/_base.jsp"/>
