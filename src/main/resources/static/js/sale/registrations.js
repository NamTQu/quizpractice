function setPageFilters() {
    const urlParams = new URLSearchParams(window.location.search);
    const validFrom = urlParams.get("validFrom");
    const validTo = urlParams.get("validTo");
    const status_ = urlParams.get("status");
    const sortBy = urlParams.get("sortBy");
    const order = urlParams.get("order");
    const searchValue=urlParams.get("searchValue");
    if (validFrom) {
        document.getElementById("validFromSelect").value = validFrom;
    }
    if (validTo) {
        document.getElementById("validToSelect").value = validTo;
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
    var validFrom = document.getElementById("validFromSelect").value;
    var validTo = document.getElementById("validToSelect").value;
    var status = document.getElementById("statusSelect").value;
    var sortBy=document.getElementById("sortBySelect").value;
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/sale/registrations-list?";
    var filters = [];

    if(searchValue){
        filters.push("searchValue=" + searchValue);
    }
    if (validFrom) {
        filters.push("validFrom=" + validFrom);
    } 
    if (validTo) {
        filters.push("validTo=" + validTo);
    }
    if (status) {
        filters.push("status=" + status);
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
    var validFrom = document.getElementById("validFromSelect").value;
    var validTo = document.getElementById("validToSelect").value;
    var status = document.getElementById("statusSelect").value;
    var sortBy=document.getElementById("sortBySelect").value;
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/sale/registrations-list?pageNo=" + (parseInt(pageNo) + 1)+"&";
    
    var filters = [];

    if(searchValue){
        filters.push("searchValue=" + searchValue);
    }
    if (validFrom) {
        filters.push("validFrom=" + validFrom);
    } 
    if (validTo) {
        filters.push("validTo=" + validTo);
    }
    if (status) {
        filters.push("status=" + status);
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
    var validFrom = document.getElementById("validFromSelect").value;
    var validTo = document.getElementById("validToSelect").value;
    var status = document.getElementById("statusSelect").value;
    var sortBy=document.getElementById("sortBySelect").value;
    var order=document.getElementById("orderSelect").value;
    var searchValue=document.getElementById("search").value;

    var url = "/sale/registrations-list?pageNo=" + (parseInt(pageNo) - 1)+"&";
    
    var filters = [];

    if(searchValue){
        filters.push("searchValue=" + searchValue);
    }
    if (validFrom) {
        filters.push("validFrom=" + validFrom);
    } 
    if (validTo) {
        filters.push("validTo=" + validTo);
    }
    if (status) {
        filters.push("status=" + status);
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
function getPricePackage() {
    var subjectId = $("#inputSubject").val();
    $.get("/sale/get-pricepackages?subjectId=" + subjectId, function(data) {
        $("#inputPricePackage").empty();
        data.forEach(function(item) {
            var option = "<option value=" + item.id + ">" + item.name + "</option>";
            $("#inputPricePackage").append(option);
            $("#listPrice").text(item.listPrice);
            $("#listPrice").text(item.listPrice);
            $("#salePrice").text(item.salePrice);
        });
    });
}
function changePrice(){
    var packageId=$("#inputPricePackage").val();
    $.get("/sale/get-pricepackage?pricePackageId="+packageId,function(data){
        $("#listPrice").text(data.listPrice);
        $("#salePrice").text(data.salePrice);
    });
}

registration-detail.js
function showPopup() {
    document.getElementById("popupForm").style.display = "block";
}
  
function hidePopup() {
    document.getElementById("popupForm").style.display = "none";
}
function change(isYours){
    document.getElementById("save-button").style.display="block";
    document.getElementById("change-button").style.display="none";
    document.getElementById("cancel-button").style.display="block";
    document.getElementById("inputNote").disabled=false;
    document.getElementById("inputStatus").disabled=false;
    if(isYours=="true"){
        document.getElementById("inputEmail").disabled=false;
        document.getElementById("inputFullName").disabled=false;
        document.getElementById("inputGender").disabled=false;
        document.getElementById("inputMobile").disabled=false;
        document.getElementById("inputSubject").disabled=false;
        document.getElementById("inputPricePackage").disabled=false;
        document.getElementById("inputValidFrom").disabled=false;
    }
}
function cancel(){
    document.getElementById("save-button").style.display="none";
    document.getElementById("change-button").style.display="block";
    document.getElementById("cancel-button").style.display="none";
    document.getElementById("inputNote").disabled=true;
    document.getElementById("inputStatus").disabled=true;
    document.getElementById("inputEmail").disabled=true;
    document.getElementById("inputFullName").disabled=true;
    document.getElementById("inputGender").disabled=true;
    document.getElementById("inputMobile").disabled=true;
    document.getElementById("inputSubject").disabled=true;
    document.getElementById("inputPricePackage").disabled=true;
    document.getElementById("inputValidFrom").disabled=true;
}
function backToParents(){
    window.location.href="/sale/registrations-list";
}
function getPricePackage() {
    var subjectId = $("#inputSubject").val();
    $.get("/sale/get-pricepackages?subjectId=" + subjectId, function(data) {
        $("#inputPricePackage").empty();
        data.forEach(function(item) {
            var option = "<option value=" + item.id + ">" + item.name + "</option>";
            $("#inputPricePackage").append(option);
        });
    });
}
function changePrice(){
    var packageId=$("#inputPricePackage").val();
    $.get("/sale/get-pricepackage?pricePackageId="+packageId,function(data){
        $("#listPrice").text(data.listPrice);
        $("#salePrice").text(data.salePrice);
    });
}
