package com.example.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/match")
public class MatchController {

    @Autowired
    private MatchRepository matchRepo;

    @GetMapping
    public List<Match> getAllMatches() {
        return matchRepo.findAll();
    }

    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable Long id) {
        return matchRepo.findById(id).get();
    }

    @PostMapping("/addmatch")
    public String addMatch(@RequestBody Match match) {
        try {
            matchRepo.save(match);
            return String.format("Match saved: %s", match.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Fail to save match: %s because of %s", match.toString(), e.getMessage());
        }
    }

    @DeleteMapping("/deletematch/{id}")
    public String deleteMatch(@PathVariable Long id) {
        try {
            matchRepo.deleteById(id);
             return String.format("Match with id %s deleted", id);
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Fail to delete match with id %s",id);
        }
    }

}
