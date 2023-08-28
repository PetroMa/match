package com.app.match.services;

import com.app.match.DataNotFoundException;
import com.app.match.model.Match;
import com.app.match.repositories.MatchOddsRepository;
import com.app.match.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MatchService {

    @Autowired
    private MatchRepository matchRepo;

    @Autowired
    private MatchOddsRepository oddsRepo;

    public List<Match> getAllMatches() {
        return matchRepo.findAll();
    }

    public Optional<Match> getMatchOddsById(Long matchId) {
        Optional<Match> optionalMatch = matchRepo.findById(matchId);
        if (!optionalMatch.isPresent()) {
            throw new DataNotFoundException(
                    String.format("Match with id %s does not exist", matchId));
        }
        return optionalMatch;
    }

    public void addMatch(Match match) {
        matchRepo.save(match);
        if (match.getMatchOdds()!= null) {
            oddsRepo.saveAll(match.getMatchOdds());
        }
    }

    public void deleteMatch(Long matchId) {
        oddsRepo.deleteByMatch_id(matchId);
        matchRepo.deleteById(matchId);
    }

    public void updateMatchDateAndTime(Long matchId, LocalDateTime dateTime) {
        Optional<Match> optionalMatch = matchRepo.findById(matchId);
        if (!optionalMatch.isPresent()) {
            throw new DataNotFoundException(
                    String.format("Match with id %s does not exist", matchId));
        }
        optionalMatch.get().setMatchDate(dateTime.toLocalDate());
        optionalMatch.get().setMatchHour(dateTime.toLocalTime());
        matchRepo.save(optionalMatch.get());
    }

}
