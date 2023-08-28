package com.app.match.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "match_odds",
        uniqueConstraints = { @UniqueConstraint(columnNames = {"specifier", "match_id"})})
public class MatchOdds {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double odd;

    private String specifier;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getOdd() {
        return odd;
    }

    public void setOdd(Double odd) {
        this.odd = odd;
    }

    public String getSpecifier() {
        return specifier;
    }

    public void setSpecifier(String specifier) {
        this.specifier = specifier;
    }

    @JsonIgnore
    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Long getMatchId(){
        return match.getId();
    }

    public String getMatchTeams() {
        return String.format("%s vs %s", match.getTeamA(), match.getTeamB());
    }

    public String getSport() {
        return match.getSport().name();
    }

    @Override
    public String toString() {
        return "MatchOdds{" +
                "id=" + id +
                ", odd=" + odd +
                ", specifier='" + specifier + '\'' +
                ", match=" + match +
                '}';
    }
}
