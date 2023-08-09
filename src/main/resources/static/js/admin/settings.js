function setPageFilters() {
    const urlParams = new URLSearchParams(window.location.search);
    const type = urlParams.get("type");
    const status_ = urlParams.get("enable");
    const sortBy = urlParams.get("sortBy");
    const order = urlParams.get("order");
    const value=urlParams.get("value");
  
    if (type) {
        document.getElementById("typeSelect").value = type;
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
    if(value){
        document.getElementById("search").value = value;
    }
}
  
window.addEventListener("load", setPageFilters);
function navigateToInteract() {
    var type = document.getElementById("typeSelect").value;
    var status = document.getElementById("statusSelect").value;
    var sortBy=document.getElementById("sortBySelect").value;
    var order=document.getElementById("orderSelect").value;
    var value=document.getElementById("search").value;
    var url = "/admin/settings?";
    var filters = [];

    if(value){
        filters.push("value="+value);
    }
    if (type) {
        filters.push("type=" + type);
    }
    if (status) {
        filters.push("enable=" + status);
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
    var type = document.getElementById("typeSelect").value;
    var status = document.getElementById("statusSelect").value;
    var sortBy=document.getElementById("sortBySelect").value;
    var order=document.getElementById("orderSelect").value;
    var value=document.getElementById("search").value;
    
    var url = "/admin/settings?&pageNo=" + (parseInt(pageNo) + 1)+"&";
    var filters = [];

    if(value){
        filters.push("value="+value);
    }
    if (type) {
        filters.push("type=" + type);
    }
    if (status) {
        filters.push("enable=" + status);
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
    var type = document.getElementById("typeSelect").value;
    var status = document.getElementById("statusSelect").value;
    var sortBy = document.getElementById("sortBySelect").value;
    var order = document.getElementById("orderSelect").value;
    var value=document.getElementById("search").value;

    var url = "/admin/settings?pageNo=" + (parseInt(pageNo) - 1)+"&";
    var filters = [];

    if(value){
        filters.push("value="+value);
    }
    if (type) {
        filters.push("type=" + gender);
    }
    if (status) {
        filters.push("enable=" + status);
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