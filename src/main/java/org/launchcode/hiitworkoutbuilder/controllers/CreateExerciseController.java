package org.launchcode.hiitworkoutbuilder.controllers;

import org.launchcode.hiitworkoutbuilder.models.Exercise;
import org.launchcode.hiitworkoutbuilder.models.User;
import org.launchcode.hiitworkoutbuilder.models.data.ExerciseRepository;
import org.launchcode.hiitworkoutbuilder.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("exercises")
public class CreateExerciseController {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String index(Model model, HttpSession session) {
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.addAll(user.getExercises());
        model.addAttribute("exercises", user.getExercises());
        model.addAttribute(new Exercise());
        return "exercises/index";
    }

    @PostMapping
    public String processAddExercise(@ModelAttribute @Valid Exercise newExercise, Errors errors, Model model, HttpSession session) {

        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());

        if (errors.hasErrors()) {
            model.addAttribute("exercises", user.getExercises());
            return "exercises/index";
        }
        newExercise.setUser(user);
        exerciseRepository.save(newExercise);
        model.addAttribute("exercises", user.getExercises());
        return "exercises/index";
    }

    @GetMapping("edit/{exerciseId}")
    public String editExercise(Model model, @PathVariable int exerciseId, HttpSession session) {
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(new Exercise());
        if (exercise.getUser().getId() != user.getId()) {
            return "redirect:..";
        }
        model.addAttribute("exercise", exercise);
        return "exercises/edit";
    }

    @PostMapping("edit/{exerciseId}")
    public String processEditExercise(@ModelAttribute @Valid Exercise updateExercise, @PathVariable int exerciseId,
                                      Errors errors, Model model, HttpSession session) {
        if (errors.hasErrors()) {
            Exercise exercise = exerciseRepository.findById(exerciseId).orElse(new Exercise());
            model.addAttribute("exercise", exercise);
            return "exercises/edit";
        }

        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(new Exercise());
        exercise.setName(updateExercise.getName());
        exerciseRepository.save(exercise);
        model.addAttribute("exercises", user.getExercises());
        return "redirect:..";
    }

    @GetMapping("delete/{exerciseId}")
    public String deleteExercise(Model model, @PathVariable int exerciseId, HttpSession session) {
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(new Exercise());
        if (exercise.getUser().getId() != user.getId()) {
            return "redirect:..";
        }
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
