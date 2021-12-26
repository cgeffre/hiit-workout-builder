package org.launchcode.hiitworkoutbuilder.controllers;

import org.launchcode.hiitworkoutbuilder.models.Exercise;
import org.launchcode.hiitworkoutbuilder.models.Workout;
import org.launchcode.hiitworkoutbuilder.models.data.ExerciseRepository;
import org.launchcode.hiitworkoutbuilder.models.data.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("select")
public class SelectWorkoutController {

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("workouts", workoutRepository.findAll());
        return "select/index";
    }

    @GetMapping("view/{workoutId}")
    public String viewWorkout(Model model, @PathVariable int workoutId) {
        Workout workout = workoutRepository.findById(workoutId).orElse(new Workout());
        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.addAll(workout.getExercises());
        ArrayList<Exercise> exercises = new ArrayList<>();
        workout.addRestsToWorkout(exerciseList, workout, exercises);
        model.addAttribute("workout", workout);
        model.addAttribute("exercises", workout.getExercises());
        model.addAttribute("totalDuration", workout.totalDurationOfWorkout(exercises, workout));
        return "select/view";
    }

    @GetMapping("workout/{workoutId}")
    public String doWorkout(Model model, @PathVariable int workoutId) {
        Workout workout = workoutRepository.findById(workoutId).orElse(new Workout());
        ArrayList<Exercise> exerciseList = workout.exerciseRandomizer(workout);
        ArrayList<Exercise> exercises = new ArrayList<>();
        workout.addRestsToWorkout(exerciseList, workout, exercises);
        model.addAttribute("workout", workout);
        model.addAttribute("exercises", exercises);
        return "select/workout";
    }

    @GetMapping("edit/{workoutId}")
    public String editWorkout(Model model, @PathVariable int workoutId) {
        Workout workout = workoutRepository.findById(workoutId).orElse(new Workout());
        Workout updateWorkout = new Workout();
        model.addAttribute("workout", workout);
        model.addAttribute("updateWorkout", updateWorkout);
        model.addAttribute("exercises", exerciseRepository.findAll());
        return "select/edit";
    }

    @PostMapping("edit/{workoutId}")
    public String processEditWorkout(@ModelAttribute @Valid Workout updateWorkout, @PathVariable int workoutId, Errors errors, Model model) {
        if (errors.hasErrors()) {
            Workout workout = workoutRepository.findById(workoutId).orElse(new Workout());
            model.addAttribute("workout", workout);
            model.addAttribute("updateWorkout", updateWorkout);
            return "select/edit";
        }

        Workout workout = workoutRepository.findById(workoutId).orElse(new Workout());
        workout.setName(updateWorkout.getName());
        workout.setExercises(updateWorkout.getExercises());
        workout.setRestInterval(updateWorkout.getRestInterval());
        workout.setSecondsDuration(updateWorkout.getSecondsDuration());
        workoutRepository.save(workout);
        model.addAttribute("updateWorkout", updateWorkout);
        return "redirect:..";
    }

    @GetMapping("delete/{workoutId}")
    public String deleteWorkout(Model model, @PathVariable int workoutId) {
        Workout workout = workoutRepository.findById(workoutId).orElse(new Workout());
        ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.addAll(workout.getExercises());
        model.addAttribute("totalDuration", workout.totalDurationOfWorkout(exercises, workout));
        model.addAttribute("workout", workout);
        model.addAttribute("exercises", exercises);
        return "select/delete";
    }

    @PostMapping("delete/{workoutId}")
    public String processDeleteWorkout(@PathVariable int workoutId) {
        Workout workout = workoutRepository.findById(workoutId).orElse(new Workout());
        workoutRepository.delete(workout);
        return "redirect:..";
    }
}
