<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title><%=request.getAttribute("pageTitle") != null ? request.getAttribute("pageTitle") : "QuizMaster"%></title>

<!-- Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">

<!-- Base CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/base.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/components.css" />

<%
if (request.getAttribute("pageClass") != null) {
%>
<style>
body {
    font-family: 'Inter', sans-serif;
    background-color: #f9fafb;
    color: #1f2937;
    margin: 0;
    padding: 0;
}
.page {
    padding: 2rem 1rem;
    max-width: 1200px;
    margin: auto;
    transition: all 0.3s ease-in-out;
}
</style>
<%
}
%>
</head>
<body class="<%=request.getAttribute("pageClass") != null ? request.getAttribute("pageClass") : ""%>">

<!-- Include Header -->
<jsp:include page="/WEB-INF/views/layouts/_header.jsp" />

<!-- Main Content -->
<main class="page">
    <%
    String content = (String) request.getAttribute("content");
    if (content != null) {
    %>
        <jsp:include page="<%=content%>" />
    <%
    }
    %>
</main>

<!-- Include Footer -->
<jsp:include page="/WEB-INF/views/layouts/_footer.jsp" />

<!-- JS -->
<script src="<%=request.getContextPath()%>/assets/js/app.js"></script>

<!-- Smooth Animations -->
<script>
document.addEventListener('DOMContentLoaded', () => {
    const pageElements = document.querySelectorAll('.page *');
    pageElements.forEach(el => {
        el.style.opacity = 0;
        el.style.transform = 'translateY(20px)';
        setTimeout(() => {
            el.style.transition = 'all 0.5s ease-out';
            el.style.opacity = 1;
            el.style.transform = 'translateY(0)';
        }, 50);
    });
});
</script>
</body>
</html>
