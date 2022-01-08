package org.launchcode.hiitworkoutbuilder.controllers;

import org.launchcode.hiitworkoutbuilder.models.User;
import org.launchcode.hiitworkoutbuilder.models.Workout;
import org.launchcode.hiitworkoutbuilder.models.data.ExerciseRepository;
import org.launchcode.hiitworkoutbuilder.models.data.UserRepository;
import org.launchcode.hiitworkoutbuilder.models.data.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping
public class CreateWorkoutController {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("create")
    public String index (Model model, HttpSession session) {
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        model.addAttribute("exercises", user.getExercises());
        model.addAttribute(new Workout());
        return "create/index";
    }

    @PostMapping("create")
    public String processCreateWorkout (@ModelAttribute @Valid Workout newWorkout, Errors errors, Model model, HttpSession session) {

        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());

        if (errors.hasErrors()) {
            model.addAttribute("exercises", user.getExercises());
            return "create/index";
        }

        newWorkout.setUser(user);
        workoutRepository.save(newWorkout);
        model.addAttribute("workouts", user.getWorkouts());
        return "select/index";
    }

}
