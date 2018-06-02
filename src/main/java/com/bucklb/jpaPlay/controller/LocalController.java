package com.bucklb.jpaPlay.controller;

import com.bucklb.jpaPlay.model.Person;
import com.bucklb.jpaPlay.model.Text;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


// MVC (rather than API) endpoints
// ... homeController relies on access to the repos.  Can we just use our own endpoints to achieve the same?
//
// GET  : /local/{id}           : get person and texts and display them
// GET  : /local                : get list of all persons and display them (rather than return them)
//

@Controller
@RequestMapping("/local")
public class LocalController {

    @GetMapping("/{id}")
    public String greetPersonById(Model model, @PathVariable(value = "id") Long personId, Pageable pageable) {

        // Get the person from an ENDPOINT rather than directly from a repo
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Person> response = restTemplate.getForEntity(
                "http://localhost:8090/persons/"+personId,
                Person.class);
        Person person = response.getBody();

        // Not sure this is meaningful, as exception will likely have been raised anyway
        String showName="n/a";
        String showEmail="n/a";
        if (person != null){
            showName=person.getFirstName() + " "+ person.getLastName();
            showEmail=person.getEmail();
        }

        // Grab the texts VIA ANOTHER ENDPOINT rather than direct from repo.  Pull the payload out via getBody
        restTemplate=new RestTemplate();
        ResponseEntity<Text[]> textsResponse = restTemplate.getForEntity(
                "http://localhost:8090/persons/"+personId+"/texts",
                Text[].class);
        Text[] texts=textsResponse.getBody();


        // Pass the attributes we want to display voa model
        model.addAttribute("name", showName);
        model.addAttribute("email", showEmail);
        model.addAttribute("texts",texts);

        return "greet";
    }

    @GetMapping("")
    public String displayPersons(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Person[]> personsResponse = restTemplate.getForEntity(
                "http://localhost:8090/persons",
                Person[].class);
        Person[] persons=personsResponse.getBody();
        model.addAttribute("persons",persons);
        return "persons";
    }

}
