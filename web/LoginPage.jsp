<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <link rel="stylesheet" href="assets/LoginPageAssets/style.css">
         <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
        <title>Employee Management</title>
    </head>

    <body style="background-image: url(img/bg-body.png);">


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
                    
                </div>
            </section>
            <section class="body-info">
                <div class="col-12">
                    <h2 id="title-banner">Sign me in</h2>      
                    </br>
                    
                </div>
                <div class="col-12" id="login-form-container">
                    <div class="col-2"></div>
                    <div class="col-5" id="login-form">
                        
                        <form action="MainServlet?action=login" name="login" method="POST" >

                            <div class="form-action">
                                <label for="username">Username</label></br>
                                <input type="text" name="username"  required></br>
                            </div>
                            <div class="form-action">
                                <label for="password">Password</label></br>
                                <input type="password" name="password" id="passField" required>
                                <input type="checkbox" name="agree_check" id="togglePassword" style="display: inline; width: 18px; height: 18px;margin: 15px 0px;">Show Password</br>
                            </div>

                            <div class="ssa-box">

                                <input type="checkbox" name="agree_check" value="agree_check" id="agree-check">
                                <label for="agree_check">Remember me</label></br>
                                <input type="submit" id="btnSubmit" value="Sign in">
                            </div>

                        </form>
                    </div>
                    <div class="col-5">
                        <div class="text-field">Join us</div>
                        <a href="">Learn more about us</a>
                        <a href="MainServlet" alt="ViewPage"> <img src="img/gaming.gif" alt="Join us" class="joinImage"></a>
                        <div class="btn-register">
                            <div class="subText">Feel free to join</div>
                            <a href="RegisterPage.jsp">Register</a>
                        </div>
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
        const togglePassword = document.querySelector('#togglePassword');
        const password = document.querySelector('#passField');
        togglePassword.addEventListener('click', function (e) {
            // toggle the type attribute
            const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
            password.setAttribute('type', type);
            // toggle the eye slash icon
            this.classList.toggle('fa-eye-slash');
        });
    </script>
</html>