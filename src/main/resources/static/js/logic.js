document.addEventListener("DOMContentLoaded", () => {
    const params = new URLSearchParams(window.location.search);
    const path = window.location.pathname;

    // grammar.html — показати теми
    if (path.includes("grammar.html")) {
        const level = params.get("level");
        if (level) {
            fetch(`data/grammar-${level}.json`)
                .then(res => res.json())
                .then(data => renderTopics(data.topics));
        }
    }

    // grammar-topic.html — показати тести
    if (path.includes("grammar-topic.html")) {
        const topic = params.get("topic");
        if (topic) {
            fetch(`tests/${topic}.json`)
                .then(res => res.json())
                .then(data => renderQuiz(data));
        }
    }
});

function renderTopics(topics) {
    const container = document.getElementById("topics-container");
    topics.forEach(topic => {
        const card = document.createElement("div");
        card.className = "card";
        card.innerHTML = `
      <h3>${topic.title}</h3>
      <p>${topic.description}</p>
      <a href="${topic.lessonUrl}" class="card-btn">Start Lesson</a>
    `;
        container.appendChild(card);
    });
}

function renderQuiz(data) {
    document.getElementById("quiz-title").textContent = data.title;
    const container = document.getElementById("quiz-content");

    data.questions.forEach((q, index) => {
        const block = document.createElement("div");
        block.className = "quiz-question";

        const questionHTML = `
      <p><strong>${index + 1}. ${q.question}</strong></p>
      ${q.options.map((opt, i) => `
        <label>
          <input type="radio" name="q${index}" value="${opt}">
          ${String.fromCharCode(65 + i)}. ${opt}
        </label>
      `).join('')}
    `;

        block.innerHTML = questionHTML;
        container.appendChild(block);
    });

    // обробка перевірки відповідей
    const checkBtn = document.getElementById("check-answers-btn");
    if (checkBtn) {
        checkBtn.addEventListener("click", () => {
            const allQuestions = container.querySelectorAll(".quiz-question");

            data.questions.forEach((q, index) => {
                const radios = allQuestions[index].querySelectorAll("input[type=radio]");
                const labels = allQuestions[index].querySelectorAll("label");

                let userAnswered = false;

                radios.forEach((radio, i) => {
                    const isCorrect = radio.value === q.answer;

                    if (radio.checked) {
                        userAnswered = true;
                        if (isCorrect) {
                            labels[i].classList.add("correct");
                        } else {
                            labels[i].classList.add("incorrect");
                        }
                    }
                });

                // даже если пользователь НЕ выбрал правильный — подсветим его
                radios.forEach((radio, i) => {
                    if (radio.value === q.answer) {
                        labels[i].classList.add("correct");
                    }
                });
            });

            checkBtn.disabled = true;
            checkBtn.textContent = "Checked";
        });

    }
    if (page === 'writing.html') {
        fetch(`data/writing-${level}.json`)
            .then(response => response.json())
            .then(data => displayTopics(data.topics));
    }
    function displayTopics(topics) {
        const container = document.getElementById("topics-container");
        container.innerHTML = "";
        topics.forEach(topic => {
            const card = document.createElement("div");
            card.className = "card";
            card.innerHTML = `
      <h3>${topic.title}</h3>
      <p>${topic.description}</p>
      <a href="${topic.lessonUrl}" class="card-btn">Start Lesson</a>
    `;
            container.appendChild(card);
        });
    }

}
