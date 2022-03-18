package com.example.demo.service;


import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    public static PersonRepository personRepository;


    // dependency injection
    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;

    }

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    public Person addPerson(Person person){
        return personRepository.save(person);
    }

    public Optional<Person> getPersonById(Integer id){
        return personRepository.findById(id);
    }

    public String deletePersonById(Integer id){

        if (personRepository.findById(id) == null){
            throw new IllegalArgumentException(id + "not exist");
        }
        personRepository.deleteById(id);
        return id + "deleted";
    }


    public Person updateById(Person newPerson){

        Person oldPerson= personRepository.findById(newPerson.getId()).
                orElseThrow(()-> new IllegalStateException(newPerson.getId() + "does can find"));
        return personRepository.save(newPerson);
    }


    public Person updateTeilById(Integer id, @RequestBody Person newPerson){

        Person oldPerson= personRepository.findById(id).
                orElseThrow(()-> new IllegalStateException(id + "does can find"));

        if ((newPerson.getName() != null)){
            oldPerson.setName(newPerson.getName());
        }

        if ((newPerson.getSurname() != null)){
            oldPerson.setSurname(newPerson.getSurname());
        }

        if ((newPerson.getAge() != 0)){
            oldPerson.setAge(newPerson.getAge());
        }

        return personRepository.save(oldPerson);
    }

}
