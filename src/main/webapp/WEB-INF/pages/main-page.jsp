<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title>TSI chat</title>
	<link rel="stylesheet" href="/assets/css/style.css" type="text/css" media="all" />
	<link href='http://fonts.googleapis.com/css?family=Coda' rel='stylesheet' type='text/css' />
	<link href='http://fonts.googleapis.com/css?family=Jura:400,500,600,300' rel='stylesheet' type='text/css' />
	<script src="/assets/js/jquery-1.8.0.min.js" type="text/javascript"></script>
	<script src="/assets/js/functions.js" type="text/javascript"></script>
	<script src="/assets/js/jquery.simplemodal.1.4.4.min.js" type="text/javascript"></script>
	<script src="/assets/js/jqtransform/jquery.jqtransform.js" type="text/javascript"></script>
	<link href='/assets/js/jqtransform/jqtransform.css' rel='stylesheet' type='text/css' />
</head>
<body>
	<!-- wrapper -->
	<div class="wrapper">
		<!-- header -->
		<header class="header">
			<div class="shell">
				<div class="header-top">
					<h1 id="logo"><a href="#">TSI chat</a></h1>
					<nav id="navigation">
						<a href="#" class="nav-btn">Home<span> </span></a>
						<ul>
							<li class="active"><a href="#">Home</a></li>
							<li><a onclick="createAccount();">Create Account</a></li>
							<li><a onclick="authorization();">Sign In</a></li>
						</ul>
					</nav>
					<div class="cl">&nbsp;</div>
				</div>
				<div class="slider">
					<div id="bg"> </div>
					<div id="carousel">
						<div>
							<h5>Новый сервис для студентов</h5>
							<h3>TSI, Рига</h3>
							<p>Создавай чат-комнаты, общайся со своими друзьями и знакомыми в онлайне без границ. 
								Будь в курсе последних событий института, всегда оставайся на связи. TSI chat 
								открывает перед тобой новые возможности.</p>
							<a onclick="createAccount();" class="green-btn">Создать аккаунт</a>
							<img class="img-front" src="/assets/css/images/slide1.png"/>
						</div>
					</div>
				</div>
			</div>
		</header>
		<!-- end of header -->
		<!-- shell -->
		<div class="shell">
			<!-- main -->
			<div class="main">
				<!-- cols -->
				<section class="cols">

					<div class="col">
						<img src="/assets/css/images/col-img1.png" alt="" />
						<div class="col-cnt">
							<h2>SIGN UP</h2>
							<p>Создай свой аккаунт совершенно бесплатно</p>
							<a onclick="createAccount();" class="more">подробнее..</a>
						</div>
					</div>
					<div class="col">
						<img src="/assets/css/images/col-img2.png" alt="" />
						<div class="col-cnt">
							<h2>COMMUNICATE</h2>
							<p>Общайся с друзьями и преподавателями</p>
							<a onclick="authorization();" class="more">подробнее..</a>
						</div>
					</div>
					<div class="col">
						<img src="/assets/css/images/col-img3.png" alt="" />
						<div class="col-cnt">
							<h2>PROMOTE</h2>
							<p>Посоветуй наш проект своим друзьям и знакомым</p>
							<a href="#" class="more">подробнее..</a>
						</div>
					</div>
					<div class="cl">&nbsp;</div>

				</section>
				<!-- end of cols -->
				<section class="post">
					<img src="/assets/css/images/notebook.png" alt="" />
					<div class="post-cnt">
						<h2>Choose TSI chat for your daily communication</h2>
						<p>
							<strong>TSI chat</strong> предлагает вам неограниченные возможности общения с вашими однокурсниками из института Транспорта и Связи (Рига, Латвия).
						</p>
						<p>
							Теперь вы всегда можете оставаться в центре событий института Транспорта и Связи (Рига, Латвия), а так же имеете 
							возможность связаться с сотнями студентов ВУЗа за считанные секунды! Более того, сервис TSI chat надежен, стабилен и абсолютно 
							бесплатен. Так будет всегда!
						</p>
						<p>
							Впервые тут? Ничего не знаете об <strong>институте Транспорта и Связи</strong>? Узнайте подробнее:
						</p>
						<ul>
							<li><a href="http://tsi.lv/">Домашняя страница института Транспорта и Связи</a></li>
							<li><a href="https://www.facebook.com/TSIpage?fref=ts">Группа TSI в социальной сети Facebook</a></li>
						</ul>
					</div>
					<div class="cl">&nbsp;</div>
				</section>

				<section class="content">
					<h2>FEW WORDS ABOUT TSI CHAT</h2>
					<p>
						Форумы, электронные письма, уведомления и прочие offline средства коммуникации уже давно уступили свое место 
						сервисам online общения. Сегодня модно общаться с людьми в реальном времени. Это сокращает время, затраченое на 
						донесение информации до собеседника и создает иллюзию реального общения в втртуальной среде.
					</p>
					<br>
					<p>
						<strong>TSI chat</strong> явялется сервисом "живого" общения. Он сфокусирован на задаче предоставления удобного 
						инструмента коммуникации между людьми в рамках одного ВУЗа. Наш пользовательский интерфейс интуитивно понятен и удобен.
						Наш back-end разработан на Java с использовании мощного и гибкого фреймворка Spring, что позволило нам реализовать 
						проект качественно и надежно.
					</p>
				</section>

			</div>
			<!-- end of main -->
		</div>
		<!-- end of shell -->	
		<!-- footer -->
		<div id="footer">
			<!-- shell -->
			<div class="shell">
				<!-- footer-cols -->
				<div class="footer-cols">
					<div class="col">
						<h2>About TSI</h2>
						<ul>
							<li><a href="http://tsi.lv/">Домашняя страница TSI</a></li>
							<li><a href="https://www.facebook.com/TSIpage?fref=ts">Группа TSI в Facebook</a></li>
						</ul>
					</div>

					<div class="col">
						<h2>Spring Framework</h2>
						<ul>
							<li><a href="http://spring.io/">Домашняя страница Spring</a></li>
							<li><a href="http://spring.io/guides">Spring туториалы </a></li>
							<li><a href="http://spring.io/blog">Блог от Spring</a></li>
						</ul>
					</div>

					<div class="col">
						<h2>Jelastic</h2>
						<ul>
							<li><a href="http://jelastic.com/">Домашняя страница Jelastic</a></li>
							<li><a href="http://docs.jelastic.com/">Документация</a></li>
							<li><a href="http://jelastic.com/category/press-release/">Блог Jelastic</a></li>
						</ul>
					</div>

					<div class="col">
						<h2>CONTACT us</h2>

						<p>Email: <a href="#">vitaly.rogozha@gmail.com</a></p>
						<p>Phone: +123-45-678-90</p>
						<p>Address: Неизвестная улица,</p>
						<p>Рига, Латвия</p>
					</div>
					<div class="cl">&nbsp;</div>
				</div>
				<!-- end of footer-cols -->
				<div class="footer-bottom">
					<div class="footer-nav">
						<ul>
							<li><a href="#">Home</a></li>
							<li><a onclick="createAccount();">Create Account</a></li>
							<li><a onclick="authorization();">Sign in</a></li>
						</ul>
					</div>
					<p class="copy">Copyright &copy; 2013<span>|</span>Created by: <a href="https://www.facebook.com/Vitro20?fref=ts" target="_blank">Виталий Рогожа</a></p>
					<div class="cl">&nbsp;</div>
				</div>
			</div>
			<!-- end of shell -->
		</div>
		<!-- footer -->
	</div>
	<!-- end of wrapper -->

