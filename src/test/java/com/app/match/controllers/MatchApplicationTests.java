package com.app.match.controllers;

import com.app.match.controllers.MatchController;
import com.app.match.model.Match;
import com.app.match.model.Sport;
import com.app.match.repositories.MatchOddsRepository;
import com.app.match.repositories.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(MatchController.class)
class MatchApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchRepository matchRepo;

    @MockBean
    private MatchOddsRepository oddsRepo;

    private static String URL = "/api/match";

    @Test
    public void testGetMatchById() throws Exception {

        // prepare data and mock's behaviour
        Match match = new Match();
        match.setId(new Long(1));
        match.setMatchDate(LocalDate.of(2002, Month.APRIL, 22));
        match.setMatchHour(LocalTime.of(22, 3, 2));
        match.setSport(Sport.BASKETBALL);
        match.setTeamA("PAO");
        match.setTeamB("PAOK");

        when(matchRepo.findById(new Long(1))).thenReturn(java.util.Optional.of(match));

        // execute
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL + "/"+new Long(1)))
                .andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        // verify that service method was called once
        verify(matchRepo).findById(any(Long.class));


    }

}