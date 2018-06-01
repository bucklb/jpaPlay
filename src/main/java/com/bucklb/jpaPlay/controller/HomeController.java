package com.bucklb.jpaPlay.controller;

import com.bucklb.jpaPlay.exception.ExceptionalException;
import com.bucklb.jpaPlay.model.Person;
import com.bucklb.jpaPlay.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    PersonRepository personRepository;
    private static final String appName = "ThymeleafTour";

    @GetMapping("/")
    public String home(Model model,
                       @RequestParam(value = "name", required = false,
                               defaultValue = "Guest") String name) {

        model.addAttribute("name", name);
        model.addAttribute("title", appName);
        return "home";

    }


    //
    //
    // Perhaps want to use something like this to grab data from mySql (via a GET .. /persons/3 type request)
    // in which case ending up revisiting much of the quote dicking around
    //
    //
    @GetMapping("/bucklb")
    public String bucklb(Model model,
                         @RequestParam(value = "name", required = false,
                                 defaultValue = "Guest") String name) {

        Long personId;
        personId=3L;

        System.out.println("Looking fo r person with id = "+personId);

        // Grab a person from repo ...
        Person person =personRepository.findById(personId)
                .orElseThrow(() -> new ExceptionalException("Person", "id", personId));

        String showName="n/a";
        String showEmail="n/a";

        if (person != null){
            showName=person.getFirstName() + " "+ person.getLastName();
            showEmail=person.getEmail();
        }

        System.out.println("Person details = " + showEmail + " & " + showEmail);

        model.addAttribute("name", showName);
        model.addAttribute("email", showEmail);

        return "bucklb";

    }

    //
    // If we use "bucklb" it's a bit limiting ...
    //
    @GetMapping("/greet/{id}")
    public String greetPersonById(Model model,@PathVariable(value = "id") Long personId) {

        System.out.println("Looking to greet a person with id = "+personId);

        // Grab a person from repo ...
        Person person =personRepository.findById(personId)
                .orElseThrow(() -> new ExceptionalException("Person", "id", personId));

        String showName="n/a";
        String showEmail="n/a";

        if (person != null){
            showName=person.getFirstName() + " "+ person.getLastName();
            showEmail=person.getEmail();
        }

        System.out.println("Person details = " + showEmail + " & " + showEmail);

        model.addAttribute("name", showName);
        model.addAttribute("email", showEmail);

        return "greet";

    }





}
