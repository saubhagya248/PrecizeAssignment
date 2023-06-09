package com.precize.CodingAssignment.Controller;

import com.precize.CodingAssignment.DTO.CandidateRequestDTO;
import com.precize.CodingAssignment.DTO.CandidateResponseDTO;
import com.precize.CodingAssignment.Exceptions.CandidateNotFoundException;
import com.precize.CodingAssignment.Exceptions.InvalidNameException;
import com.precize.CodingAssignment.Exceptions.InvalidScoreException;
import com.precize.CodingAssignment.Model.Candidate;
import com.precize.CodingAssignment.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sat-result")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @PostMapping("/insert")
    public ResponseEntity insertRecord(@RequestBody CandidateRequestDTO requestDTO){
        try{

            CandidateResponseDTO response = candidateService.insertRecord(requestDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (InvalidNameException | InvalidScoreException exception) {

            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/view-all")
    public ResponseEntity viewAll(){
        List<CandidateResponseDTO> responseList = candidateService.viewAll();
        return new ResponseEntity<>(responseList,HttpStatus.OK);
    }

    @GetMapping("/get-rank")
    public ResponseEntity getRank(@RequestParam String name){
        try{
            String rank = candidateService.getRank(name);
            String response = "Hi, "+name+" your rank is "+rank+" !";
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (CandidateNotFoundException exception) {
            return new ResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-score")
    public ResponseEntity updateScore(@RequestParam String name, @RequestParam Double updatedScore){
        try{
            CandidateResponseDTO responseDTO = candidateService.updateScore(name,updatedScore);
            String response = "Hi! "+responseDTO.getName()+" , your score has been updated. Updated Score: "+responseDTO.getScore();
            return new ResponseEntity(response,HttpStatus.OK);
        } catch (CandidateNotFoundException | InvalidScoreException exception) {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteRecord(@RequestParam String name){
        try{
            String response = candidateService.deleteRecord(name);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CandidateNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
