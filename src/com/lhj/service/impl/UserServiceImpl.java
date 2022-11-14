package com.lhj.service.impl;

import com.lhj.dao.CandidateDao;
import com.lhj.dao.impl.CandidateImpl;
import com.lhj.model.Candidate;
import com.lhj.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    CandidateDao candidateDao = new CandidateImpl();
    @Override
    public List<Candidate> findAll() {
        return candidateDao.findAll();
    }

    @Override
    public int delete(int id) {
        return candidateDao.delete(id);
    }

    @Override
    public Candidate findById(int id) {
        return candidateDao.findById(id);
    }

    @Override
    public int update(Candidate candidate) {
        return candidateDao.update(candidate);
    }
}

