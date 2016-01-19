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
import model.withdrawModel;
import model.userModel;

/**
 *
 * @author M.A
 */
public class withdraw extends HttpServlet {

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

        request.getRequestDispatcher("withdraw.jsp").forward(request, response);
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
            String withdraw = request.getParameter("withdraw");
            int parsewithdraw = Integer.parseInt(withdraw);
            HttpSession session = request.getSession(false);
            ResultSet accounAattribute = (ResultSet) session.getAttribute("account");
            if (parsewithdraw > 0 && accounAattribute != null && session != null) {
                if (accounAattribute.getInt(2) > parsewithdraw) {
                    withdrawModel withdrawModel = new withdrawModel();
                    withdrawModel.account = (ResultSet) accounAattribute;
                    boolean addDeposite = withdrawModel.addwithdraw(parsewithdraw);
                    if (!addDeposite) {
                        request.setAttribute("error", "Withdraw failed !!!");
                        request.getRequestDispatcher("withdraw.jsp").forward(request, response);
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
                }else{
                    request.setAttribute("error", "You Cant't Provide Value More Than Your Balance !!!");
                request.getRequestDispatcher("withdraw.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "You Cant't Provide Minus Values !!!");
                request.getRequestDispatcher("withdraw.jsp").forward(request, response);
            }
        } catch (NumberFormatException | ServletException | IOException numberFormatException) {
            request.setAttribute("error", "Check Your Data !!!");
            request.getRequestDispatcher("withdraw.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(withdraw.class.getName()).log(Level.SEVERE, null, ex);
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
