package com.app.match.repositories;

import com.app.match.model.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MatchOddsRepository extends JpaRepository<MatchOdds, Long> {

    long deleteByMatch_id(Long matchId);

    Optional<MatchOdds> findByMatch_idAndSpecifier(Long matchId, String specifier);

    List<MatchOdds> findByMatch_id(Long matchId);
}
