package mybootapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mybootapp.model.Person;



public interface PersonRepository extends JpaRepository<Person, String> {

}