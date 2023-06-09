package com.precize.CodingAssignment.Service;

import com.precize.CodingAssignment.DTO.CandidateRequestDTO;
import com.precize.CodingAssignment.DTO.CandidateResponseDTO;
import com.precize.CodingAssignment.Model.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CandidateService {

    public CandidateResponseDTO insertRecord(CandidateRequestDTO requestDTO);

    public List<CandidateResponseDTO> viewAll();

    public String getRank(String name);

    public CandidateResponseDTO updateScore(String name, Double updatedScore);

    public String deleteRecord(String name);
}
