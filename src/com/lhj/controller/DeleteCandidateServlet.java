package com.lhj.controller;

import com.lhj.model.Candidate;
import com.lhj.service.UserService;
import com.lhj.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin/del")
public class DeleteCandidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("id");
        if (sid == null || "".equals(sid)) {
            response.sendRedirect("manage");
            System.out.println("id为空");
            return;
        }
        //2.获取要删除的用户数据
        UserService userService = new UserServiceImpl();
        userService.delete(Integer.parseInt(sid));
        response.sendRedirect("manage");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
