package com.kangbiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kangbiao.dao.DocumentDao;

@WebServlet("/get_company_list.do")
public class GetCompanyList extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        DocumentDao documentDao = new DocumentDao();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(documentDao.getAllCompany());
    }

}
