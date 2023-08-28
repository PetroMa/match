package com.app.match.repositories;

import com.app.match.model.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchOddsRepository extends JpaRepository<MatchOdds, Long> {

    long deleteByMatch_id(Long matchId);
}
