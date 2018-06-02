package com.bucklb.jpaPlay.controller;

import com.bucklb.jpaPlay.exception.ExceptionalException;
import com.bucklb.jpaPlay.model.Person;
import com.bucklb.jpaPlay.model.Text;
import com.bucklb.jpaPlay.repository.PersonRepository;
import com.bucklb.jpaPlay.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/view")
public class HomeController {

    private static final String appName = "ThymeleafTour";

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TextRepository textRepository;


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
    public String greetPersonById(Model model,@PathVariable(value = "id") Long personId, Pageable pageable) {

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

        Page<Text> texts=textRepository.findByPersonId(personId,pageable);
        model.addAttribute("texts",texts);


        return "greet";

    }
    //
    // If we get /view/persons then populate a reasonable table to display (rather than the rather bald json in api)
    //
    @GetMapping(value = {"/persons"})
    public String viewPersonsAsTable(Model model)
    {

        List<Person> persons = personRepository.findAll();
        model.addAttribute("persons", persons);

        // The html to direct things to
        return "persons";
    }








}
