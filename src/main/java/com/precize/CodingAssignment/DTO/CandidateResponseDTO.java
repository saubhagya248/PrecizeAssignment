package com.precize.CodingAssignment.DTO;

import com.precize.CodingAssignment.Enum.Result;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CandidateResponseDTO {

    String name;

    String address;

    String city;

    String country;

    String pincode;

    Double score;

    Result result;

}
