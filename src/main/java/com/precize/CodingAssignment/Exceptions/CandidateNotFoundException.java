package com.precize.CodingAssignment.Exceptions;

public class CandidateNotFoundException extends RuntimeException{
    public CandidateNotFoundException(String message){
        super(message);
    }
}
