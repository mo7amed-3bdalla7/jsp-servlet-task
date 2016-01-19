/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.RowFilter;
import libs.databaseModel;
import model.userModel;

/**
 *
 * @author M.A
 */
public class signup extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null) {
            request.setAttribute("display", true);
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else {
            response.sendRedirect("login");
        }
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
            databaseModel dbModel = databaseModel.getConnection();
            String fname = request.getParameter("frist_name");
            String lname = request.getParameter("last_name");
            int age = Integer.parseInt(request.getParameter("age"));
            
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            String confirmPpassword = request.getParameter("cpassword");
            String email = request.getParameter("email");
            PreparedStatement statement = dbModel.statement("select * from users where email = ? or phone = ?");
            statement.setString(1, email);
            statement.setString(2, phone);
            ResultSet fetchData = dbModel.fetchData(statement);

            if(age<=18){
                request.setAttribute("error", "Age Must be At Least 18 year ! ");
                request.setAttribute("display", true);
                request.getRequestDispatcher("login.jsp").include(request, response);
            }else if(!phone.matches("\\d{11}")){
                request.setAttribute("error", "Phone Must be 11 Didits ! ");
                request.setAttribute("display", true);
                request.getRequestDispatcher("login.jsp").include(request, response);
            }else if(!email.matches("^\\S+@\\S+[.]\\S+$")){
                request.setAttribute("error", "Not vallidated EMAIL ! ");
                request.setAttribute("display", true);
                request.getRequestDispatcher("login.jsp").include(request, response);
            }else if (fetchData.next()) {
                request.setAttribute("error", "You May Registered Before <a href=\"#\" > Forget Your Password! </a> ");
                request.setAttribute("display", true);
                request.getRequestDispatcher("login.jsp").include(request, response);
            } else if (!confirmPpassword.equals(password)) {
                request.setAttribute("error", "Passwords Don't Match ! ");
                request.setAttribute("display", true);
                request.getRequestDispatcher("login.jsp").include(request, response);
            } else {

                userModel newUser = new userModel();
                newUser.setFristName(fname);
                newUser.setLastName(lname);
                newUser.setAge(age);
                newUser.setPhone(phone);
                newUser.setEmail(email);
                newUser.setPassword(password);
                // newUser.setPhoto(phone);
                if (newUser.save() == 1) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", newUser);
                    response.sendRedirect("profile");
                } else {
                    request.setAttribute("error", "Check Your Data !!!! ");
                    request.setAttribute("display", true);
                    request.getRequestDispatcher("login.jsp").include(request, response);
                }
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
