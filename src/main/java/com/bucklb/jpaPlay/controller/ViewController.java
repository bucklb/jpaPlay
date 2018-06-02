package com.bucklb.jpaPlay.controller;

import com.bucklb.jpaPlay.exception.ExceptionalException;
import com.bucklb.jpaPlay.model.Person;
import com.bucklb.jpaPlay.model.Text;
import com.bucklb.jpaPlay.repository.PersonRepository;
import com.bucklb.jpaPlay.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


// MVC (rather than API) endpoints
// Using repo "directly" so we need to have details injected
//
// GET  : /view                : get list of all persons and display them (rather than return them)
// GET  : /view/{id}           : get person and texts and display them
// GET  : /view/bucklb?name=<> : just pretty way of acknowledging parameter passed in
//

@Controller
@RequestMapping("/view")
public class ViewController {

    private static final String appName = "ThymeleafTour";

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TextRepository textRepository;

    //
    // Leave a spare endpoint (which happens to be from the tutorial in effect
    //
    @GetMapping("/bucklb")
    public String bucklb(Model model,
                         @RequestParam(value = "name", required = false,
                                 defaultValue = "Guest") String name) {

        model.addAttribute("name", name);
        model.addAttribute("email", "n/a");

        return "bucklb";
    }

    //
    // If we use "bucklb" it's a bit limiting ...
    //
    @GetMapping("/{id}")
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

//        Page<Text> texts=textRepository.findByPersonId(personId,pageable);
        List<Text> texts=textRepository.findByPersonId(personId,pageable);
        model.addAttribute("texts",texts);

        return "greet";
    }

    //
    // If we get /view/persons then populate a reasonable table to display (rather than the rather bald json in api)
    //
    @GetMapping(value = {""})
    public String viewPersonsAsTable(Model model)
    {
        List<Person> persons = personRepository.findAll();
        model.addAttribute("persons", persons);

        // The html to direct things to
        return "persons";
    }








}
