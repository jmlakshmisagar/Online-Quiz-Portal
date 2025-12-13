<%@ page contentType="text/html;charset=UTF-8" %>
<header class="topbar">
    <div class="container flex justify-between items-center">
        <div class="brand font-bold text-xl text-indigo-600">QuizMaster</div>
        <nav class="nav flex gap-4">
            <%
            if (session.getAttribute("admin") != null) {
            %>
                <a href="<%=request.getContextPath()%>/admin/dashboard" class="nav-link">Dashboard</a>
                <a href="<%=request.getContextPath()%>/admin/logout" class="nav-link logout">Logout</a>
            <%
            } else if (session.getAttribute("user") != null) {
            %>
                <a href="<%=request.getContextPath()%>/quizzes" class="nav-link">Quizzes</a>
                <a href="<%=request.getContextPath()%>/logout" class="nav-link logout">Logout</a>
            <%
            } else {
            %>
                <a href="<%=request.getContextPath()%>/login" class="nav-link">Login</a>
                <a href="<%=request.getContextPath()%>/register" class="nav-link register">Register</a>
            <%
            }
            %>
        </nav>
    </div>
</header>

<style>
.topbar {
    background-color: #ffffff;
    padding: 1rem 2rem;
    box-shadow: 0 2px 8px rgba(0,0,0,0.05);
    position: sticky;
    top: 0;
    z-index: 1000;
}
.nav-link {
    color: #374151;
    font-weight: 500;
    text-decoration: none;
    padding: 0.5rem 1rem;
    border-radius: 6px;
    transition: all 0.3s ease;
}
.nav-link:hover {
    background-color: #e0e7ff;
    color: #1e3a8a;
}
.brand {
    font-family: 'Inter', sans-serif;
}
.flex { display: flex; }
.justify-between { justify-content: space-between; }
.items-center { align-items: center; }
.gap-4 { gap: 1rem; }
</style>
