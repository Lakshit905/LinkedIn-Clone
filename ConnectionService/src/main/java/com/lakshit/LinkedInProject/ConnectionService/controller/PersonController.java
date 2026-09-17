package com.lakshit.LinkedInProject.ConnectionService.controller;

import com.lakshit.LinkedInProject.ConnectionService.entity.Person;
import com.lakshit.LinkedInProject.ConnectionService.service.PersonService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/connections")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{userId}/first-degree")
    public ResponseEntity<List<Person>> getFirstDegreeConnectionsOfUser(@PathVariable Long userId){
        List<Person> persons = personService.getFirstDegreeConnectionsOfUser(userId);
        return ResponseEntity.ok(persons);
    }
}
