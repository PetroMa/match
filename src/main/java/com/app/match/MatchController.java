package com.app.match;

import com.app.match.model.Match;
import com.app.match.repositories.MatchOddsRepository;
import com.app.match.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchRepository matchRepo;

    @Autowired
    private MatchOddsRepository oddsRepo;

    @GetMapping
    public List<Match> getAllMatches() {
        return matchRepo.findAll();
    }

    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable Long id) {
        Optional<Match> optionalMatch = matchRepo.findById(id);
        return optionalMatch.isPresent()?optionalMatch.get():null;
    }

    @PostMapping("/add")
    public String addMatch(@RequestBody Match match) {
        try {
            matchRepo.save(match);
            if (match.getMatchOdds()!= null) {
              oddsRepo.saveAll(match.getMatchOdds());
            }
            return String.format("Match saved: %s", match.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Fail to save match: %s because of %s", match.toString(), e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public String deleteMatch(@PathVariable Long id) {
        try {
            matchRepo.deleteById(id);
            return String.format("Match with id %s deleted", id);
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Fail to delete match with id %s",id);
        }
    }

    @DeleteMapping("/delete/odds/{id}")
    public String deleteOdds(@PathVariable Long id) {
        try {
            oddsRepo.deleteById(id);
            return String.format("Odds entity with id %s deleted", id);
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Fail to delete odds entity with id %s",id);
        }
    }

}
