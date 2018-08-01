package com.arthur.demo.repository;

import com.arthur.demo.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    public Person findByEmail(String email);

}
