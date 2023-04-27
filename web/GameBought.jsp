<%-- 
    Document   : GameBought
    Created on : 19-Mar-2021, 21:59:01
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
    <link rel="stylesheet" href="assets/BoughtGameAssets/style.css" />
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
  <c:set var="bought" value="${requestScope.listbought}"/>
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
            <div id="account"><a href="">Your Account</a></div>
          </div>
        </div>
      </section>
      <section class="body-info">
        <div class="game-box col-12">
          <div class="game-titel-box">
            <div class="game-title-guide">
              <a href="MainServlet">All Game</a>>><a href="">Your Bought Game</a>
            </div>
            <div class="game-main-title">
              <h2 id="gameTitle">YOUR Bought Games</h2>
            
            </div>
          </div>
          <div class="leftcol">
            <table id="customers">
              <tr>
                <th>Game Bought</th>
                <th>Poster</th>
                <th>Price</th>
              </tr>
              
          
              <tr>
                
                <td><a href="">  <img src="img/RotT.capsule.184x69.png" alt="" id="imageSource" />Alfreds Futterkiste</a></td>
                <td><a href="">Maria Anders</a></td>
                <td><a href="">Germany</a></td>          
              
              </tr>
          
              <tr>
                
                  <td><a href="">  <img src="img/RotT.capsule.184x69.png" alt="" id="imageSource" /></a></td>
                <td><a href="">Maria Anders</a></td>
                <td><a href="">Germany</a></td>          
              
              </tr>
          
              <tr>
                
                <td><a href="">  <img src="img/RotT.capsule.184x69.png" alt="" id="imageSource" />Alfreds Futterkiste</a></td>
                <td><a href="">Maria Anders</a></td>
                <td><a href="">Germany</a></td>          
              
              </tr>
          
            </table>
          </div>
       
          <div class="rightcol">
           
          <div class="botcol">
           
            <a href="">
              <span>Get All DownLoadLink</span>
            </a>
          </div>
        </div>
        
        <div class="game-purchase-box">
        
          <a href="">Continue SHOPPING</a>
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
