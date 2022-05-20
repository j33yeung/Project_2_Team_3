package com.Project_2_Location_Search_API.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MapRequestDTO {
    private String state;
    private String format;
    private String countrycodes;
}