<div id="registrationDiv">
	<div id="registrationForm">
		<form>
			<div class="line">
				<div class="label">Придумайте логин:</div>
				<div><input type="text" id="newUserLogin"></div>
                                <div style="clear: both;"></div>
			</div>
                        <ul class="errorMsg" id="loginErrors">
                            
                        </ul>
			<div class="line">
				<div class="label">Подберите надежный пароль:</div>
				<div><input type="password" id="newUserPassword"></div>
                                <div style="clear: both;"></div>
			</div>
                        <ul class="errorMsg" id="passwordErrors">
                            
                        </ul>
			<div class="line">
				<div class="label">Пароль еще раз:</div>
				<div><input type="password" id="newUserPasswordRepeat"></div>
                                <div style="clear: both;"></div>
			</div>
                        <ul class="errorMsg" id="passwordRepeatErrors">
                            
                        </ul>
			<div class="line" style="margin-top: 25px">
				<div class="label" style="width: 50%; margin: 0;"><div class="btn-green" id="register">Создать</div></div>
				<div><div class="btn-green cancelModal">Отмена</div></div>
                                <div style="clear: both;"></div>
			</div>
		</form>
	</div>
</div>

<div id="successfullRegistration">
    <div class="label">Новый аккаунт создан!</div>
    <br>
    <div class="btn-green cancelModal">Закрыть</div>
</div>
        
<div id="authorizationDiv">
    <div id="authorizationForm">
         <form:form method="POST" action="/" modelAttribute="authForm">
            <div class="line">
                    <div class="label">Ваш логин:</div>
                    <div><form:input path="userLogin" /></div>
                    <div style="clear: both;"></div>
            </div>
            <ul class="errorMsg">
            <c:if test="${not empty loginErrors}">
                 <c:forEach var="error" items="${loginErrors}">
                     <li>${error}</li>
                 </c:forEach>
             </c:if>
            </ul>
            <div class="line">
                    <div class="label">Ваш пароль:</div>
                    <div><form:password path="userPassword" /></div>
                    <div style="clear: both;"></div>
            </div>
             <ul class="errorMsg">
             <c:if test="${not empty passwordErrors}">
                 <c:forEach var="error" items="${passwordErrors}">
                     <li>${error}</li>
                 </c:forEach>
             </c:if>
            </ul>
            <div class="line" style="margin-top: 25px">
                <div class="label" style="width: 50%; margin: 0;"><div class="btn-green" onclick="submitAuthForm();">Войти</div></div>
                <div><div class="btn-green cancelModal">Отмена</div></div>
                <div style="clear: both;"></div>
            </div>

        </form:form>
    </div>
</div>
        
<c:if test="${displayAuthForm}">
    <script type="text/javascript">
        authorization();
    </script>
</c:if>
        
</body>
</html>