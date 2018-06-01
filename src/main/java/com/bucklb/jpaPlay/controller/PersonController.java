package com.bucklb.jpaPlay.controller;

import com.bucklb.jpaPlay.exception.ExceptionalException;
import com.bucklb.jpaPlay.repository.PersonRepository;
import com.bucklb.jpaPlay.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    // Get All Persons
    @GetMapping("/persons")
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    // Create a new Note

    // Get a Single Note
    @GetMapping("/persons/{id}")
    public Person getPersonById(@PathVariable(value = "id") Long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new ExceptionalException("Person", "id", personId));
    }

    // Update a Note

    // Delete a Note



}
