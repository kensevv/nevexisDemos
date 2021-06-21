package com.frantishex.lwr.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.frantishex.lwr.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> 
{
	@Query(nativeQuery=true, value="select * from person where name like concat('%',:pNamePart,'%') or egn like concat('%',:pNamePart,'%') or phone like concat ('%',:pNamePart,'%') order by name limit 100 ")
    List<Person> findByNamePart(@Param("pNamePart")String pNamePart);

	@Query(nativeQuery=true, value="select * from person where name like concat('%',:pNamePart,'%') order by name limit 100 ")
    List<Person> findByName(@Param("pNamePart")String pNamePart);
	
	@Query(nativeQuery=true, value="select * from person where name = :name order by name limit 100 ")
    List<Person> findByWholeName(@Param("name")String name);

	@Query(nativeQuery=true, value="select * from person where egn = :pNamePart order by name limit 100 ")
    List<Person> findByEGN(@Param("pNamePart")String pNamePart);
	
	@Query(nativeQuery=true, value="select * from person where phone = :phone order by name limit 100 ")
    List<Person> findByPhone(@Param("phone")String phone);
}