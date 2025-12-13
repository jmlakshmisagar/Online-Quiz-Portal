<%@ page contentType="text/html;charset=UTF-8" %>
<div class="login-container fade-in">
    <h1>User Login</h1>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/login" class="login-form">
        <input class="input" type="text" name="username" placeholder="Username" required/>
        <input class="input" type="password" name="password" placeholder="Password" required/>
        <button class="btn btn-primary btn-press" type="submit">Login</button>
    </form>
</div>

<style>
.login-container {
    max-width: 400px;
    margin: 60px auto;
    padding: 24px;
    border-radius: 12px;
    box-shadow: 0 12px 30px rgba(0,0,0,0.08);
    background: #fff;
    text-align: center;
    animation: fadeIn 0.8s ease forwards;
}
.login-form .input {
    width: 100%;
    margin: 12px 0;
    padding: 10px 12px;
    border-radius: 8px;
    border: 1px solid #ccc;
    transition: border 0.3s;
}
.login-form .input:focus {
    border-color: #007bff;
}
.login-form button {
    width: 100%;
    margin-top: 16px;
}
.alert {
    background: #f8d7da;
    color: #842029;
    padding: 10px 12px;
    border-radius: 8px;
    margin-bottom: 16px;
}
@keyframes fadeIn { from {opacity:0;} to {opacity:1;} }
</style>

<script>
document.querySelectorAll('.login-form input').forEach(input => {
    input.addEventListener('focus', () => input.style.borderColor = '#007bff');
    input.addEventListener('blur', () => input.style.borderColor = '#ccc');
});
</script>
