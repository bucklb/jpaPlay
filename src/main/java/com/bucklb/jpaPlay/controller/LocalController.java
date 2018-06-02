package com.bucklb.jpaPlay.controller;



import com.bucklb.jpaPlay.exception.ExceptionalException;
import com.bucklb.jpaPlay.model.Person;
import com.bucklb.jpaPlay.model.Text;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


//
// homeController relies on access to the repos.  Can we just use our own endpoints to achieve the same?
//

@Controller
@RequestMapping("/local")
public class LocalController {

    @GetMapping("/{id}")
    public String greetPersonById(Model model, @PathVariable(value = "id") Long personId, Pageable pageable) {

        // Hopefully grab the person from local endpoint (so we need know nothing of the repo). Extricate from response
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Person> response = restTemplate.getForEntity(
                "http://localhost:8090/persons/"+personId,
                Person.class);
        Person person = response.getBody();

        // Same old same old.
        String showName="n/a";
        String showEmail="n/a";
        if (person != null){
            showName=person.getFirstName() + " "+ person.getLastName();
            showEmail=person.getEmail();
        }

        System.out.println("Person details = " + showEmail + " & " + showEmail);
        model.addAttribute("name", showName);
        model.addAttribute("email", showEmail);

        
        // Around here we may want to get the accompanying details




/*
        Page<Text> texts=textRepository.findByPersonId(personId,pageable);
        model.addAttribute("texts",texts);
*/

        return "greet";

    }



}
