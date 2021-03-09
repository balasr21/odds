package com.oddschecker.odds.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.oddschecker.odds.domain.Bet;
import com.oddschecker.odds.domain.Odds;
import com.oddschecker.odds.domain.Users;
import com.oddschecker.odds.exception.InvalidBetException;
import com.oddschecker.odds.exception.InvalidOddsException;
import com.oddschecker.odds.exception.InvalidUserException;
import com.oddschecker.odds.models.OddsInfo;
import com.oddschecker.odds.repository.BettingRepository;
import com.oddschecker.odds.repository.OddsRepository;
import com.oddschecker.odds.repository.UserRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BettingServiceImplTest {

    @Mock
    OddsRepository oddsRepository;

    @Mock
    BettingRepository bettingRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    BettingServiceImpl bettingService;

    @Test
    @DisplayName("Test creation of odd with valid UserId and valid BetId")
    void testCreateOdds() {

        when(bettingRepository.findByBetId(any())).thenReturn(Bet.builder().betId(1).build());
        when(userRepository.findByUserId(anyString())).thenReturn(Users.builder().userId("1").build());
        when(oddsRepository.save(any())).thenReturn(Odds.builder().build());

        OddsInfo odds = bettingService.createOdd(OddsInfo.builder().odds("1/10").userId("1").betId(1).build());
        Assertions.assertNotNull(odds);
        Assertions.assertEquals(1, odds.getBetId());
        Assertions.assertEquals("1", odds.getUserId());
        Assertions.assertEquals("1/10", odds.getOdds());
    }

    @Test
    @DisplayName("Test creation of odd with valid UserId and valid BetId")
    void testCreateOdds_valid_Odds_WithSP() {

        when(bettingRepository.findByBetId(any())).thenReturn(Bet.builder().betId(1).build());
        when(userRepository.findByUserId(anyString())).thenReturn(Users.builder().userId("1").build());
        when(oddsRepository.save(any())).thenReturn(Odds.builder().build());

        OddsInfo odds = bettingService.createOdd(OddsInfo.builder().odds("SP").userId("1").betId(1).build());
        Assertions.assertNotNull(odds);
        Assertions.assertEquals(1, odds.getBetId());
        Assertions.assertEquals("1", odds.getUserId());
        Assertions.assertEquals("SP", odds.getOdds());
    }

    @Test
    @DisplayName("Test creation of odd with invalid UserId")
    void testCreateOdd_With_Invalid_UserId() {

        when(bettingRepository.findByBetId(any())).thenReturn(Bet.builder().betId(1).build());
        when(userRepository.findByUserId(any())).thenReturn(null);
        Assertions.assertThrows(InvalidUserException.class, () -> bettingService.createOdd(OddsInfo.builder().odds("1/10").userId("Invalid").betId(1).build()));
    }

    @Test
    @DisplayName("Test creation of odd with invalid BetId")
    void testCreateOdd_With_Invalid_BetId() {

        when(userRepository.findByUserId(anyString())).thenReturn(Users.builder().userId("1").build());
        when(bettingRepository.findByBetId(anyInt())).thenReturn(null);
        Assertions.assertThrows(InvalidBetException.class, () -> bettingService.createOdd(OddsInfo.builder().odds("1/10").userId("1").betId(0).build()));
    }

    @Test
    @DisplayName("Test creation of odd with invalid Odds - 1")
    void testCreateOdd_With_Invalid_Odds_1() {

        when(bettingRepository.findByBetId(any())).thenReturn(Bet.builder().betId(1).build());
        when(userRepository.findByUserId(anyString())).thenReturn(Users.builder().userId("1").build());
        Assertions.assertThrows(InvalidOddsException.class, () -> bettingService.createOdd(OddsInfo.builder().odds("0/10").userId("1").betId(1).build()));
    }

    @Test
    @DisplayName("Test creation of odd with invalid Odds - 2")
    void testCreateOdd_With_Invalid_Odds_2() {

        when(bettingRepository.findByBetId(any())).thenReturn(Bet.builder().betId(1).build());
        when(userRepository.findByUserId(anyString())).thenReturn(Users.builder().userId("1").build());
        Assertions.assertThrows(InvalidOddsException.class, () -> bettingService.createOdd(OddsInfo.builder().odds("SP/10").userId("1").betId(1).build()));
    }

    @Test
    @DisplayName("Test creation of odd with invalid Odds - 3")
    void testCreateOdd_With_Invalid_Odds_3() {

        when(bettingRepository.findByBetId(any())).thenReturn(Bet.builder().betId(1).build());
        when(userRepository.findByUserId(anyString())).thenReturn(Users.builder().userId("1").build());
        Assertions.assertThrows(InvalidOddsException.class, () -> bettingService.createOdd(OddsInfo.builder().odds("1/2/3").userId("1").betId(1).build()));
    }

    @Test
    @DisplayName("Test creation of odd with invalid Odds - 4")
    void testCreateOdd_With_Invalid_Odds_4() {

        when(bettingRepository.findByBetId(any())).thenReturn(Bet.builder().betId(1).build());
        when(userRepository.findByUserId(anyString())).thenReturn(Users.builder().userId("1").build());
        Assertions.assertThrows(InvalidOddsException.class, () -> bettingService.createOdd(OddsInfo.builder().odds("23*1").userId("1").betId(1).build()));
    }

    @Test
    @DisplayName("Test get Odds for given valid betId")
    void testGetOddsForValidBetId() {

        List<OddsInfo> odds = new ArrayList<>();
        odds.add(OddsInfo.builder().betId(1).userId("1").initialBet("1").profitWon("2").build());
        odds.add(OddsInfo.builder().betId(1).userId("1").initialBet("1").profitWon("3").build());
        odds.add(OddsInfo.builder().betId(1).userId("1").initialBet("1").profitWon("4").build());
        odds.add(OddsInfo.builder().betId(1).userId("1").initialBet("1").profitWon("5").build());
        odds.add(OddsInfo.builder().betId(1).userId("1").initialBet("SP").build());

        when(oddsRepository.getOddsById(anyInt())).thenReturn(odds);
        List<OddsInfo> result = bettingService.getOdds(1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(5, result.size());
        Assertions.assertEquals("2/1", result.get(0).getOdds());
        Assertions.assertEquals("3/1", result.get(1).getOdds());
        Assertions.assertEquals("4/1", result.get(2).getOdds());
        Assertions.assertEquals("5/1", result.get(3).getOdds());
        Assertions.assertEquals("SP", result.get(4).getOdds());
    }

}