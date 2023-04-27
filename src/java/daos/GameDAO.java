/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Category;
import dtos.Game;
import dtos.GameToAdd;
import dtos.ShoppingCartItem;
import dtos.Storage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import sun.security.pkcs11.Secmod;
import utils.DBConnect;

/**
 *
 * @author SE130280
 */
public class GameDAO {

    private List<Game> listgame;
    Connection con = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    public GameDAO() {
        try {
            this.listgame = getAllGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Game> getAll() {
        return this.listgame;
    }

    public Game find(String id) {
        for (Game game : this.listgame) {
            if (String.valueOf(game.getGameID()).equalsIgnoreCase(id)) {
                return game;
            }
        }
        return null;
    }
    public ArrayList<String> getGameBoughtByName(String username)throws SQLException, NamingException{
        ArrayList<String> listID = new ArrayList<>();
        String sql = "Select GameID from GameStorage where Username = ?";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);
                rs = pstm.executeQuery();
                while (rs.next()) {                    
                    int idgame = rs.getInt("GameID");
                    listID.add(String.valueOf(idgame));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listID;
    } 
    public ArrayList<Storage> getAllStorage() throws SQLException, NamingException {
        ArrayList<Storage> list = new ArrayList<>();
        String sql = "select * from GameStorage";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {                    
                    int id = rs.getInt("StorageID");
                    int gameid = rs.getInt("GameID");
                    String buyname = rs.getString("Username");
                    list.add(new Storage(id, gameid, buyname));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addGameStorage(String gameID, String username) throws Exception {
        String sql = "insert into GameStorage(GameID, Username) values (?,?)";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, gameID);
                pstm.setString(2, username);
                pstm.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public String getIDbyType(String type) {
        String gametype = null;
        String sql = "select CatID\n"
                + "from Category\n"
                + "where Catetype = ?";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, type);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    gametype = rs.getString("CatID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gametype;
    }

    public boolean deleteGame(String id) throws SQLException, NamingException {
        String sql = "DELETE FROM Game WHERE GameID=?";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, id);

                pstm.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateGame(Game game) throws NamingException, SQLException {
        String sql = "update Game set Gamename=?, Price=?, CategoryID=?, Description=?,"
                + "ReleaseDay=?, Rating=?, imgURL=?, username=?, PubID=?, DevID=?,linkdown=? where GameID=?";

        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);

                pstm.setString(1, game.getGameName());;
                pstm.setDouble(2, game.getPrice());
                pstm.setString(3, game.getCategoryID());
                pstm.setString(4, game.getDescription());
                pstm.setString(5, game.getReleaseDay());
                pstm.setInt(6, game.getRating());
                pstm.setString(7, game.getImgURL());
                pstm.setString(8, game.getUsername());
                pstm.setString(9, game.getPubID());
                pstm.setString(10, game.getDevID());
                pstm.setString(11, game.getLinkdown());
                pstm.setString(12, game.getGameID());
                pstm.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {

            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                pstm.close();
            }
        }
        return false;
    }

    public Game getGameByID(String id) throws SQLException {
        String sql = "select * from Game where GameID = ? ";

        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, id);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    String gameID = rs.getString("GameID");
                    String gameName = rs.getString("Gamename");
                    double price = rs.getDouble("Price");
                    String CateID = rs.getString("CategoryID");
                    String des = rs.getString("Description");
                    String pubID = rs.getString("PubID");
                    String devID = rs.getString("DevID");
                    String ReleaseDay = (String) rs.getString("ReleaseDay");
                    int rating = rs.getInt("Rating");
                    String imgUrl = rs.getString("imgURL");
                    String username = rs.getString("username");
                    String link = rs.getString("linkdown");
                    Game game = new Game(gameID, gameName, price, CateID, des, pubID, devID, ReleaseDay, rating, imgUrl, username, link);
                    return game;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public boolean createGame(GameToAdd game) throws NamingException, SQLException {
        try {
            String sql = "INSERT INTO Game(Gamename, Price, CategoryID, "
                    + "Description, DevID, PubID, ReleaseDay, Rating, imgURL, username, linkdown)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)"
                    + "SELECT SCOPE_IDENTITY()";
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);

                pstm.setString(1, game.getGameName());
                pstm.setDouble(2, game.getPrice());
                pstm.setString(3, game.getCategoryID());
                pstm.setString(4, game.getDescription());
                pstm.setString(5, game.getPubID());
                pstm.setString(6, game.getDevID());
                pstm.setString(7, game.getReleaseDay());
                pstm.setInt(8, game.getRating());
                pstm.setString(9, game.getImgURL());
                pstm.setString(10, game.getUsername());
                pstm.setString(11, game.getLinkdown());
                pstm.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                con.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }

    public int GameCount() {
        int count = 0;

        return 0;

    }

    public ArrayList<Game> getAllGame() throws SQLException, NamingException {
        ArrayList<Game> list = new ArrayList<>();

        String sql = "select * from Game";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    String gameID = rs.getString("GameID");
                    String gameName = rs.getString("Gamename");
                    double price = rs.getDouble("Price");
                    String CateID = rs.getString("CategoryID");
                    String des = rs.getString("Description");
                    String pubID = rs.getString("PubID");
                    String devID = rs.getString("DevID");
                    String ReleaseDay = (String) rs.getString("ReleaseDay");
                    int rating = rs.getInt("Rating");
                    String imgUrl = rs.getString("imgURL");
                    String username = rs.getString("username");
                    String link = rs.getString("linkdown");
                    list.add(new Game(gameID, gameName, price, CateID, des, pubID, devID, ReleaseDay, rating, imgUrl, username, link));

                }
            }
        } catch (Exception e) {
        }

