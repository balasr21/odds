package com.oddschecker.odds.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OddsInfo implements Serializable {

    public OddsInfo(Integer betId, String userId, String initialBet, String profitWon) {
        this.betId = betId;
        this.userId = userId;
        this.initialBet = initialBet;
        this.profitWon = profitWon;
    }

    @ApiModelProperty(notes="Bet Id",required = true,value="1")
    @JsonProperty("betId")
    private Integer betId;

    @ApiModelProperty(notes="User Id",required = true,value="1")
    @JsonProperty("userId")
    private String userId;

    @ApiModelProperty(notes="ID of user who is offering the odds",required = true,value="Example 1/10")
    @JsonProperty("odds")
    private String odds;

    @JsonIgnore
    private String initialBet;

    @JsonIgnore
    private String profitWon;

}
