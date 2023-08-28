package com.app.match.controllers;

import com.app.match.model.MatchOdds;
import com.app.match.services.OddsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/odds")
public class OddsController {

    @Autowired
    OddsService oddsService;

    @GetMapping
    public List<MatchOdds> getAllOdds() {
        return oddsService.getAllOdds();
    }

    @GetMapping("/{id}")
    public Optional<MatchOdds> getMatchOddsById(@PathVariable Long id) {
       return oddsService.getMatchOddsById(id);
    }

    @PostMapping("/add")
    public String saveOdds(@RequestParam Long matchId, @RequestParam List<Double> odds) {
        try {
            oddsService.addOdds(matchId, odds);
            return "Odds saved successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOddById(@PathVariable Long id) {
        try {
            oddsService.deleteOddById(id);
            return String.format("Odd with id %s deleted", id);
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Fail to delete odd with id %s",id);
        }
    }

    @PutMapping("/update/{id}")
    public String updateOddById(@PathVariable Long id, @RequestParam Double odd) {
        try {
            oddsService.updateOddsById(id, odd);
            return String.format("Odd with id %s updated", id);
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Fail to update odd with id %s",id);
        }
    }

}
