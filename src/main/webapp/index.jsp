<%
// Entry point: redirect based on session
Object user = session.getAttribute("user");
Object admin = session.getAttribute("admin");


if(admin != null){
response.sendRedirect(request.getContextPath() + "/admin/dashboard");
return;
}
if(user != null){
response.sendRedirect(request.getContextPath() + "/quizzes");
return;
}


// Default landing
response.sendRedirect(request.getContextPath() + "/login");
%>