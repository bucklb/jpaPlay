package com.bucklb.jpaPlay.controller;


//
// Looking at texts by person, so use a bespoke controller
//

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


@RestController
public class TextController {

    @Autowired
    private TextRepository textRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/persons/{personId}/texts")
    public Page<Text> getAllTextsByPersonId(@PathVariable(value="personId") Long personId,
                                            Pageable pageable){

        System.out.println("Trying to get comments");

        return textRepository.findByPersonId(personId,pageable);
        // Stuck with convention?          return textRepository.findByMagic(personId,pageable);
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
