<%
    request.setAttribute("pageTitle", "Take Quiz");
    request.setAttribute("content", "/WEB-INF/views/user/_take-quiz-content.jsp");
    request.setAttribute("pageClass", "container take-quiz");
%>
<jsp:include page="/WEB-INF/views/layouts/_base.jsp"/>
