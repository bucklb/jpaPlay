package com.bucklb.jpaPlay.controller;

import com.bucklb.jpaPlay.exception.ExceptionalException;
import com.bucklb.jpaPlay.repository.PersonRepository;
import com.bucklb.jpaPlay.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//
// Controller for persons API
//
// GET  : /persons              - list all the people
// POST : /persons              - add new person
// GET  : /persons/{id}         - get specific person
// PUT  : /persons/{id}         - amend specific person
// DEL  : /persons/{id}         - delete specific person (except fails if person has texts)
//



@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;


    // Get All Persons
    @GetMapping("/persons")
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    // Create a new Person
    @PostMapping("/persons")
    public Person createPerson(@Valid @RequestBody Person person){
        return personRepository.save(person);
    }

    // Get a Single Person
    @GetMapping("/persons/{id}")
    public Person getPersonById(@PathVariable(value = "id") Long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new ExceptionalException("Person", "id", personId));
    }

    // Update a Person
    @PutMapping("/persons/{personId}")
    public Person updatePerson(@PathVariable Long personId, @Valid @RequestBody Person postRequest) {
        return personRepository.findById(personId).map(person -> {
            person.setFirstName(postRequest.getFirstName());
            person.setLastName(postRequest.getLastName());
            person.setEmail(postRequest.getEmail());
            return personRepository.save(person);
        }).orElseThrow(() -> new ExceptionalException("PersonId " + personId + " not found","filler","filler"));
    }

    // Delete a Person
    @DeleteMapping("/persons/{personId}")
    public ResponseEntity<?> deletePerson(@PathVariable Long personId) {
        return personRepository.findById(personId).map(person -> {
            personRepository.delete(person);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ExceptionalException("personId " + personId + " not found","",""));
    }



}
