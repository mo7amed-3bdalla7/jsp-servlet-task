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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.depositeModel;
import model.transfermodel;

/**
 *
 * @author mohamedfashkl
 */
@WebServlet(name = "transfer", urlPatterns = {"/transfer"})
public class transfer extends HttpServlet {

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
//        request.getRequestDispatcher("transfer.jsp").include(request, response);

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
        request.getRequestDispatcher("transfer.jsp").forward(request, response);
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
            int reciver = Integer.parseInt(request.getParameter("acnum"));
            double value = Double.parseDouble(request.getParameter("tansvalue"));
            HttpSession session = request.getSession(false);
            ResultSet account = (ResultSet) session.getAttribute("account");
            if (value > 0) {
                if (account.getInt(1) != reciver) {
                    if (account.getInt(2) >= value) {
                        transfermodel transfer = new transfermodel();
                        boolean check = transfer.transfer(account.getInt(1), reciver, value);
                        if (check) {
                            if (session.getAttribute("user") != null) {
                                ResultSet refrechedaccount = depositeModel.getAccount(account.getInt(1));
                                if (refrechedaccount.next()) {
                                    request.getSession().setAttribute("account", refrechedaccount);
                                }
                            }
                            response.sendRedirect("profile");
                        } else {
                            request.setAttribute("error", "Check your Input Data !!!");
                            request.getRequestDispatcher("transfer.jsp").forward(request, response);

                        }
                    } else {
                        request.setAttribute("error", "Check your Input Data You Can't Transfer Value Larger Than Your Balance!!!");
                        request.getRequestDispatcher("transfer.jsp").forward(request, response);

                    }
                } else {

                    request.setAttribute("error", "Check your Input Data You Can't Transfer to your Self!!!");
                    request.getRequestDispatcher("transfer.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "You can't Add minus or less Zero Values !!!");
                request.getRequestDispatcher("transfer.jsp").forward(request, response);
            }
        } catch (NumberFormatException x) {
            request.setAttribute("error", "You can't Add non valid  Values ^_^ !!!");
            request.getRequestDispatcher("transfer.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(transfer.class.getName()).log(Level.SEVERE, null, ex);
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
