<%
    request.setAttribute("pageTitle", "Leaderboard");
    request.setAttribute("content", "/WEB-INF/views/user/_leaderboard-content.jsp");
    request.setAttribute("pageClass", "container leaderboard");
%>
<jsp:include page="/WEB-INF/views/layouts/_base.jsp"/>
