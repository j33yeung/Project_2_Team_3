package com.project2.LocationSearchAPI.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "LocationQuery")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LocationQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int queryID;

    @Column(name = "locationName")
    private String locationName;

    @Column(name = "status")
    private String status;

    @Column(name = "population")
    private int population;

    @Column(name = "vaccinated")
    private int vaccinated;

    @Column(name = "totalInfections")
    private int totalInfections;

    @Column(name = "totalDeaths")
    private int totalDeaths;

    @Column(name = "totalRecovered")
    private int totalRecovered;
}