        return list;
    }

    public ArrayList<Category> getCategory() throws NamingException, SQLException {
        ArrayList<Category> listCate = new ArrayList<>();
        String sql = "select * from Category";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String cateID = rs.getNString("CatID");
                    String type = rs.getString("Catetype");
                    String des = rs.getString("Description");
                    Category c = new Category(cateID, type, des);
                    listCate.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCate;
    }

    public String getTypeByID(String id) {
        String gametype = null;
        String sql = "select Catetype\n"
                + "from Category\n"
                + "where CatID = ?";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, id);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    gametype = rs.getString("Catetype");
                }
            }
        } catch (Exception e) {
        }
        return gametype;
    }

    public int totalPage(String username, int totalGamesPerPage, boolean isUser) throws SQLException, NamingException {
        ArrayList<Game> totalGame = new ArrayList();
        if (isUser == false) {
            System.out.println("not in");
            if (username != null) {
                totalGame = getGameNotUser(username);
            } else if (username == null) {
                totalGame = getAllGame();
            }
        } else {
            System.out.println("on user");
            totalGame = getGamesByUser(username);
        }
        int count = 1;
        if (totalGame.size() > totalGamesPerPage) {
            if (totalGame.size() % totalGamesPerPage == 0) {
                count = totalGame.size() / totalGamesPerPage;
            } else {
                count = 1 + totalGame.size() / totalGamesPerPage;
            }
        } else if (totalGame.size() <= totalGamesPerPage) {
            count = 1;
        }
        return count;
    }

    public int totalPageByType(String username, String type, int totalGamesPerPage, boolean isUser) throws SQLException, NamingException {
        ArrayList<Game> totalGame = new ArrayList();
        if (isUser == false) {
            if (username == null) {
                totalGame = getGamesByType(type);
            } else if (username != null) {
                totalGame = getGamesByTypeNotUser(type, username);
            }
        } else {
            totalGame = getMyGamesByType(type, username);
        }
        int count = 1;
        if (totalGame.size() > totalGamesPerPage) {
            if (totalGame.size() % totalGamesPerPage == 0) {
                count = totalGame.size() / totalGamesPerPage;
            } else {
                count = 1 + totalGame.size() / totalGamesPerPage;
            }
        } else if (totalGame.size() <= totalGamesPerPage) {
            count = 1;
        }
        return count;
    }

    public ArrayList<Game> getGameByPageNotUser(String username, int total, int currentPage) {
        ArrayList<Game> totalGame = getGameNotUser(username);
        ArrayList<Game> gamesOnPage = new ArrayList();
        int count = 0;
        int page = 1;
        int i = 0;
        for (; i < totalGame.size(); i++) {
            if (page == currentPage) {
                gamesOnPage.add(totalGame.get(i));
                System.out.println(gamesOnPage.size());
            }
            count++;
            if (count == total) {
                i = total - 1;
                count = 0;
                page++;
                System.out.println(gamesOnPage);
            }
        }
        return gamesOnPage;
    }

    public ArrayList<Game> getGameByTypeOnPage(String typeid, int totalGamePerPage, String username, int currentPage, Boolean isUser) throws SQLException, NamingException {
        ArrayList<Game> totalGameByType = new ArrayList();
        if (isUser == false) {
            if (username != null) {
                totalGameByType = getGamesByTypeNotUser(typeid, username);
            } else if (username == null) {
                totalGameByType = getGamesByType(typeid);
            }
        } else {
            totalGameByType = getMyGameByTypeOnPage(typeid, totalGamePerPage, username, currentPage);
        }
//        } else if (username == null && typeid == null) {
//            totalGameByType = getAllGame();
//        } else if (username != null && typeid == null) {
//            totalGameByType = getGameNotUser(username);
//        }
//        ArrayList<Game> gamesByPage = getGameByPageNotUser(username, totalGameOnPage);
        ArrayList<Game> gamesOnPage = new ArrayList();
        int count = 0;
        int page = 1;
        for (int i = 0; i < totalGameByType.size(); i++) {
            if (page == currentPage) {
                System.out.println("I: " + i);
                gamesOnPage.add(totalGameByType.get(i));
                System.out.println(gamesOnPage.size());

            }
            count++;
            if (count == totalGamePerPage) {
                count = 0;
                page++;
                System.out.println("final Game num: " + gamesOnPage.size());
                System.out.println(i);
            }

        }
        return gamesOnPage;
    }

    public ArrayList<Game> getMyGameByTypeOnPage(String typeid, int totalGamePerPage, String username, int currentPage) throws SQLException, NamingException {
        ArrayList<Game> totalGameByType = new ArrayList();
        totalGameByType = getMyGamesByType(typeid, username);
        ArrayList<Game> gamesOnPage = new ArrayList();
        int count = 0;
        int page = 1;
        for (int i = 0; i < totalGameByType.size(); i++) {
            if (page == currentPage) {
                System.out.println("I: " + i);
                gamesOnPage.add(totalGameByType.get(i));
                System.out.println(gamesOnPage.size());

            }
            count++;
            if (count == totalGamePerPage) {
                count = 0;
                page++;
                System.out.println("final Game num: " + gamesOnPage.size());
                System.out.println(i);
            }

        }
        return gamesOnPage;
    }

