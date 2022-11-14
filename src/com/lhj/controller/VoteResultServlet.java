package com.lhj.controller;

import com.lhj.model.Candidate;
import com.lhj.service.UserService;
import com.lhj.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/result")
public class VoteResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取要投票的用户id
        String sid = request.getParameter("id");
        if (sid == null || "".equals(sid)) {
            response.sendRedirect("manage");
            System.out.println("id为空");
            return;
        }
        //2.获取要投票的用户数据
        UserService userService = new UserServiceImpl();
        Candidate candidate = userService.findById(Integer.parseInt(sid));
        candidate.setVotes(candidate.getVotes() + 1);
        int row = userService.update(candidate);
        System.out.println(row);
        response.sendRedirect("list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
