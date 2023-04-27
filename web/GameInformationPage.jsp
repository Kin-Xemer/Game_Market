<%-- 
    Document   : GameInformationPage
    Created on : 19-Mar-2021, 11:16:08
    Author     : SE130280
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" />
        <link rel="stylesheet" href="assets/GameInformationPageAssets/style.css" />
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
                        <div id="account"> <div class="dropdown">
                                <a href="" >Welcome <c:out value="${sessionScope.username}"></c:out>
                                        <div class="dropdown-content">
                                            <a href="">Bought Game</a>
                                            <a href="MainServlet?action=listmygame">Your Library</a>
                                            <a href="CartServlet?action=ok">View your Cart</a>
                                            <a href="MainServlet?action=logout">Logout</a>
                                        </div>
                                    </a>
                                </div> 
                            </div>
                        </div>
                </section>
            <c:set var="game" value="${game}"/>
            <c:set var="gameByID" value="${requestScope.gameByID}"/>
            <section class="body-info">
                <div class="game-box col-12">
                    <div class="game-titel-box">
                        <div class="game-title-guide">
                            <a href="MainServlet">All Game</a>>><a href="MainServlet?action=filter&type=${requestScope.gameType}">${requestScope.gameType}</a>>><a>${gameByID.gameName}</a>
                        </div>
                        <div class="game-main-title">
                            <h2 id="gameTitle">${gameByID.gameName} </h2>
                            <a href="MainServlet"><span>RETURN</span></a>
                        </div>
                    </div>
                    <div class="leftcol">
                        <img src="${gameByID.imgURL}" alt="" id="imageSource" />
                    </div>
                    <div class="rightcol">
                        <div id="gameShortDes">

                            ${gameByID.description}
                        </div>
                        <div id="releaseDay">
                            <span>ReleaseDate: </span>${gameByID.releaseDay}
                        </div>
                        <div id="ratingStar"><span>Reviews received: </span>${gameByID.rating}/5 stars</div>
                        <div id="poster"><span>Poster: </span>${gameByID.username}</div>
                        <div id="publisher"><span>Publisher: </span>${gameByID.pubID}</div>
                        <div id="developer"><span>Developer: </span>${gameByID.devID}</div>
                    </div>
                    <div class="botcol">
                        <a href="CartServlet?action=ok">
                            <span>View Your Cart</span>
                        </a>
                    </div>
                </div>
                    <label style="color: #d9534f">${requestScope.msg}</label>
                <div class="game-purchase-box">
                    
                    <span>Buy ${gameByID.gameName} with <span style="color: #4caf50">${gameByID.price}$</span></span>
                    <a href="MainServlet?action=addtocart&gID=${gameByID.gameID}&act=infor">ADD TO CART</a>
                </div>
                <div class="system-requirement-box">
                    <ul><strong>Minimum:</strong></br>
                        <li>Requires a 64-bit processor and operating system</br></li>
                        <li><strong>OS:</strong> Window 10</br></li>
                        <li><strong>Processor:</strong> Intel Q9450 @ 2.6GHz or AMD Phenom II X6 @ 3.3 GHz</br></li>
                        <li><strong>Memory:</strong> 4 GB RAM</br></li>
                        <li><strong>Graphic:</strong>  Nvidia GeForce GTX 650 or AMD Radeon 7750</br></li>
                        <li><strong>DirectX:</strong>  Version 11</br></li>
                        <li><strong>Storage:</strong>   50 GB available space</br></li>
                    </ul>
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
