package com.Project_2_Location_Search_API.dto;

import lombok.*;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MapWithStatus {
    private byte[] img;
    private String status;
}
