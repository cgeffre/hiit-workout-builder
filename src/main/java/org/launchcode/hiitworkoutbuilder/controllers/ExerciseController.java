package org.launchcode.hiitworkoutbuilder.controllers;

import org.launchcode.hiitworkoutbuilder.models.Exercise;
import org.launchcode.hiitworkoutbuilder.models.data.ExerciseRepository;
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
@RequestMapping("exercises")
public class ExerciseController {

    @Autowired
    ExerciseRepository exerciseRepository;

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

}
