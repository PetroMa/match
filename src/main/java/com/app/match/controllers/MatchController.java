package com.app.match.controllers;

import com.app.match.model.Match;
import com.app.match.services.MatchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Get All Matches")
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Match by id")
    public Optional<Match> getMatchById(@ApiParam(value = "The match Id", required = true) @PathVariable Long id) {
        return matchService.getMatchOddsById(id);
    }

    @PostMapping("/add")
    @ApiOperation(value = "Add Match")
    public String addMatch(@ApiParam(value = "The match to be added with odd specifiers or without. " +
            "The specifiers must be unique.")@RequestBody Match match) {
        try {
            matchService.addMatch(match);
            return "Match saved successfully";
        } catch (Exception e) {
            return String.format("Cannot save match because of %s", e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete Match by id")
    public String deleteMatch(@ApiParam(value = "The match Id", required = true) @PathVariable Long id) {
        try {
            matchService.deleteMatch(id);
            return String.format("Match with id %s deleted", id);
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Fail to delete match with id %s",id);
        }
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Update the time of the match with id")
    public String updateMatchTime(@ApiParam(value = "The match Id", required = true)@PathVariable Long id,
                                  @ApiParam(value = "Date time in ISO format YYYY-MM-DDThh:mm:ss", required = true)
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
