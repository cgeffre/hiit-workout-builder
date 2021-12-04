package org.launchcode.hiitworkoutbuilder.controllers;

import org.launchcode.hiitworkoutbuilder.models.Workout;
import org.launchcode.hiitworkoutbuilder.models.data.ExerciseRepository;
import org.launchcode.hiitworkoutbuilder.models.data.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping
public class CreateWorkoutController {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @GetMapping("create")
    public String index (Model model) {
        model.addAttribute("exercises", exerciseRepository.findAll());
        model.addAttribute(new Workout());
        return "create/index";
    }

    @PostMapping("create")
    public String processCreateWorkout (@ModelAttribute @Valid Workout newWorkout, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("exercises", exerciseRepository.findAll());
            return "create/index";
        }

        workoutRepository.save(newWorkout);
            return "redirect:";
    }

}
