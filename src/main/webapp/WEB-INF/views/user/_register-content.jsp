<%@ page contentType="text/html;charset=UTF-8" %>
<div class="register-container fade-in">
    <h1>Create Account</h1>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/register" class="register-form">
        <input class="input" type="text" name="username" placeholder="Username" required/>
        <input class="input" type="email" name="email" placeholder="Email" required/>
        <input class="input" type="text" name="fullName" placeholder="Full Name"/>
        <input class="input" type="password" name="password" placeholder="Password" required/>
        <button class="btn btn-primary btn-press" type="submit">Register</button>
    </form>
</div>

<style>
.register-container {
    max-width: 450px;
    margin: 50px auto;
    padding: 24px;
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 12px 30px rgba(0,0,0,0.08);
    text-align: center;
    animation: fadeIn 0.8s ease forwards;
}
.register-form .input {
    width: 100%;
    margin: 10px 0;
    padding: 10px 12px;
    border-radius: 8px;
    border: 1px solid #ccc;
}
.register-form .input:focus {
    border-color: #007bff;
}
.register-form button {
    width: 100%;
    margin-top: 12px;
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
