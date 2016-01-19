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
import libs.databaseModel;
import model.depositeModel;
import model.userModel;

/**
 *
 * @author M.A
 */
public class deposite extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

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
        try {
            HttpSession session = request.getSession(false);
            if (session.getAttribute("user") != null) {
                userModel user = (userModel) session.getAttribute("user");
                databaseModel connection = databaseModel.getConnection();
                ResultSet account = depositeModel.getAccount(user.getAccountNum());
                if (account.next()) {
                    request.getSession().setAttribute("account", account);
                    request.getRequestDispatcher("deposite.jsp").forward(request, response);
                } else {
                    response.sendRedirect("login");
                }

            } else {
                response.sendRedirect("login");
            }
        } catch (SQLException ex) {
            Logger.getLogger(deposite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(deposite.class.getName()).log(Level.SEVERE, null, ex);
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
            String deposite = request.getParameter("deposite");
            int parsedeposite = Integer.parseInt(deposite);
            HttpSession session = request.getSession(false);
            Object accounAattribute = session.getAttribute("account");
            if (parsedeposite > 0 && accounAattribute != null && session != null) {
                depositeModel depositeModel = new depositeModel();
                depositeModel.account = (ResultSet) accounAattribute;
                boolean addDeposite = depositeModel.addDeposite(parsedeposite);
                if (!addDeposite) {
                    request.setAttribute("error", "Deposite failed !!!");
                    request.getRequestDispatcher("deposite.jsp").forward(request, response);
                } else {
                    if (session.getAttribute("user") != null) {
                        userModel user = (userModel) session.getAttribute("user");
                        ResultSet account = depositeModel.getAccount(user.getAccountNum());
                        if (account.next()) {
                            request.getSession().setAttribute("account", account);
                        }
                    }
                    response.sendRedirect("profile");
                }
            } else {
                request.setAttribute("error", "You Cant't Provide Minus Values !!!");
                request.getRequestDispatcher("deposite.jsp").forward(request, response);
            }
        } catch (NumberFormatException | ServletException | IOException numberFormatException) {
            request.setAttribute("error", "You Cant't Non Numeric Values !!!");
            request.getRequestDispatcher("deposite.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(deposite.class.getName()).log(Level.SEVERE, null, ex);
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
