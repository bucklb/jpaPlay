package com.bucklb.jpaPlay.controller;



import com.bucklb.jpaPlay.exception.ExceptionalException;
import com.bucklb.jpaPlay.model.Person;
import com.bucklb.jpaPlay.model.Text;
import com.bucklb.jpaPlay.repository.PersonRepository;
import com.bucklb.jpaPlay.repository.TextRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


//
// Looking at texts by person, so use a bespoke controller : Text API
//
//  GET  : /persons/{id}/texts  : return a LIST (array) of texts, and NOT a page thereof
//  POST : /persons/{id}/texts  : create a new text for a given person
//


@RestController
public class TextController {

    @Autowired
    private TextRepository textRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/persons/{personId}/texts")
//    public Page<Text> getAllTextsByPersonId(@PathVariable(value="personId") Long personId,
    public List<Text> getAllTextsByPersonId(@PathVariable(value="personId") Long personId,
                                            Pageable pageable){
        // Want to inspect the contents.
//        Page<Text> response=textRepository.findByPersonId(personId,pageable);
//        List<Text> texts=response.getContent();
        List<Text> texts=textRepository.findByPersonId(personId,pageable);

//        return response;
        return texts;
    }

    @PostMapping("/persons/{personId}/texts")
    public Text createText(@PathVariable (value = "personId") Long personId,
                                 @Valid @RequestBody Text text) {
        return personRepository.findById(personId).map(person -> {
            text.setPerson(person);
            return textRepository.save(text);
        }).orElseThrow(() -> new ExceptionalException("PersonId " + personId + " not found","filler","filler"));
    }

}
