package com.example.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MatchOddsRepository extends JpaRepository<MatchOdds, Long> {

}
