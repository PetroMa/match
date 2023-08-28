package com.app.match.services;

import com.app.match.DataNotFoundException;
import com.app.match.model.Match;
import com.app.match.model.MatchOdds;
import com.app.match.repositories.MatchOddsRepository;
import com.app.match.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OddsService {

    @Autowired
    private MatchRepository matchRepo;

    @Autowired
    private MatchOddsRepository oddsRepo;

    public List<MatchOdds> getAllOdds() {
        return oddsRepo.findAll();
    }

    public Optional<MatchOdds> getMatchOddsById(Long oddId) {
        if (!oddsRepo.existsById(oddId)) {
            throw new DataNotFoundException(
                    String.format("Odd with id %s does not exist", oddId));
        }
        return oddsRepo.findById(oddId);
    }

    public List<MatchOdds> getOddsByMatch(Long matchId) {
        return oddsRepo.findByMatch_id(matchId);
    }

    public void addOdd(Long matchId, String specifier, Double odd) {
        Optional<Match> optionalMatch = matchRepo.findById(matchId);
        if (!optionalMatch.isPresent()) {
            throw new DataNotFoundException(
                    String.format("Match with id %s does not exist", matchId)
            );
        }
        MatchOdds matchOdd = new MatchOdds();
        matchOdd.setMatch(optionalMatch.get());
        matchOdd.setOdd(odd);
        matchOdd.setSpecifier(specifier);
        oddsRepo.saveAndFlush(matchOdd);
    }

    public void deleteOddById(Long oddId) {
        if (!oddsRepo.existsById(oddId)) {
            throw new DataNotFoundException(
                    String.format("Odd with id %s does not exist", oddId));
        }
        oddsRepo.deleteById(oddId);
    }

    public void updateOddsById(Long id, Double odd) {
        Optional<MatchOdds> optionalMatchOdd = oddsRepo.findById(id);
        if (!optionalMatchOdd.isPresent()) {
            throw new DataNotFoundException(
                    String.format("Odd with id %s does not exist", id));
        }
        optionalMatchOdd.get().setOdd(odd);
        oddsRepo.save(optionalMatchOdd.get());
    }

    public void updateOddsByMatchIdAndSpecifier(Long matchId, String specifier, Double odd) {
        Optional<Match> optionalMatch = matchRepo.findById(matchId);
        if (!optionalMatch.isPresent()) {
            throw new DataNotFoundException(
                    String.format("Match with id %s does not exist", matchId));
        }

        Optional<MatchOdds> optionalMatchOdd = oddsRepo.findByMatch_idAndSpecifier(matchId,specifier);

        if (!optionalMatchOdd.isPresent()) {
            throw new DataNotFoundException(
                    String.format("Specifier %s for match with id %s does not exist", specifier, matchId));
        }
        optionalMatchOdd.get().setOdd(odd);
        oddsRepo.save(optionalMatchOdd.get());
    }


}


