package com.precize.CodingAssignment.Repository;

import com.precize.CodingAssignment.Model.Candidate;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRespository extends JpaRepository<Candidate,Integer> {

    @Query( value = "SELECT * FROM sat_results where name = :name", nativeQuery = true)
    public Optional<Candidate> findByName(String name);

}
