package com.Project_2_Location_Status_API.Entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name= "Statuses")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "AUTO_INCREMENT")
    private int id;

    @Column(name = "score")
    private Integer score;

    @Column(name = "location")
    private String location;

    @Column(name = "creationDate")
    private String creationDate;
}
