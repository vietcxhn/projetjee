package mybootapp.web;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mybootapp.model.Group;
import mybootapp.model.Person;
import mybootapp.model.Product;
import mybootapp.repo.PersonRepository;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
	PersonRepository personRepo;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String personView(@PathVariable String id) {
        return "personView";
    }

    
    @ModelAttribute("person")
    public Person person(@PathVariable String id) {
    	return personRepo.getById(id);
    }
}
