package com.oddschecker.odds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oddschecker.odds.models.OddsInfo;
import com.oddschecker.odds.service.BettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class OddsController
 */

@RestController
@CrossOrigin
@RequestMapping("/odds")
@Api(value = "Oddschecker Odds API")
public class OddsController {

    @Autowired
    BettingService bettingService;

    @PostMapping("/")
    @ApiOperation(value="Offer Odds for a bet",notes="This API Places Odds for an given betId")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success|Odds have been created for Bet"),
            @ApiResponse(code = 400, message = "Invalid format of Odds or Invalid User ID or Invalid Bet ID")})
    public ResponseEntity<OddsInfo> createOdd(@RequestBody final OddsInfo odds) {
        return new ResponseEntity<>(bettingService.createOdd(odds), HttpStatus.CREATED);
    }

    @GetMapping("/{betId}")
    @ApiOperation(value="Find Odds by Bet ID",notes="This API retrieves all Odds for a given Bet ID for all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|Odds for the given Bet ID for all Users"),
            @ApiResponse(code = 404, message = "No Bets found for given Bet ID")})
    public ResponseEntity<List<OddsInfo>> getOdds(@PathVariable Integer betId) {
        List<OddsInfo> oddsInfo = bettingService.getOdds(betId);
        if (!oddsInfo.isEmpty()) {
            return new ResponseEntity<>(oddsInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
