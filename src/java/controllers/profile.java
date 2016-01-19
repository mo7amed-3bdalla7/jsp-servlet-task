/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.userModel;

/**
 *
 * @author M.A
 */
public class profile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession(false);
            userModel user = (userModel) session.getAttribute("user");
           // System.out.println(user);
            if (user != null) {
                ResultSet deposite = user.getHistory("deposit",false);
                request.setAttribute("deposite", deposite);
                ResultSet withdraw = user.getHistory("withdraw",false);
                request.setAttribute("withdraw", withdraw);
                ResultSet transfer = user.getHistory("transfer",true);
                request.setAttribute("transfer", transfer);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }else
            {
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
        } catch (IOException ex) {
            Logger.getLogger(profile.class.getName()).log(Level.SEVERE, null, ex);
        }
//            databaseModel connection = databaseModel.getConnection();
//            PreparedStatement statement = connection.statement("SELECT * FROM `users`");
//            ResultSet Data = connection.fetchData(statement);
//            while (Data.next()) {
//                System.out.println(Data.getString(2));
//            }
        ///request.setAttribute("data", Data);
        // request.getRequestDispatcher("profile.jsp").include(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
