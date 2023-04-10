package mybootapp.model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NamedEntityGraph(
		  name = "group-entity-graph",
		  attributeNodes = {
		    @NamedAttributeNode("name"),
		    @NamedAttributeNode(value = "persons", subgraph = "persons-subgraph"),
		  },
		  subgraphs = {
		    @NamedSubgraph(
		      name = "persons-subgraph",
		      attributeNodes = {
		        @NamedAttributeNode("firstName"),
		        @NamedAttributeNode("lastName"),
		      }
		    )
		  }
		)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "my_group")
public class Group {

	@Id
	@GeneratedValue
	private Long id;

	@Basic
	private String name;
	
    @OneToMany(//
            fetch = FetchType.LAZY, //
            mappedBy = "group", //
            cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE } //
            )
    @ToString.Exclude  // pour éviter les boucles
    private List<Person> persons;
    
    public void addPerson(Person p) {
        if (persons == null) {
        	persons = new ArrayList<>();
        }
        persons.add(p);
        p.setGroup(this);
    }
    
}
