function validateForm() {
    var checkboxes = document.getElementsByClassName('checkbox');
    var isChecked = false;

    for(var i = 0; i < checkboxes.length; i++) {
        if(checkboxes[i].checked) {
            isChecked = true;
            break;
        }
    }

    if(!isChecked) {
        alert("Please check at least one checkbox.");
        return false; // Prevent form submission
    }

    return true; // Allow form submission
}
