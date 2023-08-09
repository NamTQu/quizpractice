function showPopup() {
    document.getElementById("popupForm").style.display = "block";
}
  
function hidePopup() {
    document.getElementById("popupForm").style.display = "none";
}
function change(){
    document.getElementById("save-button").style.display="block";
    document.getElementById("change-button").style.display="none";
    document.getElementById("cancel-button").style.display="block";
    document.getElementById("inputRole").disabled=false;
    document.getElementById("inputStatus").disabled=false;
}
function cancel(){
    document.getElementById("save-button").style.display="none";
    document.getElementById("change-button").style.display="block";
    document.getElementById("cancel-button").style.display="none";
    document.getElementById("inputRole").disabled=true;
    document.getElementById("inputStatus").disabled=true;
}
function backToParents(){
    window.location.href="/admin/user-list";
}