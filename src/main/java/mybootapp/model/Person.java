package mybootapp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    private String id;
    
    @Basic
    private String firstName;
    
    @Basic
    private String lastName;
    
    @Basic
    private String email;
    
    @Basic
    private String website;
    
    @Basic
    private String dateOfBirth;
    
    @Basic
    private String password;
    
    @ManyToOne()
    @ToString.Exclude
    private Group group;

    @ElementCollection(fetch = FetchType.EAGER)
    Set<String> roles;
}
