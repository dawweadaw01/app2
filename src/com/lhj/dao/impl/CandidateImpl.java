package com.lhj.dao.impl;

import com.lhj.dao.CandidateDao;
import com.lhj.model.Candidate;

import java.util.ArrayList;
import java.util.List;

public class CandidateImpl extends BaseDao implements CandidateDao {
    @Override
    public List<Candidate> findAll() {
        List<Candidate> userList = new ArrayList<>();
        String sql = "SELECT * FROM table_candidate";
        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Candidate candidate = new Candidate();
                candidate.setId(rs.getInt("id"));
                candidate.setName(rs.getString("name"));
                candidate.setPhotoUrl(rs.getString("photoUrl"));
                candidate.setVotes(rs.getInt("votes"));
                userList.add(candidate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public int delete(int id) {
        int result=0;
        String sql="DELETE FROM mydb.table_candidate  WHERE id=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            result=pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Candidate findById(int id) {
        Candidate candidate=null;
        String sql = "SELECT * FROM table_candidate WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                candidate = new Candidate();
                candidate.setId(rs.getInt("id"));
                candidate.setName(rs.getString("name"));
                candidate.setPhotoUrl(rs.getString("photoUrl"));
                candidate.setVotes(rs.getInt("votes"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidate;
    }

    @Override
    public int update(Candidate candidate) {
        int result=0;
        String sql="UPDATE mydb.table_candidate SET votes=? WHERE id=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,candidate.getVotes());
            pstmt.setInt(2,candidate.getId());
            result=pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

