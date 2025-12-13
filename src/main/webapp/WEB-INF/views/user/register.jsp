<%
    request.setAttribute("pageTitle", "Register");
    request.setAttribute("content", "/WEB-INF/views/user/_register-content.jsp");
    request.setAttribute("pageClass", "container page-register");
%>
<jsp:include page="/WEB-INF/views/layouts/_base.jsp"/>
