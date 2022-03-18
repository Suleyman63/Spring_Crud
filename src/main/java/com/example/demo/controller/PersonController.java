package com.example.demo.controller;


import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/person")
public class PersonController {

    public PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService=personService;
    }

    @GetMapping(path = "/person")
    public List<Person> getAll() {
        return personService.getAllPerson();
    }


    @PostMapping(path = "/add")
    public Person addNewPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @GetMapping(path = "/person/{id}")
    public Optional<Person> getById(@PathVariable Integer id) {
        return personService.getPersonById(id);
    }


    @DeleteMapping(path = "/person/delete/{id}")
    public String delById(@PathVariable Integer id){
        return personService.deletePersonById(id);
    }

@PutMapping(path = "person/update")
    public Person updateById(@RequestBody Person newPerson){
        return personService.updateById(newPerson);
    }

    @PatchMapping(path = "/person/update/{id}")
   public Person updTeilById(@PathVariable Integer id, @Validated @RequestBody Person newPerson){
        return personService.updateTeilById(id, newPerson);

   }

}
