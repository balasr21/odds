package com.oddschecker.odds.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oddschecker.odds.constant.GlobalConstants;
import com.oddschecker.odds.exception.InvalidBetException;
import com.oddschecker.odds.exception.InvalidOddsException;
import com.oddschecker.odds.domain.Bet;
import com.oddschecker.odds.domain.Odds;
import com.oddschecker.odds.exception.InvalidUserException;
import com.oddschecker.odds.models.OddsInfo;
import com.oddschecker.odds.models.Status;
import com.oddschecker.odds.domain.Users;
import com.oddschecker.odds.repository.BettingRepository;
import com.oddschecker.odds.repository.OddsRepository;
import com.oddschecker.odds.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BettingServiceImpl implements BettingService {

    @Autowired
    OddsRepository oddsRepository;

    @Autowired
    BettingRepository bettingRepository;

    @Autowired
    UserRepository userRepository;

    @Override public OddsInfo createOdd(OddsInfo odds) {

        log.info("Creating Odd for UserID [{}] with BetID [{}]", odds.getUserId(), odds.getBetId());

        if (!validOdds(odds.getOdds())) {
            log.error("Invalid Odds received [{}]", odds.getOdds());
            throw new InvalidOddsException("Invalid Odds received " + odds.getOdds());
        }

        Odds oddsDetails =
                Odds.builder()
                    .id(UUID.randomUUID())
                    .bet(getBetById(odds.getBetId()))
                    .initialBet(getInitialBet(odds.getOdds()))
                    .profitWon(getProfitWon(odds.getOdds()))
                    .status(Status.ACTIVE.name())
                    .user(getUserById(odds.getUserId()))
                    .createdTime(LocalDateTime.now()).build();

        oddsRepository.save(oddsDetails);
        return odds;

    }

    private String getProfitWon(String odds) {
        if (odds.contains(GlobalConstants.FRACTION)) {
            return odds.split(GlobalConstants.FRACTION)[0];
        } else {
            return odds;
        }
    }

    private String getInitialBet(String odds) {
        if (odds.contains(GlobalConstants.FRACTION)) {
            return odds.split(GlobalConstants.FRACTION)[1];
        } else if (odds.equals(GlobalConstants.SP)) {
            return odds;
        } else {
            // If Odds is placed as 1,2,3 etc
            return "1";
        }
    }

    private boolean validOdd(String odds) {

        if (odds.equals(GlobalConstants.SP)) {
            return true;
        } else if (odds.contains(GlobalConstants.FRACTION)) {
            String[] data = odds.split(GlobalConstants.FRACTION);
            try {
                if (data.length == 2 && Integer.parseInt(data[0]) > 0) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                log.error("Invalid Odds received [{}]", odds);
                return false;
            }
        } else {
            try {
                Integer.parseInt(odds);
                return true;
            } catch (Exception e) {
                log.error("Invalid Odds received [{}]", odds);
                return false;
            }
        }

    }

    private boolean validOdds(String odds) {

        if (odds.equals(GlobalConstants.SP) ||
            Pattern.compile("^[1-9]\\d*(/\\d+)?").matcher(odds).matches()) {
            return true;
        }

        return false;

    }

    private Users getUserById(String userId) {
        Users user = userRepository.findByUserId(userId);

        if (user == null) {
            log.error("Invalid User ID received for creating odds [{}]", userId);
            throw new InvalidUserException("Invalid User ID received for creating odds " + userId);
        }
        return user;
    }

    private Bet getBetById(Integer betId) {
        Bet bet = bettingRepository.findByBetId(betId);

        if (bet == null) {
            log.error("Invalid Bet ID received for creating odds [{}]", betId);
            throw new InvalidBetException("Invalid Bet ID received for creating odds " + betId);
        }

        return bet;
    }

    @Override
    public List<OddsInfo> getOdds(Integer betId) {
        List<OddsInfo> oddsInfo = oddsRepository.getOddsById(betId);
        oddsInfo.stream().forEach(o -> o.setOdds(o.getInitialBet().equals("SP") ? "SP" : o.getProfitWon() + "/" + o.getInitialBet()));
        return oddsInfo;
    }
}
