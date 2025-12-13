<%
    request.setAttribute("pageTitle", "Manage Questions");
    request.setAttribute("content", "/WEB-INF/views/admin/_questions-content.jsp");
    request.setAttribute("pageClass", "container manager");
%>
<jsp:include page="/WEB-INF/views/layouts/_base.jsp"/>
