import { questions } from "./data.js";

const progressValueEl = document.querySelector(".progress .value");
const numberEl = document.querySelector(".number");
const questionEl = document.querySelector(".question");
const choice1El = document.querySelector(".choice1");
const choice2El = document.querySelector(".choice2");

let currentNumber = 0;
let testResult = "";

// 화면에 질문 출력
function renderQuestion() {
	const question = questions[currentNumber];
	
	numberEl.innerHTML = question.number;
	questionEl.innerHTML = question.question;
	choice1El.innerHTML = question.choices[0].text;
	choice2El.innerHTML = question.choices[1].text;
	progressValueEl.style.width = (currentNumber + 1) * 25 + "%";
}

// 답변 클릭 시 호출될 함수
function nextQuestion(choiceNumber) {
	const question = questions[currentNumber];
	testResult += ("_!" + question.choices[choiceNumber].value);
	
	if (currentNumber === questions.length - 1) {
		showResultPage();
		return;
	}
	
	currentNumber++;
	console.log("currentNumber: " + currentNumber);
	renderQuestion();
}

// 결과 페이지로 이동
function showResultPage() {
	location.href = `placeMbtiTest/result?place=${testResult}`; // 쿼리스트링
}

choice1El.addEventListener("click", () => {
	nextQuestion(0);
});

choice2El.addEventListener("click", () => {
	nextQuestion(1);
});

renderQuestion();
