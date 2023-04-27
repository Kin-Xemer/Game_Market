<%-- 
    Document   : UpdatePage
    Created on : 13-Mar-2021, 21:12:28
    Author     : SE130280
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dtos.Category"%>
<%@page import="dtos.Category"%>
<%@page import="daos.GameDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dtos.Game"%>
<%@page import="dtos.Game"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update</title>
    </head>
    <body>

        <c:set var="gID" value="${param.gID}"></c:set>
        <c:set var="dao" value="${game}"></c:set>
        <c:set var="listgame" value="${game.getGameByID(gID)}"></c:set>
        <form action="MainServlet?action=update&gID=${param.gID}" method="POST">
            <h2  class="modal__title">Update</h2>

            <br><label for="title">Game Title</label>
            <input type="text" name="title" value="${listgame.gameName}" required/>

            <br><label for="image-url">Image URL</label>
            <input type="file" name="imageurl" style="border:none"/>

            <br><label for="rating">Your Rating</label>
            <input type="number" step="1" max="5" min="1" name="rating" value="${listgame.rating}"id="rating" required/>

            <br><label for="description">Description</label>
            <input type="text" name="description" value="${listgame.description}" />

            <br><label for="releaseDate">Release Date</label>
            <input type="date" name="releaseDate" value="${listgame.releaseDay}"  id="releaseDate" required/>

            <br><label for="publisherId">Publisher </label>
            <input type="text" name="pub" value="${listgame.pubID}" id="publisherId" required/>

            <br><label for="developerId">Developer </label>
            <input type="text" name="dev" value="${listgame.devID}" id="developerId" required/>

            <br><label for="price">Price</label>
            <input type="number" name="price" step="0.01" value="${listgame.price}" id="price" required/>

            <label for="link">Link Download</label>
            <input type="text" name="link" value="${listgame.linkdown}" required/>

            <br><label for="gameType">Type</label>
            <select id="gameType" name="type" >


                <c:forEach items="${dao.getCategory()}" var="list">
                    <option class="type" value="${list.cateType}" > <c:out value="${list.cateType}"/> </option>

                </c:forEach>
            </select>
            <br><button class="btn btn--passive">Cancel</button>
            <br><button class="btn btn--success">Update</button>
        </form>
    </body>
</html>
