package org.launchcode.hiitworkoutbuilder.controllers;

import org.launchcode.hiitworkoutbuilder.models.Workout;
import org.launchcode.hiitworkoutbuilder.models.data.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("select")
public class SelectWorkoutController {

    @Autowired
    WorkoutRepository workoutRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("workouts", workoutRepository.findAll());
        return "select/index";
    }

    @GetMapping("view/{workoutId}")
    public String viewWorkout(Model model, @PathVariable int workoutId) {
        Workout workout = workoutRepository.findById(workoutId).orElse(new Workout());
        model.addAttribute("workout", workout);
        model.addAttribute("exercises", workout.getExercises());
        return "select/view";
    }
}
