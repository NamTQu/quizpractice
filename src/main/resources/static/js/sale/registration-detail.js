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
