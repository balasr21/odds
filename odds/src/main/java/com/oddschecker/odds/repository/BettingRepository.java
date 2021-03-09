package com.oddschecker.odds.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oddschecker.odds.domain.Bet;

public interface BettingRepository extends JpaRepository<Bet, UUID> {

    @Query(value = "select * from bet where bet_id=?1", nativeQuery = true)
    public Bet findByBetId(Integer betId);

}
