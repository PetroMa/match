package com.example.match;

import org.h2.value.lob.LobDataFetchOnDemand;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @Column(name = "match_date")
    private LocalDate matchDate;

    @Column(name = "match_hour")
    private LocalTime matchHour;

    @Column(name = "team_a")
    private String teamA;

    @Column(name = "team_b")
    private String teamB;

    private Sport sport;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private List<MatchOdds> matchOdds = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public LocalTime getMatchHour() {
        return matchHour;
    }

    public void setMatchHour(LocalTime matchHour) {
        this.matchHour = matchHour;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public List<MatchOdds> getMatchOdds() {
        return matchOdds;
    }

    public void setMatchOdds(List<MatchOdds> matchOdds) {
        this.matchOdds = matchOdds;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", matchDate=" + matchDate +
                ", matchHour=" + matchHour +
                ", teamA='" + teamA + '\'' +
                ", teamB='" + teamB + '\'' +
                ", sport=" + sport +
                ", matchOdds=" + matchOdds +
                '}';
    }

}
