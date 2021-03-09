package com.oddschecker.odds.domain;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="bet")
public class Bet {

    @Id
    private UUID id;

    @JsonProperty(value = "betId")
    private Integer betId;

    private String name;

    private String category;

    private String status;

    private LocalDate eventDate;

    // Second of the Day
    private Integer eventTime;

}