//    public ArrayList<Game> getGameByTypeOnPage(String typeid, int totalGamePerPage, int currentPage) throws SQLException {
//        ArrayList<Game> totalGameByType = getGamesByType(typeid);
////        ArrayList<Game> gamesByPage = getGameByPageNotUser(username, totalGameOnPage);
//        ArrayList<Game> gamesOnPage = new ArrayList();
//        int count = 0;
//        int tmp = 0;
//        int page = 1;
//        int i = 0;
//        for (; i < totalGameByType.size(); i++) {
//            if (page == currentPage) {
//                System.out.println("I: " + i);
//                gamesOnPage.add(totalGameByType.get(i));
//                System.out.println(gamesOnPage.size());
//
//            }
//            count++;
//            if (count == totalGamePerPage) {
//                tmp = i;
//                i = totalGamePerPage - 1;
//                count = 0;
//                page++;
//                System.out.println("final Game num: " + gamesOnPage.size());
//                System.out.println(i);
//            }
////            gamesOnPage.add(totalGameByType.get(count));
//
//        }
//        return gamesOnPage;
//    }
    public ArrayList<Game> getGamesByUser(String user) throws SQLException {

        ArrayList<Game> list = new ArrayList<>();
        String sql = "select * from Game where username = ?";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, user);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String gameID = rs.getString("GameID");
                    String gameName = rs.getString("Gamename");
                    double price = rs.getDouble("Price");
                    String CateID = rs.getString("CategoryID");
                    String des = rs.getString("Description");
                    String pubID = rs.getString("PubID");
                    String devID = rs.getString("DevID");
                    String ReleaseDay = (String) rs.getString("ReleaseDay");
                    int rating = rs.getInt("Rating");
                    String imgUrl = rs.getString("imgURL");
                    String username = rs.getString("username");
                    String link = rs.getString("linkdown");
                    list.add(new Game(gameID, gameName, price, CateID, des, pubID, devID, ReleaseDay, rating, imgUrl, username, link));

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                pstm.close();
            }
        }
        return list;
    }

    public ArrayList<Game> getGameOnPage(int totalGamePerPage, int currentPage, String username, Boolean isUser) throws SQLException, NamingException {
        ArrayList<Game> totalGameByType = new ArrayList();
        if (isUser == false) {
            System.out.println("in");
            if (username != null) {
                totalGameByType = getGameNotUser(username);
            } else if (username == null) {
                totalGameByType = getAllGame();
            }
        } else {
            System.out.println("out");
            totalGameByType = getGamesByUser(username);
        }
//        ArrayList<Game> gamesByPage = getGameByPageNotUser(username, totalGameOnPage);
        ArrayList<Game> gamesOnPage = new ArrayList();
        int count = 0;
        int tmp = 0;
        int paging = 1;

        System.out.println(totalGameByType.size());
        for (int i = 0; i < totalGameByType.size(); i++) {

            if (paging == currentPage) {
                System.out.println("I: " + i);
                gamesOnPage.add(totalGameByType.get(i));
                System.out.println(gamesOnPage.size());

            }
            count++;
            System.out.println(count);
            if (count == totalGamePerPage) {
                count = 0;
                paging = paging + 1;
                System.out.println("final Game num: " + gamesOnPage.size());
                System.out.println(paging);
            }
//            gamesOnPage.add(totalGameByType.get(count));

        }
        return gamesOnPage;
    }

