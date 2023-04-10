package mybootapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import mybootapp.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long>{

}
