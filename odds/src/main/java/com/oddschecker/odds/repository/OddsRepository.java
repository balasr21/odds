package com.oddschecker.odds.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oddschecker.odds.domain.Odds;
import com.oddschecker.odds.models.OddsInfo;

@Repository
public interface OddsRepository extends JpaRepository<Odds, UUID> {

    @Query("select NEW com.oddschecker.odds.models.OddsInfo(bet.betId,user.userId,odds.initialBet,odds.profitWon) from Odds odds join odds.bet bet join odds.user user where bet.betId=:betId and odds.status='ACTIVE'")
    public List<OddsInfo> getOddsById(Integer betId);

}
