package dist.ai.backend.controllers;

import dist.ai.backend.database.VoteContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @Autowired
    VoteContext voteContext;

    @PostMapping("/new")
    public ResponseEntity<String> New() {
        return new ResponseEntity<>("{\"uid\":12}", HttpStatus.CREATED);
    }

    @GetMapping("/status")
    public ResponseEntity<String> Status() {
        return new ResponseEntity<>("status OK", HttpStatus.OK);
    }
}
