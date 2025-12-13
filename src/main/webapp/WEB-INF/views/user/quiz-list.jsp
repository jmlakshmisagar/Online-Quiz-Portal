<%
    request.setAttribute("pageTitle", "Quizzes");
    request.setAttribute("content", "/WEB-INF/views/user/_quizzes-content.jsp");
    request.setAttribute("pageClass", "container");
%>
<jsp:include page="/WEB-INF/views/layouts/_base.jsp"/>
