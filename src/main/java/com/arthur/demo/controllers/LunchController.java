package com.arthur.demo.controllers;


import com.arthur.demo.model.LunchAtWork;
import com.arthur.demo.model.Person;
import com.arthur.demo.service.LunchServices;
import com.arthur.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://192.168.86.52:3000")
//@CrossOrigin(origins = "http://192.168.86.37:3000")
@RestController
@RequestMapping("/lunch")
public class LunchController {

    @Autowired
    LunchServices lunchService;

    @Autowired
    PersonService personService;

    @GetMapping("/list")
    public synchronized List<LunchAtWork> getLunchList() {

        return this.lunchService.getLunch();
    }

    @GetMapping("/dates")
    public synchronized List<String> getLunchDate() {

        return this.lunchService.getLunchDate();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LunchAtWork createLunch(@RequestBody LunchAtWork lunch) throws Exception {

        if (!(lunchService.existLunch(lunch.getId()))) {
            if(personService.existPerson(lunch.getPerson().getEmail())){
                this.lunchService.addLunch(lunch);
            }
            else {
                this.personService.addPerson(lunch.getPerson());
                this.lunchService.addLunch(lunch);
            }

        } else {
            throw new Exception("lunch with " + lunch.getId() + " exists");
        }
        return lunch;
    }


    @RequestMapping(value = "id", method = RequestMethod.GET)
    public LunchAtWork displayLunch(@RequestParam String id) {

        String message = null;
        if (lunchService.existLunch(id)){
            return this.lunchService.displayLunch(id);
        }
        else{
            message = "' " + id + " ' does'nt exist in the DB";
        }
        return this.lunchService.displayLunch(id);
    }


    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public LunchAtWork setLunch(@RequestBody LunchAtWork lunch) {
        String id = lunch.getPerson().getId();
        Person person = personService.displayPersonById(id);
        lunch.setPerson(person);
        String message = null;
        LunchAtWork lunchUpdated = this.lunchService.updateLunch(lunch);

        if (lunchUpdated != null){
            message = "Data have been updated";
        }
        else{
            message = "' " + lunch.getId() + " ' does'nt exist in the DB";
        }
        return lunchUpdated;
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void removeLunch(@PathVariable("id") String id) {
        lunchService.deleteLunch(id);
    }


    @RequestMapping(value = "plat", method = RequestMethod.GET)
    public List<LunchAtWork> displayByPlat(String plat){
        return this.lunchService.findByName(plat);
    }




}
