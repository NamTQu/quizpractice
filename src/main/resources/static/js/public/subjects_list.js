function setPageFilters() {
    const urlParams = new URLSearchParams(window.location.search);
    const order = urlParams.get("order");
    const searchValue=urlParams.get("search");
    const subCategories = urlParams.getAll("subCategory");
    
    if (order) {
        document.getElementById("orderSelect").value = order;
    }
    if(searchValue){
        document.getElementById("search").value=searchValue;
    }
    if (subCategories.length > 0) {
        const checkboxes = document.querySelectorAll(".subcategory");

        checkboxes.forEach((checkbox) => {
            const subcategoryId = checkbox.value;

            if (subCategories.includes(subcategoryId)) {
                checkbox.checked = true;
            }
        });
    }
}
  
window.addEventListener("load", setPageFilters);

function navigateToInteract() {
    
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    
    var url = "/subjects/subjects-list?";
    var filters = [];

    if(searchValue){
        filters.push("search=" + searchValue);
    }
    if(order) {
        filters.push("order="+order);
    }
    var checkedSubcategories = document.querySelectorAll(".subcategory:checked");
    checkedSubcategories.forEach(function (checkbox) {
        if(checkbox.checked){
            filters.push("subCategory=" + checkbox.value);
        }else{
            var valueToRemove = "subCategory=" + checkbox.value;
            var index = filters.indexOf(valueToRemove);
            if (index !== -1) {
                filters.splice(index, 1);
            }
        }
    });
    url += filters.join("&");
    
    window.location.href = url;
    
}

function moveOn(pageNo){

    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/subjects/subjects-list?pageNo=" + (parseInt(pageNo) + 1)+"&";
    
    var filters = [];

    if(searchValue){
        filters.push("search=" + searchValue);
    }
    if(order) {
        filters.push("order="+order);
    }
    var checkedSubcategories = document.querySelectorAll(".subcategory:checked");
    checkedSubcategories.forEach(function (checkbox) {
        filters.push("subCategory=" + checkbox.value);
    });
    url += filters.join("&");
    
    window.location.href = url;
}

function moveBack(pageNo){
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/subjects/subjects-list?pageNo="+ (parseInt(pageNo) - 1)+"&";
    
    var filters = [];

    if(searchValue){
        filters.push("search=" + searchValue);
    }
    if(order) {
        filters.push("order="+order);
    }
    var checkedSubcategories = document.querySelectorAll(".subcategory:checked");
    checkedSubcategories.forEach(function (checkbox) {
        filters.push("subCategory=" + checkbox.value);
    });
    url += filters.join("&");
    
    window.location.href = url;
}

function viewDetails(subjectId){
    window.location.href = "/subjects/subject-detail?subjectId="+subjectId;
}

function register(subjectId){
    getPricePackage(subjectId);
    document.getElementById('popupForm_'+subjectId).style.display="block";
}
function hidePopup(subjectId){
    document.getElementById('popupForm_'+subjectId).style.display="none";

}
function getPricePackage(subjectId) {
    $.get("/get-pricepackages?subjectId=" + subjectId, function(data) {
        $("#inputPricePackage_"+subjectId).empty();
        data.forEach(function(item) {
            var option = "<option value=" + item.id + ">" + item.name + "</option>";
            $("#inputPricePackage_"+subjectId).append(option);
        });
    });
}