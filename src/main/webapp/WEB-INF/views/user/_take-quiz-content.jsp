<%@ page contentType="text/html;charset=UTF-8" %>
<div class="take-quiz-container fade-in">
    <h1>${quiz.name}</h1>
    <form method="post" action="${pageContext.request.contextPath}/quiz/submit" class="quiz-form">
        <input type="hidden" name="quizId" value="${quiz.id}"/>
        <input type="hidden" name="questionId" value="${question.id}"/>
        <h2>Q${currentIndex}. ${question.text}</h2>
        <div class="options">
            <c:forEach items="${optList}" var="opt">
                <label class="quiz-option">
                    <input type="radio" name="selectedOptionId" value="${opt.id}" required/>
                    <span>${opt.optionText}</span>
                </label>
            </c:forEach>
        </div>
        <div class="actions">
            <button class="btn btn-primary" type="submit" name="action" value="next" disabled>Next</button>
            <button class="btn btn-outline" type="submit" name="action" value="finish">Finish</button>
        </div>
    </form>
</div>

<style>
.take-quiz-container { max-width: 700px; margin: 50px auto; background: #fff; padding: 24px; border-radius: 12px; box-shadow: 0 12px 30px rgba(0,0,0,0.08);}
.quiz-form h2 { margin: 16px 0; }
.options { display: grid; gap: 12px; margin: 12px 0; }
.quiz-option { display: flex; align-items: center; padding: 10px; border: 1px solid #ccc; border-radius: 8px; cursor: pointer; transition: 0.3s; }
.quiz-option:hover { background: #f0f8ff; }
.quiz-option input { margin-right: 12px; }
.actions { display: flex; gap: 12px; flex-wrap: wrap; margin-top: 16px; }
.btn-primary { background: #007bff; color: #fff; border-radius: 8px; padding: 8px 16px; border: none; cursor: pointer; }
.btn-outline { border: 1px solid #007bff; color: #007bff; border-radius: 8px; padding: 8px 16px; background: transparent; cursor: pointer; }
.fade-in { animation: fadeIn 0.8s ease forwards; }
@keyframes fadeIn { from {opacity:0;} to {opacity:1;} }
</style>

<script>
document.querySelectorAll('.quiz-option input').forEach(radio => {
    radio.addEventListener('change', () => {
        document.querySelector('.quiz-form button[name="next"]').disabled = false;
    });
});
</script>
