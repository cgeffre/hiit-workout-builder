package org.launchcode.hiitworkoutbuilder.controllers;

import org.launchcode.hiitworkoutbuilder.models.Exercise;
import org.launchcode.hiitworkoutbuilder.models.Workout;
import org.launchcode.hiitworkoutbuilder.models.data.ExerciseRepository;
import org.launchcode.hiitworkoutbuilder.models.data.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;

@Controller
@RequestMapping("exercises")
public class ExerciseController {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @GetMapping
    public String index (Model model) {
        model.addAttribute("exercises", exerciseRepository.findAll());
        model.addAttribute(new Exercise());
        return "exercises/index";
    }

    @PostMapping
    public String processAddExercise(@ModelAttribute @Valid Exercise newExercise, Errors errors, Model model) {
       if (errors.hasErrors()) {
           model.addAttribute("exercises", exerciseRepository.findAll());
           return "exercises/index";
       }

       exerciseRepository.save(newExercise);
       model.addAttribute("exercises", exerciseRepository.findAll());
       return "exercises/index";
    }

    @GetMapping("edit/{exerciseId}")
    public String editExercise(Model model, @PathVariable int exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(new Exercise());
        model.addAttribute("exercise", exercise);
        return "exercises/edit";
    }

    @PostMapping("edit/{exerciseId}")
    public String processEditExercise(@ModelAttribute @Valid Exercise updateExercise, @PathVariable int exerciseId, Errors errors, Model model) {
        if (errors.hasErrors()) {
            Exercise exercise = exerciseRepository.findById(exerciseId).orElse(new Exercise());
            model.addAttribute("exercise", exercise);
            return "exercises/edit";
        }

        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(new Exercise());
        exercise.setName(updateExercise.getName());
        exerciseRepository.save(exercise);
        model.addAttribute("exercises", exerciseRepository.findAll());
        return "redirect:..";
    }

    @GetMapping("delete/{exerciseId}")
    public String deleteExercise(Model model, @PathVariable int exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(new Exercise());
        model.addAttribute("exercise", exercise);
        return "exercises/delete";
    }

    @PostMapping("delete/{exerciseId}")
    public String processDeleteExercise(@PathVariable int exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(new Exercise());
        exercise.removeExerciseFromWorkouts();
        exerciseRepository.delete(exercise);

        return "redirect:..";
    }
}
