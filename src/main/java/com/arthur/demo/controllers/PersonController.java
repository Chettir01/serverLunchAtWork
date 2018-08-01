package com.arthur.demo.controllers;


import com.arthur.demo.model.Person;
import com.arthur.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://192.168.86.14:3000")
@CrossOrigin(origins = "http://192.168.86.52:3000")


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * Default action for PersonManager: return all people
     *
     * @return The list of all people
     */
    @GetMapping("/list")
    public synchronized List<Person> getPersonList() {

        return this.personService.getPersonList();
    }

    /**
     * Add a person
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Person createPerson(@RequestBody Person person) throws Exception {

        System.out.println(person.getEmail());

        if (!(personService.existPerson(person.getEmail()))) {
            this.personService.addPerson(person);
        } else {
            throw new Exception("person with " + person.getEmail() + " exists");
        }
        return person;
    }

    /**
     * Display a person's profile or find a person according his id
     */
    @RequestMapping(value = "email", method = RequestMethod.GET)
    public Person displayProfile(@RequestParam String email) {

        String message = null;
        Person personToShowed = this.personService.displayPerson(email);

        if (personService.existPerson(email)){
            return personToShowed;
        }
        else{
            message = "' " + email + " ' does'nt exist in the DB";
        }
        return personToShowed;
    }

    /**
     * Update person data
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Person setProfile(@RequestBody Person person) {

        String message = null;
        Person personUpdated = this.personService.updatePerson(person);

        if (personUpdated != null){
            message = "Data have been updated";
        }
        else{
            message = "' " + person.getEmail() + " ' does'nt exist in the DB";
        }
        return personUpdated;
    }


    /**
     * Remove a person
     */
    @RequestMapping(value = "/delete/{email}", method = RequestMethod.DELETE)
    public String removePerson(@PathVariable("email") String email) {

        String message = null;
        Person personDeleted = this.personService.deletePerson(email);

        if (personDeleted != null) {
            message = "Person " + personDeleted.getFirstname() + " " + personDeleted.getLastname() + " has been deleted";
        } else {
            message = "Person " + email + " does'nt exist in the DB";
        }
        return message;
    }

    /**
     * Check a person in DB
     */
    @RequestMapping(value = "/verification/{email}", method = RequestMethod.GET)
    public boolean existPerson( String email) {

        return this.personService.existPerson(email);
    }
}
