<%-- 
    Document   : CartPage
    Created on : 18-Mar-2021, 20:37:49
    Author     : SE130280
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" />
        <link rel="stylesheet" href="assets/AddToCartPageAssets/style.css" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
            />
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css" />
        <link
            href="https://fonts.googleapis.com/icon?family=Material+Icons"
            rel="stylesheet"
            />
        <title>Game Steam</title>
    </head>

    <body style="background-image: url(img/bg-body.png)">
        <div class="container">
            <section class="top-info menu">
                <div class="head">
                    <div class="col-6">
                        <ul id="more-info">
                            <li><a href="">Help</a></li>
                            <li><a href="">About us</a></li>
                            <li><a href="">Friends</a></li>
                        </ul>
                    </div>
                    <div class="col-6">
                        <div id="account"><a href="MainServlet">Your Account</a></div>
                    </div>
                </div>
            </section>
            
            <section class="body-info">
                <label style="color: #5cb85c">${requestScope.cartmsg}</label>
                <div class="game-box col-12">
                    <div class="game-titel-box">
                        <div class="game-title-guide">
                            <a href="">All Game</a>>><a href="CartPage.jsp">Your Cart</a>
                        </div>
                        <div class="game-main-title">
                            <h2 id="gameTitle">YOUR SHOPPING CART</h2>
                        </div>
                    </div>


                    <div class="leftcol">
                        <c:set var="ldt" value="${sessionScope.cart}"/>
                        <c:set var="total" value="${0}" />
                        <c:choose>
                            <c:when test="${not empty ldt}" >

                                <c:forEach var="list" items="${sessionScope.cart}">
                                    <c:set var="total" value="${ total + list.game.price}"/>
                                    <div class="game-purchase">
                                        <img src="${list.game.imgURL}" alt="" id="imageSource" />
                                        <span>${list.game.gameName}</span>
                                        <div class="game-price">
                                            <div class="game-price-box">${list.game.price} $</div>
                                            <a class="game-remove" href="CartServlet?action=delete&gID=${list.game.gameID}" onclick="return confirm('Are you sure?')" >Remove</a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:out value="Your cart is empty"/>
                            </c:otherwise>
                        </c:choose>


                    </div>
                    <div class="home-page"><img src="img/gaming.gif" alt=""></div>
                    <div class="rightcol">

                        <div class="botcol">
                            <p><c:out value="Estimated total: ${total} $"/></p>
                            <a href="MainServlet?action=purchase">
                                <span>Purchase</span>
                            </a>
                        </div>
                    </div>

                    <div class="game-purchase-box">

                        <a href="MainServlet">Continue SHOPPING</a>
                    </div>

            </section>
        </div>

        <div class="col-12 bot-footer" style="background-image: url(img/maxresdefault.jpg);">
            <div id="footer">
                <div class="footer-content">
                    <footer>
                        <span>Trieu Thanh Dat-SE150136</span>
                        <span>contact: 0347185920</span>
                        <span><a>trieuthanhdat12345@gmail.com</a></span>
                        <span>Nguyen Manh Kien-SE130280</span>
                        <span>contact: 0347647902</span>
                        <span><a>nguyenmanhkien@gmail.com</a></span>
                    </footer>
                </div>
            </div>
        </div>
    </body>
</html>