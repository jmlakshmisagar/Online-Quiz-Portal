<%@ page contentType="text/html;charset=UTF-8" %>
<div class="questions-manager fade-in">
    <h1>Manage Questions</h1>
    <button class="btn btn-primary" id="add-question-btn">Add Question</button>
    <table class="table zebra">
        <thead>
            <tr>
                <th>ID</th>
                <th>Question</th>
                <th>Created By</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="questions-list">
            <!-- Questions will be dynamically loaded -->
        </tbody>
    </table>
</div>

<style>
.questions-manager h1 {
    margin-bottom: 16px;
}
#add-question-btn {
    margin-bottom: 16px;
}
.table th, .table td {
    padding: 12px;
}
</style>

<script>
window.addEventListener('DOMContentLoaded', () => {
    const questions = [
        {id:1, text:'What is Java?', createdBy:'Admin'},
        {id:2, text:'JS closures explained?', createdBy:'Admin'}
    ];
    const tbody = document.getElementById('questions-list');
    questions.forEach(q => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${q.id}</td>
            <td>${q.text}</td>
            <td>${q.createdBy}</td>
            <td>
                <button class="btn btn-outline edit-btn">Edit</button>
                <button class="btn btn-outline delete-btn">Delete</button>
            </td>`;
        tbody.appendChild(row);
    });
});
</script>
