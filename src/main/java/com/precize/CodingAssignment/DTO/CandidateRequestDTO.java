package com.precize.CodingAssignment.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CandidateRequestDTO {
    String name;

    String address;

    String city;

    String country;

    String pincode;

    Double score;
}
