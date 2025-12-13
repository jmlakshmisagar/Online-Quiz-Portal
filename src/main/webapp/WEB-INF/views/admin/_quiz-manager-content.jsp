<%@ page contentType="text/html;charset=UTF-8" %>
<div class="quiz-manager fade-in">
    <h1>Manage Quizzes</h1>
    <button class="btn btn-primary" id="add-quiz-btn">Add Quiz</button>
    <table class="table zebra">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Published</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="quiz-list">
            <!-- Quizzes will be dynamically loaded -->
        </tbody>
    </table>
</div>

<style>
.quiz-manager h1 {
    margin-bottom: 16px;
}
#add-quiz-btn {
    margin-bottom: 16px;
}
.table th, .table td {
    padding: 12px;
}
</style>

<script>
window.addEventListener('DOMContentLoaded', () => {
    const quizzes = [
        {id:1, name:'JS Basics', published:true},
        {id:2, name:'Java OOP', published:false}
    ];
    const tbody = document.getElementById('quiz-list');
    quizzes.forEach(q => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${q.id}</td>
            <td>${q.name}</td>
            <td>${q.published ? 'Yes' : 'No'}</td>
            <td>
                <button class="btn btn-outline edit-btn">Edit</button>
                <button class="btn btn-outline delete-btn">Delete</button>
            </td>`;
        tbody.appendChild(row);
    });
});
</script>
