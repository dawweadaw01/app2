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

@WebServlet("/admin/manage")
public class ManageCandidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userservice = new UserServiceImpl();
        List<Candidate> userList = userservice.findAll();
        //返回包含用户数据的html页面
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>用户列表</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<a href='add.html'>添加用户</a>");
        //
        if (null == userList || userList.isEmpty()) {
            out.println("<h1>暂时无用户</h1>");
        } else {
            out.println("<table>");
            out.println("<caption>用户列表</caption>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>name</th>");
            out.println("<th>头像</th>");
            out.println("<th>投票数</th>");
            out.println("<th>操作</th>");
            out.println("</tr>");
            for (Candidate candidate : userList) {
                out.println("<tr>");
                out.println("<td>" + candidate.getId() + "</td>");
                out.println("<td>" + candidate.getName() + "</td>");
                out.println("<td><img alt='' width='100' height='150' src='" + candidate.getPhotoUrl() + "'/></td>");
                out.println("<td>" + candidate.getVotes() + "</td>");
                out.println("<td><a href='mod?id=" + candidate.getId() + "'>修改</a> " +
                        "<a href='del?id=" + candidate.getId() + "'>删除</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
        out.println("</body>");
        out.println("</html>");


    }
}
