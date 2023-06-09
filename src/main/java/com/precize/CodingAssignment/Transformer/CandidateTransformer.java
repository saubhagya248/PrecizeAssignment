package com.precize.CodingAssignment.Transformer;

import com.precize.CodingAssignment.DTO.CandidateRequestDTO;
import com.precize.CodingAssignment.DTO.CandidateResponseDTO;
import com.precize.CodingAssignment.Enum.Result;
import com.precize.CodingAssignment.Model.Candidate;

public class CandidateTransformer {
    public static Candidate requestObjectToCandidate(CandidateRequestDTO requestObject, Result result){
        return Candidate.builder()
                .name(requestObject.getName())
                .address(requestObject.getAddress())
                .city(requestObject.getCity())
                .pincode(requestObject.getPincode())
                .country(requestObject.getCountry())
                .score(requestObject.getScore())
                .result(result)
                .build();
    }

    public static CandidateResponseDTO candidateToResponseObject(Candidate candidate){
        return CandidateResponseDTO.builder()
                .name(candidate.getName())
                .address(candidate.getAddress())
                .city(candidate.getCity())
                .country(candidate.getCountry())
                .pincode(candidate.getPincode())
                .score(candidate.getScore())
                .result(candidate.getResult())
                .build();
    }
}
