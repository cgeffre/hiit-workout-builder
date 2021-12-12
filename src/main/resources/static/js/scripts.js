// validates that empty values are not entered for numeric fields when creating a workout
function validateNumbers() {
    let text;
    if ((document.createWorkout.secondsDuration.value === "") || (document.createWorkout.restInterval.value === "")) {
        text = "Enter a number for all numeric fields"
        document.getElementById("numError").innerHTML = text;
        event.preventDefault();
        return false;
    }
    return true;
}

// validates length of string values for edit exercise form
function validateString() {
    let text;
    if (document.editExercise.name.value === "" || document.editExercise.name.value.length > 50) {
        text = "Name must be between 1 and 50 characters";
        document.getElementById("stringError").innerHTML = text;
        event.preventDefault();
        return false;
    }
    return true;
}

// timer for workouts; iterates through list of exercises
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