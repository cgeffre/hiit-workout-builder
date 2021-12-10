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
    let counter = 0;
    document.getElementById(counter).hidden = false;

    setInterval(function () {
        seconds = parseInt(timer % 100, 10);
        seconds = seconds < 10 ? "0" + seconds : seconds;
        display.textContent = seconds;

        if (counter === exerciseNumber) {
            clearInterval(timer);
            document.getElementById(counter-1).hidden = true;
            return display.textContent = "Workout Complete!";
        }

        if (--timer < 0) {
            counter++;
            timer = duration;
        }

        if (timer === duration) {
            if (counter < exerciseNumber) {
                document.getElementById(counter-1).hidden = true;
                document.getElementById(counter).hidden = false;
            }
        }

    }, 1000);
}
