package com.Project_2_Location_Search_API.entities;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "AUTO_INCREMENT")
    private int queryID;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "status")
    private String status;

    @Column(name = "population")
    private int population;

    @Column(name = "vaccinated")
    private int vaccinated;

    @Column(name = "total_infections")
    private int totalInfections;

    @Column(name = "total_deaths")
    private int totalDeaths;

    @Column(name = "total_recovered")
    private int totalRecovered;
}