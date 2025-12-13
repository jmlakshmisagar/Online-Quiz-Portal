<%
    request.setAttribute("pageTitle", "Quiz Manager");
    request.setAttribute("content", "/WEB-INF/views/admin/_quiz-manager-content.jsp");
    request.setAttribute("pageClass", "container manager");
%>
<jsp:include page="/WEB-INF/views/layouts/_base.jsp"/>
