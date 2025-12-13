// =====================
// Quiz Module JS
// =====================

// Timer
document.querySelectorAll("[data-timer]").forEach(el => {
    const quizId = el.dataset.quiz;
    const totalMs = parseInt(el.dataset.totalms, 10);
    const progressBar = document.querySelector(".progress span");
    let start = Date.now();

    const updateTimer = () => {
        let elapsed = Date.now() - start;
        let remaining = Math.max(totalMs - elapsed, 0);
        let percent = ((totalMs - remaining) / totalMs) * 100;
        if (progressBar) progressBar.style.width = percent + "%";
        el.textContent = `${Math.ceil(remaining / 1000)}s`;

        if (remaining <= 0) clearInterval(timerInterval);
    };

    const timerInterval = setInterval(updateTimer, 1000);
    updateTimer();
});

// Enable Next button only if an option is selected
document.querySelectorAll(".quiz-option input").forEach(input => {
    input.addEventListener("change", () => {
        const form = input.closest("form");
        if (form) {
            const nextBtn = form.querySelector("button[name='action'][value='next']");
            if (nextBtn) nextBtn.disabled = false;
        }
    });
});
