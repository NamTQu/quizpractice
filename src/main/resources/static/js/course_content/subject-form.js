var formSubmittedElement = document.getElementById("formSubmitted");

function confirmSubmit() {
    if (!confirm("Are you sure you want to submit the form?")) {
        event.preventDefault();
    } else {
        document.getElementById("myForm").submit();
    }
}

const image_input = document.querySelector("#thumbnail");
var uploaded_image = "";

image_input.addEventListener("change", function() {
    console.log(image_input.value);
    const reader = new FileReader();
    reader.addEventListener("load", () => {
        uploaded_image = reader.result;
        document.querySelector("#display_image").style.backgroundImage = `url(${uploaded_image})`;
    });
    reader.readAsDataURL(this.files[0]);
})


const category = document.getElementById("category");
const subcategory = document.getElementById("subcategory");
const categoryId = category.value;
console.log('initial categoryId: ' + categoryId);
// category.addEventListener("change", loadSubcategories);
//
// function loadSubcategories() {
//     console.log('after changing categoryId: ' + categoryId);
//
//     $.ajax({
//        type: 'GET',
//        url: '/admin/new-subject/get-subcategories/' + categoryId,
//        data: {categoryId : categoryId},
//        success: function (data) {
//             //Clean existing options
//             $("#subcategory").empty();
//
//             //Add new sub-categories to the options
//             $.each(data, function(index, subCategory) {
//                 $("#subcategory").append('<option value="' + subCategory.id + '">' + subCategory.name + '</option>');
//             });
//        }
//     });
// }