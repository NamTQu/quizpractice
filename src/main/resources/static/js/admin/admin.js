function setPageFilters() {
    const urlParams = new URLSearchParams(window.location.search);
    const gender = urlParams.get("gender");
    const status_ = urlParams.get("enable");
    const role = urlParams.get("role");
    const sortBy = urlParams.get("sortBy");
    const order = urlParams.get("order");
    const searchValue=urlParams.get("searchValue");
    if (gender) {
        document.getElementById("genderSelect").value = gender;
    }
    if (role) {
        document.getElementById("roleSelect").value = role;
    }
    if (status_) {
        document.getElementById("statusSelect").value = status_;
    }
    if (sortBy) {
        document.getElementById("sortBySelect").value = sortBy;
    }
    if (order) {
        document.getElementById("orderSelect").value = order;
    }
    if(searchValue){
        document.getElementById("search").value=searchValue;
    }
}
  
window.addEventListener("load", setPageFilters);
function navigateToInteract() {
    var gender = document.getElementById("genderSelect").value;
    var status = document.getElementById("statusSelect").value;
    var role = document.getElementById("roleSelect").value;
    var sortBy=document.getElementById("sortBySelect").value;
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/admin/user-list?";
    var filters = [];

    if(searchValue){
        filters.push("searchValue=" + searchValue);
    }
    if (gender) {
        filters.push("gender=" + gender);
    }
    if (status) {
        filters.push("enable=" + status);
    }
    if (role) {
        filters.push("role=" + role);
    }
    if(sortBy) {
        filters.push("sortBy="+sortBy);
    }
    if(order) {
        filters.push("order="+order);
    }
    url += filters.join("&");
    
    window.location.href = url;
    
}
function moveOn(pageNo){
    var gender = document.getElementById("genderSelect").value;
    var status = document.getElementById("statusSelect").value;
    var role = document.getElementById("roleSelect").value;
    var sortBy=document.getElementById("sortBySelect").value;
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/admin/user-list?pageNo=" + (parseInt(pageNo) + 1)+"&";
    
    var filters = [];

    if(searchValue){
        filters.push("searchValue=" + searchValue);
    }
    if (gender) {
        filters.push("gender=" + gender);
    }
    if (status) {
        filters.push("enable=" + status);
    }
    if (role) {
        filters.push("role=" + role);
    }
    if(sortBy) {
        filters.push("sortBy="+sortBy);
    }
    if(order) {
        filters.push("order="+order);
    }
    url += filters.join("&");
    
    window.location.href = url;
}

function moveBack(pageNo){
    var gender = document.getElementById("genderSelect").value;
    var status = document.getElementById("statusSelect").value;
    var role = document.getElementById("roleSelect").value;
    var sortBy=document.getElementById("sortBySelect").value;
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/admin/user-list?pageNo=" + (parseInt(pageNo) - 1)+"&";
    
    var filters = [];

    if(searchValue){
        filters.push("searchValue=" + searchValue);
    }
    if (gender) {
        filters.push("gender=" + gender);
    }
    if (status) {
        filters.push("enable=" + status);
    }
    if (role) {
        filters.push("role=" + role);
    }
    if(sortBy) {
        filters.push("sortBy="+sortBy);
    }
    if(order) {
        filters.push("order="+order);
    }
    url += filters.join("&");
    
    window.location.href = url;
}
function showPopup() {
    document.getElementById("popupForm").style.display = "block";
}
  
function hidePopup() {
    document.getElementById("popupForm").style.display = "none";
}
