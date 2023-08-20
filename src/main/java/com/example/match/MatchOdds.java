package com.example.match;

import javax.persistence.*;

@Entity
@Table(name = "match_odds")
public class MatchOdds {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double odd;

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
}
