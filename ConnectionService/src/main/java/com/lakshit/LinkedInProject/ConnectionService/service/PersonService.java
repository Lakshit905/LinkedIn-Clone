package com.lakshit.LinkedInProject.ConnectionService.service;

import com.lakshit.LinkedInProject.ConnectionService.entity.Person;
import com.lakshit.LinkedInProject.ConnectionService.repository.personRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

    private final personRepository personRepository;

    public List<Person> getFirstDegreeConnectionsOfUser(Long userId){
        log.info("getting All first degree connections of user with id: {}",userId);
        return personRepository.getFirstDegreeConnections(userId);
    }
}
