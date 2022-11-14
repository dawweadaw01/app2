package com.lhj.controller;

import com.lhj.model.Candidate;
import com.lhj.service.UserService;
import com.lhj.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
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
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>请投票</title>");
        out.println("</head>");
        out.println("<body>");
        //
        if (null == candidate) {
            out.println("<h1>暂时无用户</h1>");
        } else {
            out.println("<table>");
            out.println("<caption>你选择的候选人</caption>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>name</th>");
            out.println("<th>头像</th>");
            out.println("<th>操作</th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>" + candidate.getId() + "</td>");
            out.println("<td>" + candidate.getName() + "</td>");
            out.println("<td><img alt='' width='100' height='150' src='" + candidate.getPhotoUrl() + "'/></td>");
            out.println("<td><a href='result?id=" + candidate.getId() + "'>为我投票吧</a></td>");
            out.println("</tr>");
            out.println("</table>");
        }
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
