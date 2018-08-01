package com.arthur.demo.repository;

import com.arthur.demo.model.LunchAtWork;
import com.arthur.demo.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LunchRepository extends MongoRepository<LunchAtWork, String> {
    public LunchAtWork findByPerson(Person person);
}
