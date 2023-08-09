const mySelect = document.querySelectorAll(".form-items");
mySelect.forEach((select) => {
	select.addEventListener("change", function () {
		document.querySelector(".form").submit();
	});
});

let navigation = document.querySelector(".navbar__content");
let menuToggle = document.querySelector(".menuToggle");
let header = document.querySelector("header");

menuToggle.onclick = function () {
	header.classList.toggle("open");
};

function profileToggle() {
	const toggleMenu = document.querySelector(".menu");
	toggleMenu.classList.toggle("active");
}

window.onload = function () {
	let myValue = document.getElementById("myDiv").textContent;

	if (myValue !== null && myValue === "true") {
		alert("Successful");
	}
};

function testOnLoad() {
	alert("Page loaded!");
}
