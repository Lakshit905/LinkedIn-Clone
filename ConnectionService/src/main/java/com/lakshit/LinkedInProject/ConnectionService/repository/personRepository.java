package com.lakshit.LinkedInProject.ConnectionService.repository;

import com.lakshit.LinkedInProject.ConnectionService.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface personRepository extends Neo4jRepository<Person,Long> {

    Optional<Person> findByUserId(Long userId);

    @Query("match (A:Person) -[:CONNECTED_TO]-(B:Person) " +
            "where A.userId= $userId " +
            "return B")
    List<Person> getFirstDegreeConnections(Long userId);
}
