package com.app.match.controllers;

import com.app.match.model.Match;
import com.app.match.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;


    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    public Optional<Match> getMatchById(@PathVariable Long id) {
        return matchService.getMatchOddsById(id);
    }

    @PostMapping("/add")
    public String addMatch(@RequestBody Match match) {
        try {
            matchService.addMatch(match);
            return "Match saved successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @DeleteMapping("/delete/{id}")
    public String deleteMatch(@PathVariable Long id) {
        try {
            matchService.deleteMatch(id);
            return String.format("Match with id %s deleted", id);
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Fail to delete match with id %s",id);
        }
    }

    @PutMapping("/update/{id}")
    public String updateMatchTime(@PathVariable Long id,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime) {
        try {
            matchService.updateMatchDateAndTime(id, localDateTime);
            return String.format("Time of match with id %s updated", id);
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Fail to update time of match with id %s",id);
        }
    }

}
