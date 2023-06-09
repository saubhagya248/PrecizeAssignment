package com.precize.CodingAssignment.Service.Impl;

import Util.Utilities;
import com.precize.CodingAssignment.DTO.CandidateRequestDTO;
import com.precize.CodingAssignment.DTO.CandidateResponseDTO;
import com.precize.CodingAssignment.Enum.Result;
import com.precize.CodingAssignment.Exceptions.CandidateNotFoundException;
import com.precize.CodingAssignment.Exceptions.InvalidNameException;
import com.precize.CodingAssignment.Exceptions.InvalidScoreException;
import com.precize.CodingAssignment.Model.Candidate;
import com.precize.CodingAssignment.Repository.CandidateRespository;
import com.precize.CodingAssignment.Service.CandidateService;
import com.precize.CodingAssignment.Transformer.CandidateTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {


    @Autowired
    CandidateRespository candidateRespository;

    @Override
    public CandidateResponseDTO insertRecord(CandidateRequestDTO requestDTO) throws InvalidScoreException,InvalidNameException{
        //search for the name if already available in database
        Optional<Candidate> candidateOptional = candidateRespository.findByName(requestDTO.getName());

        //throw exception if name is already there
        if(candidateOptional.isPresent())
            throw new InvalidNameException("Invalid name! name is already registered in database");
        //throw exception if score is not valid
        if(requestDTO.getScore()>100 || requestDTO.getScore()<0)
            throw new InvalidScoreException("Invalid score! Score should be <= 100 and >= 0");

        //form the candidate object to save in the repo
        Result result = Utilities.calculateResult(requestDTO.getScore());
        Candidate candidate = candidateRespository.save(
                CandidateTransformer.requestObjectToCandidate(requestDTO,result));

        //return response DTO
        return CandidateTransformer.candidateToResponseObject(candidate);
    }

    @Override
    public List<CandidateResponseDTO> viewAll() {
        List<Candidate> candidateList = candidateRespository.findAll();

        List<CandidateResponseDTO> responseDTOList = new ArrayList<>();

        for(Candidate candidate: candidateList){
            responseDTOList.add(CandidateTransformer.candidateToResponseObject(candidate));
        }

        return responseDTOList;
    }

    @Override
    public String getRank(String name) throws CandidateNotFoundException{
        Optional<Candidate> candidateOptional = candidateRespository.findByName(name);

        if(candidateOptional.isEmpty())
            throw new CandidateNotFoundException("Candidate with given name not found!!");

        return Utilities.getRank(candidateOptional.get().getScore());
    }

    @Override
    public CandidateResponseDTO updateScore(String name, Double updatedScore) throws CandidateNotFoundException,InvalidScoreException{
        Optional<Candidate> candidateOptional = candidateRespository.findByName(name);

        if(candidateOptional.isEmpty())
            throw new CandidateNotFoundException("Candidate with given name not found!!");

        if(updatedScore < 0 || updatedScore > 100)
            throw new InvalidScoreException("Invalid score! Score should be <= 100 and >= 0");


        Candidate candidate = candidateOptional.get();

        candidate.setScore(updatedScore);

        Candidate response = candidateRespository.save(candidate);

        return CandidateTransformer.candidateToResponseObject(response);
    }

    @Override
    public String deleteRecord(String name) {
        Optional<Candidate> candidateOptional = candidateRespository.findByName(name);

        if(candidateOptional.isEmpty())
            throw new CandidateNotFoundException("Candidate with given name not found!!");

        candidateRespository.delete(candidateOptional.get());

        return "Record deleted succesfully";
    }


}
