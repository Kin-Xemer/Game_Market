/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.GameDAO;
import daos.UserDAO;
import dtos.Category;
import dtos.Game;
import dtos.GameToAdd;
import dtos.ShoppingCartItem;
import dtos.Storage;
import dtos.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.net.www.content.image.gif;
import utils.DBConnect;

/**
 *
 * @author DAT PC
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

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

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String action = request.getParameter("action");
                HttpSession session = request.getSession();
                String gID = null;
                gID = request.getParameter("gID");
                ArrayList<Category> listcate = new ArrayList<Category>();
                GameDAO game = new GameDAO();
                listcate = game.getCategory();
                int total = 0;
                String users = (String) session.getAttribute("username");
//                if (users != null) {
//                    total = game.totalPage(username);
//                    request.setAttribute("totalPage", total);
//                } else {
//                    total = game.totalPage(username);
//                    request.setAttribute("totalPage", total);
//                }
                request.setAttribute("listcate", listcate);
                request.setAttribute("game", game);
                if (action != null) {
                    if (action.equalsIgnoreCase("login")) {
                        boolean isValid = UserDAO.checkLogin(username, password);
                        if (isValid == true) {
                            session.setAttribute("username", username);
                            session.setAttribute("password", password);
                            RequestDispatcher dispatch = request.getRequestDispatcher("MainPage.jsp");
                            dispatch.forward(request, response);
                        } else {

                            RequestDispatcher dispatch = request.getRequestDispatcher("LoginPage.jsp");
                            dispatch.forward(request, response);
                        }

                    } else if (action.equalsIgnoreCase("register")) {
                        String user = request.getParameter("user");
                        String pass = request.getParameter("pass");
                        String conPass = request.getParameter("confirmPassword");
                        String email = request.getParameter("email");
                        String country = request.getParameter("country");
                        UserDAO dao = new UserDAO();
                        ArrayList checkUserMail = dao.getEmail();
                        User u = new User(user, pass, email, country);
                        boolean result = dao.Register(u);
                        for (int k = 0; k <= checkUserMail.size(); k++) {
                            System.out.println(email.equals(checkUserMail.get(k)));
                            if (result == true && pass.equals(conPass) && !email.equalsIgnoreCase((String) checkUserMail.get(k))) {
                                request.removeAttribute("error");
                                request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
                            } else {
                                if (result == false) {
                                    request.setAttribute("error", "UserName has been taken!!");
                                }
                                if (!pass.equals(conPass)) {
                                    request.setAttribute("error", "Confirm password does not match!!");
                                }
                                if (email.equalsIgnoreCase((String) checkUserMail.get(k))) {
                                    request.setAttribute("error", "the email is in used!!");
                                }
                                request.getRequestDispatcher("RegisterPage.jsp").forward(request, response);
                            }
                        }
                    } else if (action.equalsIgnoreCase("addgame")) {

                        String gametitle = request.getParameter("title");
                        double price = Double.parseDouble(request.getParameter("price"));
                        String type = game.getIDbyType(request.getParameter("type"));
                        String des = request.getParameter("description");
                        String day = request.getParameter("releaseDate");
                        String rating = request.getParameter("rating");
                        String url = "img/" + request.getParameter("imageurl");
                        String Uname = (String) session.getAttribute("username");
                        String pub = request.getParameter("pub");
                        String dev = request.getParameter("dev");
                        String link = request.getParameter("link");
                        GameToAdd g = new GameToAdd(gametitle, price, type, des, pub, dev, day, Integer.parseInt(rating), url, Uname, link);
                        boolean result = game.createGame(g);
                        if (result == true) {
                            request.setAttribute("addmsgsuccess", "Add Successful !!");
                            request.getRequestDispatcher("MainPage.jsp").forward(request, response);
                        } else {
                            request.setAttribute("addmsg", "Add Error!! Please Add Again");
                            request.getRequestDispatcher("MainPage.jsp").forward(request, response);
                        }

                    } else if (action.equalsIgnoreCase("delete")) {
                        String gameID = request.getParameter("gID");
                        boolean result = game.deleteGame(gameID);
                        if (result == true) {
                            request.setAttribute("addmsgsuccess", "Delete Succesfull!!! ");
                            request.getRequestDispatcher("MainPage.jsp").forward(request, response);
                        } else {
                            request.setAttribute("addmsg", "Delete Error!! Please Delete Again");
                            request.getRequestDispatcher("MainPage.jsp").forward(request, response);
                        }
                    } else if (action.equalsIgnoreCase("listmygame")) {
                        String check = "ok";
                        request.setAttribute("listmygamecheck", check);
                        request.getRequestDispatcher("MainPage.jsp").forward(request, response);

                    } else if (action.equalsIgnoreCase("update")) {
                        String GameID = gID; //id game can update
                        String gametitle = request.getParameter("title");// lay tu updatepage
                        String price = request.getParameter("price");
                        String type = game.getIDbyType(request.getParameter("type"));
                        String des = request.getParameter("description");
                        String day = request.getParameter("releaseDate");
                        String rating = request.getParameter("rating");
                        String url = "img/" + request.getParameter("imageurl");
                        String Uname = (String) session.getAttribute("username");
                        String pub = request.getParameter("pub");
                        String dev = request.getParameter("dev");
                        String link = request.getParameter("link");
                        Game g = new Game(GameID, gametitle, Double.parseDouble(price), type, des, pub, dev, day, Integer.parseInt(rating), url, Uname, link); // con game da update roi
                        game.updateGame(g);
                        if (game.updateGame(g) == true) {
                            String checked = "ok";
                            request.setAttribute("addmsgsuccess", "Update Successful!!");
                            request.removeAttribute("listmygame");
                            request.setAttribute("listmygame", checked);
                            request.getRequestDispatcher("MainPage.jsp").forward(request, response);
                        } else {
                            request.setAttribute("addmsg", "Update Fail !!");
                            request.getRequestDispatcher("MainPage.jsp").forward(request, response);
                        }

                    } else if (action.equalsIgnoreCase("logout")) {
                        session.invalidate();
                        request.setAttribute("msg", action);
                        request.removeAttribute("listgame");
                        request.removeAttribute("listgamebytype");
                        request.removeAttribute("listcate");
                        request.removeAttribute("error");
                        request.removeAttribute("Type");
                        RequestDispatcher dispatch = request.getRequestDispatcher("LoginPage.jsp");
                        dispatch.forward(request, response);

                    } else if (action.equalsIgnoreCase("filter")) {
                        String typeBtn = request.getParameter("type");
                        GameDAO dao = new GameDAO();
                        ArrayList<Category> listcat = new ArrayList<>();
                        listcat = dao.getCategory();
                        for (int i = 0; i < listcat.size(); i++) {
                            if (typeBtn.equalsIgnoreCase(listcat.get(i).getCateType())) {
                                request.setAttribute("Type", typeBtn);
                                RequestDispatcher dispatch = request.getRequestDispatcher("MainPage.jsp?type=" + typeBtn);
                                dispatch.forward(request, response);
                            }
                        }

                    } else if (action.equalsIgnoreCase("paging")) {
                        int pageCurrent = Integer.parseInt(request.getParameter("page"));
                        RequestDispatcher dispatch = request.getRequestDispatcher("MainPage.jsp?page=" + pageCurrent);
                        dispatch.forward(request, response);

                    } else if (action.equalsIgnoreCase("addtocart")) {
                        request.getRequestDispatcher("CartServlet?gID=" + gID).forward(request, response);

                    } else if (action.equalsIgnoreCase("showInfo")) {
                        request.removeAttribute("error");
                        request.removeAttribute("gameByID");
                        request.removeAttribute("gameType");

                        String id = request.getParameter("gID");
                        String user = (String) session.getAttribute("username");
                        Game foundgame = game.getGameByID(id);
                        if (user != null && foundgame != null) {
                            String type = game.getTypeByID(foundgame.getCategoryID());
                            request.setAttribute("gameByID", foundgame);
                            request.setAttribute("gameType", type);
                            RequestDispatcher dispatch = request.getRequestDispatcher("GameInformationPage.jsp");
                            dispatch.forward(request, response);
                        } else {
                            RequestDispatcher dispatch = request.getRequestDispatcher("LoginPage.jsp");
                            dispatch.forward(request, response);
                        }

                    } else if (action.equalsIgnoreCase("Bought")) {
                        ArrayList<Game> listgamebougt = new ArrayList<>();
                        
                        ArrayList<String> id = game.getGameBoughtByName((String)session.getAttribute("username"));

                        for (int i = 0; i < id.size(); i++) {
                            listgamebougt.add(game.getGameByID(id.get(i)));
                        }
                        System.out.println(listgamebougt);
                        request.setAttribute("listbought", listgamebougt);
                        request.getRequestDispatcher("GameBought.jsp").forward(request, response);

                    } else if (action.equalsIgnoreCase("purchase")) {
                        ArrayList<ShoppingCartItem> listcart = (ArrayList<ShoppingCartItem>) session.getAttribute("cart");
                        boolean result = false;
                        for (int i = 0; i < listcart.size(); i++) {
                            String gameid = listcart.get(i).getGame().getGameID();
                            String uname = (String) session.getAttribute("username");
                            result = game.addGameStorage(gameid, uname);
                        }
                        if (result == true) {
                            request.setAttribute("addmsgsuccess", "Buy Successful !!");
                            request.getRequestDispatcher("MainPage.jsp").forward(request, response);
                            session.setAttribute("boughtgame", listcart);
                            session.removeAttribute("cart");
                        } else {
                            request.setAttribute("addmsg", "Buy Error!! Please Add Again");
                            request.getRequestDispatcher("MainPage.jsp").forward(request, response);
                        }
                    }

                } else if (gID != null) {
                    request.getRequestDispatcher("UpdatePage.jsp?gID=" + gID).forward(request, response);
                } else if (action == null) {
                    RequestDispatcher dispatch = request.getRequestDispatcher("MainPage.jsp");
                    dispatch.forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        processRequest(request, response);

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
