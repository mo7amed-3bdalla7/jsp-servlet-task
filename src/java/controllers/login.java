/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.depositeModel;
import model.userModel;

/**
 *
 * @author mohamed mabrouk
 */
public class login extends HttpServlet {

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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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

        try {
            userModel user = new userModel();
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String hasedpassword = user.hashPassword(password);
            ResultSet authantication = user.authantication(email, hasedpassword);
            if (authantication.next()) {
                user.setId(authantication.getInt(1));
                user.setFristName(authantication.getString(2));
                user.setLastName(authantication.getString(3));
                user.setPhone(authantication.getString(4));
                user.setEmail(authantication.getString(5));
                user.setAge(authantication.getInt(6));
                user.setPassword(authantication.getString(7));
                user.setAccountNum(authantication.getInt(8));
                // System.out.println(authantication.getInt(8));
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(60 * 60 * 24 * 30);
                ResultSet account = depositeModel.getAccount(user.getAccountNum());
                if (account.next()) {
                    session.setAttribute("account", account);
                }
                //System.out.println(user);
                response.sendRedirect("profile");

            } else {
                request.setAttribute("loginError", "Check your Data !!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);

            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }

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
