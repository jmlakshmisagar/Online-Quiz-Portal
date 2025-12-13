<%
    request.setAttribute("pageTitle", "Admin Dashboard");
    request.setAttribute("content", "/WEB-INF/views/admin/_dashboard-content.jsp");
    request.setAttribute("pageClass", "container");
%>
<jsp:include page="/WEB-INF/views/layouts/_base.jsp"/>
