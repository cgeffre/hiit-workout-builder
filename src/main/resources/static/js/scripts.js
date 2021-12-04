// validates that an empty value is not entered into create exercise form
function validateNumber() {
    let text;
    if (document.createWorkout.secondsDuration.value === "") {
        text = "Enter a number"
        document.getElementById("numError").innerHTML = text;
        event.preventDefault();
        return false;
    }
    return true;
}