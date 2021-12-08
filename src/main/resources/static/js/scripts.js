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

// timer for workouts
function countdownTimer(duration, display, exerciseNumber) {
    let timer = duration;
    let endMessage = "Workout Complete!"
    let i = 0;

    setInterval(function () {
        seconds = parseInt(timer % 60, 10);
        seconds = seconds < 10 ? "0" + seconds : seconds;
        display.textContent = seconds;

        if (--timer < 0) {
            i++;
            timer = duration;
        }

        if (i === exerciseNumber) {
            display.textContent = "Workout Complete!";
            clearInterval(timer);
        }

        console.log(i);

    }, 1000);
}
