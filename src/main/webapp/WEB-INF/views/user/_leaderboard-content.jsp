<%@ page contentType="text/html;charset=UTF-8" %>
<div class="leaderboard-container fade-in">
    <h1>Leaderboard – ${quiz.name}</h1>
    <table class="table zebra">
        <thead>
            <tr>
                <th>#</th>
                <th>User</th>
                <th>Score</th>
                <th>Submitted</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${leaders}" var="row" varStatus="loop">
                <tr>
                    <td>#${loop.index + 1}</td>
                    <td>${row.username}</td>
                    <td>${row.score}</td>
                    <td>${row.submitted_at}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty leaders}">
                <tr><td colspan="4">No results yet.</td></tr>
            </c:if>
        </tbody>
    </table>
</div>

<style>
.leaderboard-container { max-width: 800px; margin: 50px auto; background: #fff; padding: 24px; border-radius: 12px; box-shadow: 0 12px 30px rgba(0,0,0,0.08); }
.table { width: 100%; border-collapse: collapse; margin-top: 16px; }
.table th, .table td { padding: 12px; border-bottom: 1px solid #eee; text-align: left; }
.table th { background: #f5f5f5; }
.fade-in { animation: fadeIn 0.8s ease forwards; }
@keyframes fadeIn { from {opacity:0;} to {opacity:1;} }
</style>
