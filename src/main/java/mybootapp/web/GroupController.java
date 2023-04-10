package mybootapp.web;

import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mybootapp.model.Person;
import mybootapp.model.Group;
import mybootapp.repo.GroupRepository;
import mybootapp.repo.PersonRepository;

@Controller
@RequestMapping("/group")
public class GroupController {
	
    @PersistenceContext
    EntityManager entityManager;
    
    @Autowired
	GroupRepository groupRepo;

    @Autowired
	PersonRepository personRepo;

    @Autowired
    PasswordEncoder encoder;

    protected final Log logger = LogFactory.getLog(getClass());
    
	@PostConstruct
	public void init() {
		Group g1 = new Group(5L, "azer", null);
		Group g2 = new Group(6L, "azer2", null);
		System.out.println(g1.getId());
		System.out.println(g2.getId());
    	g1 = groupRepo.save(g1);
    	g2 = groupRepo.save(g2);
		System.out.println(g1.getId());
		System.out.println(g2.getId());
        Random r = new Random();
        for(int i = 0; i < 10; i++) {
            Person p = new Person("c" + (10000000 + i), "Xuan Viet", "CONG", "vietcxhn1@gmail.com", "github.com/vietcxhn", "1999-08-27", encoder.encode("aaa"), null, Set.of("ADMIN", "USER"));
            if(r.nextInt(2) == 0) {
            	g1.addPerson(p);
            }
        	else {
            	g2.addPerson(p);
            }

            personRepo.save(p);
        }
	}

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listGroups() {
        Collection<Group> products = groupRepo.findAll();
        return new ModelAndView("groupList", "groups", products);
    }
    

    @ModelAttribute("groups")
    Collection<Group> groups() {
        return groupRepo.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView listProducts(@PathVariable Long id) {
    	EntityGraph entityGraph = entityManager.getEntityGraph("group-entity-graph");
		
    	Map<String, Object> properties = new HashMap<>();
    	properties.put("javax.persistence.fetchgraph", entityGraph);
    	Group group = entityManager.find(Group.class, id, properties);
        return new ModelAndView("personInGroupList", "group", group);
    }
}
