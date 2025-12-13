<%@ page contentType="text/html;charset=UTF-8" %>
<div class="quizzes-container fade-in">
    <h1>Available Quizzes</h1>
    <div class="grid">
        <c:forEach items="${quizzes}" var="q">
            <div class="card hover-raise">
                <h2>${q.name}</h2>
                <div class="actions">
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/quiz/take?id=${q.id}">Start Quiz</a>
                    <a class="btn btn-outline" href="${pageContext.request.contextPath}/leaderboard?quizId=${q.id}">Leaderboard</a>
                </div>
            </div>
        </c:forEach>
        <c:if test="${empty quizzes}">
            <div class="card">No quizzes yet.</div>
        </c:if>
    </div>
</div>

<style>
.quizzes-container h1 {
    margin-bottom: 16px;
}
.grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
    gap: 16px;
}
.card {
    background: #fff;
    padding: 20px;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.05);
    transition: transform 0.3s, box-shadow 0.3s;
}
.card:hover {
    transform: translateY(-4px);
    box-shadow: 0 12px 25px rgba(0,0,0,0.1);
}
.actions {
    display: flex;
    justify-content: space-between;
    margin-top: 12px;
}
.btn {
    padding: 6px 12px;
    border-radius: 8px;
    text-decoration: none;
    text-align: center;
}
.btn-primary { background: #007bff; color: #fff; }
.btn-outline { border: 1px solid #007bff; color: #007bff; background: transparent; }
@keyframes fadeIn { from {opacity:0;} to {opacity:1;} }
</style>
