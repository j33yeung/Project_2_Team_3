package com.Project_2_Location_Search_API.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MapRequestDTO {
    private String center;
    private String marker1;
    private String marker2;
    private String path;
}
