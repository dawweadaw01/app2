package com.lhj.service;

import com.lhj.model.Candidate;

import java.util.List;

public interface UserService {
    List<Candidate> findAll();
    int delete(int id);
    Candidate findById(int id);
    int update(Candidate candidate);

}
