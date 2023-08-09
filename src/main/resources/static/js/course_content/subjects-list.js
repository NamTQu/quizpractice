const mySelect = document.querySelectorAll(".form-items");
console.log('mySelect: ' + mySelect.length);
mySelect.forEach((select) => {
	select.addEventListener("change", function () {
		document.querySelector(".form").submit();
	});
});

let popup = document.querySelector(".popup");
let close1 = document.querySelector(".close");

function testOnLoad() {
	let myValue = document.getElementById("myDiv").textContent;
	console.log('testing onLoad');
	console.log(myValue);
	if (myValue !== null && myValue === "true") {
		popup.classList.add("active");
		close1.addEventListener("click", function () {
			popup.classList.remove("active");
		});
	}
}

window.addEventListener("load", testOnLoad());

const radioLabel = document.querySelector(".radio-label");
const categoriesId = document.querySelector(".category-id-ul");
radioLabel.addEventListener("click", function() {
	radioLabel.classList.toggle("open");
});
