package com.lhj.dao;

import com.lhj.model.Candidate;

import java.util.List;

public interface CandidateDao {
    List<Candidate> findAll();
    int delete(int id);
    Candidate findById(int id);
    int update(Candidate candidate);

}
