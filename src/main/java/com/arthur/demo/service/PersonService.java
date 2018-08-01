package com.arthur.demo.service;

import com.arthur.demo.model.Person;
import com.arthur.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.debugger.AddressException;

import javax.mail.internet.InternetAddress;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getPersonList(){

        return this.personRepository.findAll();
    }

    public void addPerson(Person person){

        if (isValidEmailAddress(person.getEmail()))
            this.personRepository.save(person);
    }

    public Person deletePerson(String emailPerson){

        Person byEmail = personRepository.findByEmail(emailPerson);
        if(byEmail != null){
            this.personRepository.delete(byEmail);
        }
        return byEmail;
    }

    public Person updatePerson(Person person){

        Person personToUpdate = personRepository.findByEmail(person.getEmail());

        if(personToUpdate != null){
            personToUpdate.setFirstname(person.getFirstname());
            personToUpdate.setLastname(person.getLastname());
            personToUpdate.setOccupation(person.getOccupation());
            personToUpdate.setColor(person.getColor());
            personToUpdate.setWelcomeMsg(person.getWelcomeMsg());
            personToUpdate.setImage(person.getImage());

            this.personRepository.save(personToUpdate);
        }
        else{
            personToUpdate = person;
            this.personRepository.save(personToUpdate);
        }
        return  personToUpdate;
    }

    public Person displayPerson(String email){

        return this.personRepository.findByEmail(email);
    }

    /*************/

    //verifie l'existence d'une personne dans la bd
    public boolean existPerson(String email){

        return this.personRepository.findByEmail(email) != null;
    }

    //verifier la validité de l'email
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        } catch (javax.mail.internet.AddressException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Person displayPersonById(String id){
        return this.personRepository.findById(id).get();
    }

}
