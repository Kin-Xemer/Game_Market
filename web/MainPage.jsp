<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="dtos.Category"%>
<%@page import="daos.GameDAO"%>
<%@page import="dtos.Game"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" />
        <link rel="stylesheet" href="assets/MainPageAssets/style.css">
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

    <body style="background-image: url(img/bg-body.png);">

        <div id="backdrop"></div>
        <div class="modal" id="choices__modal">
            <h2 class="modal__title">My Game</h2>
            <form class="choices__box">
                <input type="button" id="updateBtn" value="UPDATE">
                <p>OR</p>
                <input type="button" value="DELETE" id="deleteBtn">
            </form>
        </div>
        <c:set var="listcate" value="${requestScope.listcate}"/>
        <div class="modal card" id="add-modal">

            <form action="MainServlet?action=addgame" method="POST" >
                <div class="modal__content">            
                    <label for="title">Game Title</label>
                    <input type="text" name="title" id="title" required/>

                    <label for="image-url">Image URL</label>
                    <input type="file" name="imageurl" value="" required style="border:none"/>

                    <label for="description">Description</label>
                    <input type="text" name="description" value=""/>
                    
                    <label for="rating">Your Rating</label>
                    <input type="number" step="1" max="5" min="1" name="rating" id="rating" required/>

                    <label for="releaseDate">Release Date</label>
                    <input type="date" name="releaseDate" id="releaseDate" required/>

                    <label for="publisherId">Publisher</label>
                    <input type="text" name="pub" id="publisherId" required/>

                    <label for="developerId">Developer</label>
                    <input type="text" name="dev" id="developerId" required/>

                    <label for="price">Price</label>
                    <input type="number" step="0.01" min="0" name="price" value="" required/>
                    
                    <label for="link">Link Download</label>
                    <input type="text" name="link" value="" required/>
                    
                    <label for="gameType">Type</label>
                    <select id="gameType" name="type">
                        <c:forEach items="${requestScope.listcate}" var="list">
                            <option class="type">${list.cateType}</option>

                        </c:forEach>
                    </select>
                </div>
                <div class="modal__actions">
                    <button class="btn btn--passive">Cancel</button>
                    <button class="btn btn--success">Add</button>
                </div>

            </form>
        </div>
        <div class="modal card" id="update-modal">
            <h2 class="modal__title">Update</h2>
            <div class="modal__content">

                <label for="gameId" readonly>Game ID</label>
                <input type="text" name="gameId" id="gameId" required/>

                <label for="title">Game Title</label>
                <input type="text" name="title" id="title" required/>

                <label for="image-url">Image URL</label>
                <input type="file" name="image-url" id="image_url" required style="border:none"/>

                <label for="rating">Your Rating</label>
                <input type="number" step="1" max="5" min="1" name="rating" id="rating" required/>

                <label for="releaseDate">Release Date</label>
                <input type="date" name="releaseDate" id="releaseDate" required/>

                <label for="publisherId">Publisher ID</label>
                <input type="text" name="publisherId" id="publisherId" required/>

                <label for="developerId">Developer ID</label>
                <input type="text" name="developerId" id="developerId" required/>

                <label for="price">Price</label>
                <input type="number" name="price" id="price" required/>

                <label for="gameType">Type</label>
                <select id="gameType" name="type">
                    <option class="type">Action</option>
                    <option class="type">Adventure</option>
                    <option class="type">Sport</option>
                    <option class="type">Moba</option>
                    <option class="type">FPS</option>
                </select>
            </div>
            <div class="modal__actions">
                <button class="btn btn--passive">Cancel</button>
                <button class="btn btn--success">Update</button>
            </div>
        </div>
        <div class="modal card" id="delete-modal">
            <h2 class="modal__title">Are you sure?</h2>
            <p class="modal__content">
                Are you sure you want to delete this item? This action can't be made undone!
            </p>
            <div class="modal__actions">
                <button class="btn btn--passive">No (Cancel)</button>
                <button class="btn btn--danger">Yes</button>
            </div>
        </div>

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
                    <c:choose>
                        <c:when test="${not empty sessionScope.username}">

                            <c:set var="username" value="${sessionScope.username}"/>
                            <c:if test="${username != null}">
                                <div class="col-6 ">
                                    <div id="account" >
                                        <div class="dropdown">
                                            <a href="MainServlet?type=all" >Welcome,   <c:out value="${username}"></c:out>
                                                    <div class="dropdown-content">
                                                        <a href="MainServlet?action=Bought">Bought Game</a>
                                                        <a href="MainServlet?action=listmygame">Your Library</a>
                                                        <a href="CartServlet">View your Cart</a>
                                                        <a href="MainServlet?action=logout">Logout</a>
                                                    </div>
                                                </a>
                                            </div>                                            
                                        </div>
                                </c:if>
                                <c:if test="${username == null}">
                                    <div id="account"><a href="MainServlet?action=login">Login</a></div>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <div id="account"><a href="MainServlet?action=login">Login</a></div>
                            </c:otherwise>
                        </c:choose>
                    </div>
            </section>
            <section class="body-info">
                <div class="column-info left">
                    <div class="col-3">
                        <span class="left-info">GAME MARKET</span>

                    </div>
                </div>
                <div class="column-info right" >
                    <div id="entry-text" class="col-12 card">
                        <a href="MainServlet"><p>WELCOME GAMERS</p></a>                   
                        <div class="addBtn boxContainer">
                            <c:if test="${not empty sessionScope.username}">
                                <button class="Add-game">ADD your GAME</button>
                            </c:if>
                        </div>
                        
                        <label style="color: #5cb85c">${requestScope.addmsgsuccess}</label>
                        <label style="color: #d9534f">${requestScope.addmsg}</label>
                         
                        <div class="search-box">
                            <button type="submit" id="searchBtn">
                                <i class="fa fa-search"></i></button
                            ><input
                                type="text"
                                id="searchPlace"
                                class="search"
                                placeholder="Search-here"
                                />
                        </div>
                    </div>
                    <div class="categories boxContainer">
                        <form action="MainServlet?action=filter" name="filter" class="gameTypeCate">

                            <c:set var="game" value="${game}"/>

                            <c:forEach items="${listcate}" var="i" >
                                <button type="submit" class="categories" name="type" value="<c:out value="${i.cateType}"/>">${i.cateType}</button>

                            </c:forEach>
                            <button type="submit" class="categories" name="type" value="all">SHOW ALL</button>
                        </form>
                    </div>
                    <div class="game-container">
                        <c:set var="typeBtn" value="${param.type}"/>

                        <c:if test="${empty param.page}">
                            <c:set var="currentPage" value="1"/>
                            <%System.out.println("1");%>
                        </c:if >
                        <c:if test="${not empty param.page}">
                            <c:set var="currentPage" value="${param.page}"/>     
                            <%System.out.println("! 1");%>
                        </c:if>

                        <c:choose>
                            <c:when test="${empty requestScope.listmygamecheck}">
                                <c:choose>
                                    <c:when test="${empty typeBtn or typeBtn eq 'all'}">
                                        <c:set var="page" value="${game.totalPage(username,8,false)}"/>
                                        <c:set var="listgames" value="${game.getGameOnPage(8,currentPage,username,false)}" />
                                        <%System.out.println("not Empty type and Show all");%>
                                        <c:remove var="typeBtn"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="page" value="${game.totalPageByType(username,typeBtn,8,false)}"/>
                                        <c:set var="listgames" value="${game.getGameByTypeOnPage(typeBtn,8, username, currentPage,false)}" />
                                        <%System.out.println("hellothere");%>
                                        <c:remove var="typeBtn"/>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${empty typeBtn or typeBtn eq 'all'}">
                                        <c:set var="page" value="${game.totalPage(username,8,true)}"/>
                                        <c:set var="listgames" value="${game.getGameOnPage(8,currentPage,username,true)}" />
                                        <%System.out.println("not Empty type and Show all with user");%>
                                        <c:remove var="typeBtn"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="page" value="${game.totalPageByType(username,typeBtn,8,true)}"/>
                                        <c:set var="listgames" value="${game.getGameByTypeOnPage(typeBtn,8, username, currentPage,true)}" />
                                        <%System.out.println("hellothere with user");%>
                                        <c:remove var="typeBtn"/>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach items="${listgames}" var="list">
                            <div class="game-list">
                                <div class="game-list-info">
                                    <a href="MainServlet?action=showInfo&gID=${list.gameID}" class="tab_item app_tracked">
                                        <div class="tab_item_cap">
                                            <img src="<c:out value="${list.imgURL}"/>" alt="" class="tab_item_cap_img" style="width:184px; height: 69px">
                                        </div>
                                        <div class="discount_block tab_item_discount no_discount">
                                            <div class="discount_prices">
                                                <div class="discount_final">${list.price} $</div>
                                            </div>
                                        </div>


                                        <div class="tab_item_content">
                                            <div class="tab_item_name">${list.gameName}(${list.username})</div>
                                            <div class="tab_item_details">
                                                <c:forEach begin="1" end="${list.rating}">
                                                    <span class="fa fa-star checked"></span>
                                                </c:forEach>
                                                <c:forEach begin="1" end="${5-list.rating}">
                                                    <span class="fa fa-star"></span>
                                                </c:forEach>
                                                <div class="tag_item_top_tags">
                                                    <span class="top_tag">${game.getTypeByID(list.categoryID)}</span>
                                                </div>
                                            </div>
                                        </div>



                                        <div style="clear: both;"></div>
                                    </a>
                                </div>
                                <div class="game-list-event">
                                    <c:choose>
                                        <c:when test="${empty username}">
                                            <a href="LoginPage.jsp"><button class="addToCart" name="addToCart">ADD TO CART</button></a>
                                        </c:when>
                                        <c:when test="${not empty username and username eq list.username }">
                                            <button class="edit-icon" style="background:none; border:none; cursor:pointer" id="editIcon"><i class="fa fa-edit"  style='font-size:24px'></i></button>
                                            <a href="MainServlet?gID=${list.gameID}"><button style="background: none; border:none; cursor:pointer">Update</button></a>
                                            <a href="MainServlet?action=delete&gID=${list.gameID}"><button style="background: none; border:none; cursor:pointer">Delete</button></a>
                                        </c:when> 
                                            <c:otherwise>
                                            <a href="MainServlet?action=addtocart&gID=${list.gameID}"><button class="addToCart" name="addToCart">ADD TO CART</button></a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                    <div class="pagination col-6">
                        <c:set value="1" var="first"></c:set>
                        <c:choose>
                            <c:when test="${currentPage eq first}">
                                <a>&laquo;</a>
                            </c:when>
                            <c:otherwise>
                                <a href="MainServlet?page=<c:out value="${currentPage-1}"/>&action=paging">&laquo;</a>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${page}" var="paging">
                            <c:choose>
                                <c:when test="${paging eq currentPage}">
                                    <a href='MainServlet?page=<c:out value="${currentPage}"/>&action=paging' class="active"><c:out value="${currentPage}"/></a>
                                </c:when>
                                <c:otherwise>
                                    <a href='MainServlet?page=<c:out value="${paging}"/>&action=paging'><c:out value="${paging}"/></a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${currentPage eq page}">
                                <a>&raquo;</a>
                            </c:when>
                            <c:otherwise>
                                <a href="MainServlet?page=<c:out value="${currentPage+1}"/>&action=paging">&raquo;</a>
                            </c:otherwise>
                        </c:choose>
                    </div> 
                </div>
            </section>
        </div>

        <div class="col-12" style="background-image: url(img/maxresdefault.jpg);">
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
    <script>
        const addModal = document.getElementById("add-modal");
        const startAddButton = document.querySelector("#entry-text button");
        const backdrop = document.getElementById("backdrop");
        const cancelAddButton = addModal.querySelector(".btn--passive");
        const confirmAddButton = cancelAddButton.nextElementSibling;
        const userInputs = addModal.querySelectorAll("input");
        const entryTextSection = document.getElementById("entry-text");
        const deleteModal = document.getElementById("delete-modal");
        const choicesModal = document.getElementById("choices__modal");
        const updateModal = document.getElementById("update-modal");
        const cancelUpdateButton = updateModal.querySelector(".btn--passive");

        const editIcon = document.querySelectorAll(".game-list-event .edit-icon");

        const updateBtn = document.getElementById("updateBtn");
        const deleteBtn = document.getElementById("deleteBtn");

        const closeAddModal = () => {
            addModal.classList.remove("visible");
        };
        const showAddModal = () => {
            addModal.classList.add("visible");
            toggleBackdrop();
        };
        const showChoicesModal = () => {
            choicesModal.classList.add("visible");
            toggleBackdrop();
            updateBtn.addEventListener("click", showUpdateModal);
        };
        const closeChoicesModal = () => {
            choicesModal.classList.remove("visible");
        };
        const showUpdateModal = () => {
            updateModal.classList.add("visible");
        };
        const closeUpdateModal = () => {
            updateModal.classList.remove("visible");
        };
        const closeDeletionModal = () => {
            toggleBackdrop();
            deleteModal.classList.remove("visible");
        };

        const clearInput = () => {
            for (const usrInput of userInputs) {
                usrInput.value = "";
            }
        };
        const toggleBackdrop = () => {
            backdrop.classList.toggle("visible");
        };
        const backdropClickHandler = () => {
            closeAddModal();
            closeDeletionModal();
            clearInput();
            closeChoicesModal();
            closeUpdateModal();
        };
        const cancelAddHandler = () => {
            closeAddModal();
            toggleBackdrop();
            clearInput();
        };
        const cancelUpdateHandler = () => {
            closeUpdateModal();
            closeChoicesModal();
            toggleBackdrop();
            clearInput();
        };

        startAddButton.addEventListener("click", showAddModal);
        backdrop.addEventListener("click", backdropClickHandler);
        cancelAddButton.addEventListener("click", cancelAddHandler);
        // confirmAddButton.addEventListener('click', addHandler);
        editIcon.forEach((element) => {
            element.addEventListener("click", showChoicesModal);
        });
        cancelUpdateButton.addEventListener("click", cancelUpdateHandler);
    </script>
</html>