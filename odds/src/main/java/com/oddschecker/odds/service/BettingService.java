package com.oddschecker.odds.service;

import java.util.List;

import com.oddschecker.odds.models.OddsInfo;


public interface BettingService {

    public OddsInfo createOdd(OddsInfo odds);

    public List<OddsInfo> getOdds(Integer betId);

}