//    public ArrayList<Game> getMyGameOnPage(int totalGamePerPage, int currentPage, String username) throws SQLException, NamingException {
//        ArrayList<Game> totalGameByType = new ArrayList();
//        if (username != null) {
//            totalGameByType = getGamesByUser(username);
//        }
////        ArrayList<Game> gamesByPage = getGameByPageNotUser(username, totalGameOnPage);
//        ArrayList<Game> gamesOnPage = new ArrayList();
//        int count = 0;
//        int page = 1;
//        int i = 0;
//        for (; i < totalGameByType.size(); i++) {
//            if (page == currentPage) {
//                System.out.println("I: " + i);
//                gamesOnPage.add(totalGameByType.get(i));
//                System.out.println(gamesOnPage.size());
//
//            }
//            count++;
//            if (count == totalGamePerPage) {
//                count = 0;
//                page++;
//                System.out.println("final Game num: " + gamesOnPage.size());
//                System.out.println(i);
//            }
////            gamesOnPage.add(totalGameByType.get(count));
//
//        }
//        return gamesOnPage;
//    }
    public ArrayList<Game> getGameNotUser(String username) {
        ArrayList<Game> list = new ArrayList<>();
        String sql = "  select * from Game where username != ? ";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String gameID = rs.getString("GameID");
                    String gameName = rs.getString("Gamename");
                    double price = rs.getDouble("Price");
                    String CateID = rs.getString("CategoryID");
                    String des = rs.getString("Description");
                    String pubID = rs.getString("PubID");
                    String devID = rs.getString("DevID");
                    String ReleaseDay = (String) rs.getString("ReleaseDay");
                    int rating = rs.getInt("Rating");
                    String imgUrl = rs.getString("imgURL");
                    String notUsername = rs.getString("username");
                    String link = rs.getString("linkdown");
                    list.add(new Game(gameID, gameName, price, CateID, des, pubID, devID, ReleaseDay, rating, imgUrl, notUsername, link));
                }
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<Game> getRecords(int start, int end) throws SQLException {
        ArrayList<Game> list = new ArrayList<>();
        String sql = "WITH NumberedMyTable AS\n"
                + "( SELECT * ,ROW_NUMBER() OVER (ORDER BY Game.GameID) AS RowNumber FROm Game)\n"
                + "SELECT *FROM NumberedMyTable\n"
                + "WHERE RowNumber BETWEEN " + start + " AND " + end;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    Game g = new Game();
                    g.setGameID(rs.getString("GameID"));
                    g.setGameName(rs.getString("Gamename"));
                    g.setPrice(rs.getInt("Price"));
                    g.setCategoryID(rs.getString("CategoryID"));
                    g.setDescription(rs.getString("Description"));
                    g.setPubID(rs.getString("PubID"));
                    g.setDevID(rs.getString("DevID"));
                    g.setReleaseDay(rs.getString("ReleaseDay"));
                    g.setRating(rs.getInt("Rating"));
                    g.setImgURL(rs.getString("imgURL"));
                    g.setUsername(rs.getString("username"));
                    g.setLinkdown(rs.getString("linkdown"));
                    list.add(g);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                pstm.close();
            }
        }
        return list;
    }

    public ArrayList<Game> getGamesByType(String typeId) throws SQLException {

        ArrayList<Game> list = new ArrayList<>();
        String sql = "select * from Game g, Category c where g.CategoryID=c.CatID and c.Catetype=? ";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, typeId);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String GameID = rs.getString("GameID");
                    String Gamename = rs.getString("Gamename");
                    String Price = rs.getString("Price");
                    String CategoryID = rs.getString("CategoryID");
                    String Description = rs.getString("Description");
                    String PubID = rs.getString("PubID");
                    String DevID = rs.getString("DevID");
                    String ReleaseDay = rs.getString("ReleaseDay");
                    int Rating = rs.getInt("Rating");
                    String imgURL = rs.getString("imgURL");
                    String username = rs.getString("username");
                    String link = rs.getString("linkdown");
                    Game g = new Game(GameID, Gamename, Double.parseDouble(Price), CategoryID, Description, PubID, DevID, ReleaseDay, Rating, imgURL, username, link);
                    list.add(g);

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                pstm.close();
            }
        }
        return list;
    }

    public ArrayList<Game> getMyGamesByType(String typeId, String user) throws SQLException {

        ArrayList<Game> list = new ArrayList<>();
        String sql = "select * from Game g, Category c where g.CategoryID=c.CatID and c.Catetype=? and username =? ";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, typeId);
                pstm.setString(2, user);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String GameID = rs.getString("GameID");
                    String Gamename = rs.getString("Gamename");
                    String Price = rs.getString("Price");
                    String CategoryID = rs.getString("CategoryID");
                    String Description = rs.getString("Description");
                    String PubID = rs.getString("PubID");
                    String DevID = rs.getString("DevID");
                    String ReleaseDay = rs.getString("ReleaseDay");
                    int Rating = rs.getInt("Rating");
                    String imgURL = rs.getString("imgURL");
                    String username = rs.getString("username");
                    String link = rs.getString("linkdown");
                    Game g = new Game(GameID, Gamename, Double.parseDouble(Price), CategoryID, Description, PubID, DevID, ReleaseDay, Rating, imgURL, username, link);
                    list.add(g);

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                pstm.close();
            }
        }
        return list;
    }

    public ArrayList<Game> getGamesByTypeNotUser(String typeId, String userName) throws SQLException {

        ArrayList<Game> list = new ArrayList<>();
        String sql = "select * from Game g, Category c where g.CategoryID=c.CatID and c.Catetype=? and username != ?";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, typeId);
                pstm.setString(2, userName);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String GameID = rs.getString("GameID");
                    String Gamename = rs.getString("Gamename");
                    String Price = rs.getString("Price");
                    String CategoryID = rs.getString("CategoryID");
                    String Description = rs.getString("Description");
                    String PubID = rs.getString("PubID");
                    String DevID = rs.getString("DevID");
                    String ReleaseDay = rs.getString("ReleaseDay");
                    int Rating = rs.getInt("Rating");
                    String imgURL = rs.getString("imgURL");
                    String username = rs.getString("username");
                    String link = rs.getString("linkdown");
                    Game g = new Game(GameID, Gamename, Double.parseDouble(Price), CategoryID, Description, PubID, DevID, ReleaseDay, Rating, imgURL, username, link);
                    list.add(g);

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                pstm.close();
            }
        }
        return list;
    }

    public static void main(String[] args) throws SQLException, NamingException {
        GameDAO dao = new GameDAO();
////        System.out.println(dao.getGamesByTypeNotUser("action" , "handsome").get(1).getGameName());
////        System.out.println(dao.getGameOnPage(8, 2));
////System.out.println(dao.getGameByPageNotUser("dat", 8, 2));
////        System.out.println(dao.getGameByTypeOnPageNotUser("action", 8, "kien", 1));
////        System.out.println(dao.getGameOnPage(8,1,"dat"));
////        System.out.println(dao.getGameNotUser("kien"));
////        System.out.println(dao.totalPage("kien"));
////System.out.println(dao.getGameOnPage(8,1,"handsome"));
////        System.out.println(dao.getGameByTypeOnPage("puzzle", 8, "", 1));
////        System.out.println(dao.totalPage("", 8));
////        System.out.println(dao.totalPageByType("", "action", 1));
//System.out.println(dao.getGamesByTypeNotUser("action", "kien").get(1).getGameName());
//        System.out.println(dao.getGameByTypeOnPage("Action", 8, "dat", 1));
//        System.out.println(dao.getMyGamesByType("action", "dat"));
//        System.out.println(dao.getMyGameByTypeOnPage("action", 8, "dat", 2));
//        System.out.println(dao.getAllGame());
////        System.out.println(dao.getCategory());
////        System.out.println(dao.getLastPage("","", 8));
////System.out.println(dao.getGameByID("2"));
//        System.out.println(dao.getGamesByTypeNotUser("action"));  
//        System.out.println(dao.totalPage("kien", 8, true));
//System.out.println(dao.getGameOnPage(8,1,"kien",false));
    }
}
