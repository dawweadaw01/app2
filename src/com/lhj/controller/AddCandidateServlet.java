package com.lhj.controller;

import com.lhj.model.Candidate;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;

@WebServlet("/admin/add")
public class AddCandidateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Candidate candidate = null;
        //配置保存位置
        String path = "/photo";
        //获取上传文件的保存目录
        String savedDir = this.getServletContext().getRealPath(path);
        //创建一个通用的多部分解析器
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            //解析请求，得到文件项集合
            List<FileItem> fileItems = upload.parseRequest(req);
            if (!fileItems.isEmpty()) {
                candidate = new Candidate();
            }
            //创建迭代器,处理表单数据
            Iterator<FileItem> iterator = fileItems.iterator();
            while (iterator.hasNext()) {
                FileItem item = iterator.next();
                //判断表单域，还是上传的文件
                if (item.isFormField()) {
                    if (item.getFieldName().equals("name")) {
                        candidate.setName(new String(item.getString().getBytes("iso-8859-1"), "utf-8"));
                    }
                } else {
                    //获取文件名
                    String fileName = item.getName();
                    //保存文件
                    File file = new File(savedDir + "//" + fileName);
                    System.out.println(file);
                    item.write(file);
                    candidate.setPhotoUrl(req.getContextPath() + path + "/" + fileName);
                }

            }
        } catch (Exception e) {
            System.out.println("文件上传失败" + e.getMessage());
        }

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        //向数据库添加候选人
        if (candidate != null) {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=GMT";
            String db_user = "root";
            String db_pwd = "123456";
            try {
                Class.forName(driver);
                Connection conn = DriverManager.getConnection(url, db_user, db_pwd);
                String sql = "insert into table_candidate(name,photoUrl) values(?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, candidate.getName());
                pstmt.setString(2, candidate.getPhotoUrl());
                int row = pstmt.executeUpdate();

                if (row == 1) {
                    System.out.println("添加成功" + candidate);
                    out.println("<script>alert('候选人" + candidate.getName() + "添加成功');window.location.href='manage'</script>");
                    resp.sendRedirect("manage");
                } else {
                    System.out.println("添加失败" + candidate + "," + sql);
                    out.println("<script>alert('候选人" + candidate.getName() + "添加失败');window.location.href='addPre'</script>");
                }

            } catch (Exception e) {
                System.out.println("添加失败" + candidate + "," + e.getMessage());
                out.println("<script>alert('候选人" + candidate.getName() + "添加失败');window.location.href='addPre'</script>");
            }
        } else {
            System.out.println("添加失败" + candidate);
            out.println("<script>alert('候选人添加失败:" + candidate.getName() + "');window.location.href='addPre'</script>");
        }
    }
}
