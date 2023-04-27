/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.GameDAO;
import dtos.ShoppingCartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SE130280
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {

    public CartServlet() {
        super();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        String action = request.getParameter("action");
        if (action == null || action.equalsIgnoreCase("ok") ) {
            displayCart(request, response);
        } else {
            if (action.equalsIgnoreCase("addtocart")) {//add item into cart
                buyItem(request, response);
            } else if (action.equalsIgnoreCase("delete")) {
                removeItem(request, response);
            }
        }

    }

    protected void displayCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("CartPage.jsp").forward(request, response);
    }

    protected void buyItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GameDAO dao = new GameDAO();
        String gID = request.getParameter("gID");
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            ArrayList<ShoppingCartItem> cart = new ArrayList<>();
            cart.add(new ShoppingCartItem(dao.find(gID)));
            session.setAttribute("cart", cart);//cứ mỗi lần add iteam l
        } else {// neu gio hang dang co du lieu
            ArrayList<ShoppingCartItem> cartItems = (ArrayList<ShoppingCartItem>) session.getAttribute("cart");
            int index = isExisting(gID, cartItems);
            if (index == -1) {
                cartItems.add(new ShoppingCartItem(dao.find(gID)));
            } else {
                request.setAttribute("addmsg", "You already added this game !! Please choose another game");
                
                
                
                if (request.getParameter("act") == null) {
                    request.getRequestDispatcher("MainPage.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg", "You already added this game !!");
                    request.getRequestDispatcher("MainServlet?action=showInfo&gID=" + gID).forward(request, response);
                }
                
                
                
            }
            session.setAttribute("cart", cartItems);
        }
        request.setAttribute("addmsgsuccess", "Add your cart successful !!");
        if (request.getParameter("act") == null) {
            request.getRequestDispatcher("MainPage.jsp").forward(request, response);
        } else {
            request.setAttribute("cartmsg", "Add your cart successful !!");
            request.getRequestDispatcher("CartPage.jsp").forward(request, response);
        }
    }

    protected int isExisting(String id, ArrayList<ShoppingCartItem> cartItems) {
        for (int i = 0; i < cartItems.size(); i++) {
            if (String.valueOf(cartItems.get(i).getGame().getGameID()).equalsIgnoreCase(id))// kiem tra xem trong gio hang co item nay k {
            {
                return i;// tra ve vi tri
            }
        }
        return -1;
    }

    protected void removeItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<ShoppingCartItem> cart = (ArrayList<ShoppingCartItem>) session.getAttribute("cart");
        int index = isExisting(request.getParameter("gID"), cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        request.getRequestDispatcher("CartPage.jsp").forward(request, response);
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
