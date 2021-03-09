package com.oddschecker.odds.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oddschecker.odds.models.OddsInfo;
import com.oddschecker.odds.service.BettingService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OddsController.class)
@ExtendWith(SpringExtension.class)
class OddsControllerTest {

    @MockBean
    BettingService service;

    @Autowired
    private MockMvc mockMvc;

    OddsInfo oddsInfo;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        oddsInfo = OddsInfo.builder().odds("1/10").userId("1").betId(1).build();
        when(service.createOdd(any())).thenReturn(oddsInfo);
    }

    @Test
    void testCreateOdds() throws Exception {
        String body = objectMapper.writeValueAsString(oddsInfo);
        MvcResult result = mockMvc.perform(post("/odds/")
                                                   .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                   .content(body))
                                  .andExpect(status().isCreated()).andReturn();
        Assertions.assertEquals(body, result.getResponse().getContentAsString());
    }

    @Test
    void testGetOddsByBetId() throws Exception {
        List<OddsInfo> odds = new ArrayList<>();
        odds.add(OddsInfo.builder().betId(1).userId("1").initialBet("1").profitWon("2").build());
        odds.add(OddsInfo.builder().betId(1).userId("1").initialBet("1").profitWon("3").build());
        odds.add(OddsInfo.builder().betId(1).userId("1").initialBet("1").profitWon("4").build());
        odds.add(OddsInfo.builder().betId(1).userId("1").initialBet("1").profitWon("5").build());
        odds.add(OddsInfo.builder().betId(1).userId("1").initialBet("SP").build());

        when(service.getOdds(anyInt())).thenReturn(odds);
        MvcResult result = mockMvc.perform(get("/odds/1")).andExpect(status().isOk()).andReturn();
        Assertions.assertEquals(objectMapper.writeValueAsString(odds), result.getResponse().getContentAsString());
    }

    @Test
    void testGetOddsByBetId_NotFound() throws Exception {
        mockMvc.perform(get("/odds/1")).andExpect(status().isNotFound());
    }

}