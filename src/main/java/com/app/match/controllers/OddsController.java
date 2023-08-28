package com.app.match.controllers;

import com.app.match.model.MatchOdds;
import com.app.match.services.OddsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Get All Odds")
    public List<MatchOdds> getAllOdds() {
        return oddsService.getAllOdds();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Odd By id")
    public Optional<MatchOdds> getMatchOddsById(@ApiParam(value = "The odd Id", required = true) @PathVariable Long id) {
       return oddsService.getMatchOddsById(id);
    }

    @PostMapping("/add")
    @ApiOperation(value = "Add Odds for Match with id")
    public String saveOdds(@ApiParam(value = "The Match Id", required = true) @RequestParam Long matchId,
                           @ApiParam(value = "Single or multiple odds to add in match", required = true) @RequestParam List<Double> odds) {
        try {
            oddsService.addOdds(matchId, odds);
            return "Odds saved successfully";
        } catch (Exception e) {
            return String.format("Cannot saved odds because of %s", e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete Odd by odd id")
    public String deleteOddById(@ApiParam(value = "The odd Id", required = true) @PathVariable Long id) {
        try {
            oddsService.deleteOddById(id);
            return String.format("Odd with id %s deleted", id);
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Fail to delete odd with id %s",id);
        }
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "Update the odd value for odd with id")
    public String updateOddById(
            @ApiParam(value = "The odd Id", required = true) @PathVariable Long id,
            @ApiParam(value = "The new odd value", required = true) @RequestParam Double odd) {
        try {
            oddsService.updateOddsById(id, odd);
            return String.format("Odd with id %s updated", id);
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Fail to update odd with id %s",id);
        }
    }

}
