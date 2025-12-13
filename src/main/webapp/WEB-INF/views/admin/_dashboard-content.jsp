<%@ page contentType="text/html;charset=UTF-8" %>
<div class="dashboard-container fade-in">
    <h1>Welcome, Admin</h1>
    <div class="stats-grid">
        <div class="card stat-card">
            <h2>Total Quizzes</h2>
            <p id="total-quizzes">0</p>
        </div>
        <div class="card stat-card">
            <h2>Total Users</h2>
            <p id="total-users">0</p>
        </div>
        <div class="card stat-card">
            <h2>Total Questions</h2>
            <p id="total-questions">0</p>
        </div>
    </div>
</div>

<style>
.dashboard-container {
    padding: 20px;
}
.stats-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
    gap: 16px;
    margin-top: 20px;
}
.stat-card {
    text-align: center;
    background: #fff;
    padding: 24px;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.05);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}
.stat-card:hover {
    transform: translateY(-4px);
    box-shadow: 0 12px 25px rgba(0,0,0,0.1);
}
.stat-card h2 {
    font-size: 18px;
    margin-bottom: 12px;
}
.stat-card p {
    font-size: 28px;
    font-weight: 600;
    color: #007bff;
}
</style>

<script>
window.addEventListener('DOMContentLoaded', () => {
    // Dummy data, replace with server-side values
    document.getElementById('total-quizzes').textContent = 12;
    document.getElementById('total-users').textContent = 45;
    document.getElementById('total-questions').textContent = 130;
});
</script>
