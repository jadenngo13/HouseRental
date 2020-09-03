<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- basic -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- mobile metas -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<!-- site metas -->
<title>Renty</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">
<!-- bootstrap css -->
<link rel="stylesheet" href="maincss/bootstrap.min.css">
<!-- style css -->
<link rel="stylesheet" href="maincss/style.css">
<!-- Responsive-->
<link rel="stylesheet" href="maincss/responsive.css">
<!-- fevicon -->
<link rel="icon" href="img/fevicon.png" type="image/gif" />
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet" href="maincss/jquery.mCustomScrollbar.min.css">
<!-- Tweaks for older IEs-->
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
	media="screen">

</head>
<!-- body -->
<body class="main-layout">
 
 	<!-- check to ensure some user is logged in -->
	<%-- <%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	
		if(session.getAttribute("username") == null) {
			response.sendRedirect("login.jsp");
		}
	%> --%>
	<!-- loader  -->
	<div class="loader_bg">
		<div class="loader">
			<img src="img/loading.gif" alt="#" />
		</div>
	</div>
	<!-- end loader -->
	<!-- header -->
	<header>
		<!-- header inner -->
		<div class="header">
			<div class="container">
				<div class="row">
					<div class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col logo_section">
						<div class="full">
							<div class="center-desk">
								<div class="logo">
									<a href="customerMain.jsp"><img src="img/logo.png" alt="#" /></a>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-9 col-lg-9 col-md-9 col-sm-9">
						<nav class="navigation navbar navbar-expand-md navbar-dark ">
							<button class="navbar-toggler" type="button"
								data-toggle="collapse" data-target="#navbarsExample04"
								aria-controls="navbarsExample04" aria-expanded="false"
								aria-label="Toggle navigation">
								<span class="navbar-toggler-icon"></span>
							</button>
							<div class="collapse navbar-collapse" id="navbarsExample04">
								<form action="logout" method="get">
									<div class="sign_btn">
									<a href="viewProfile">Your Profile</a>
									<a href="viewRentedHouses">Your Rented Houses</a>
									<a href="logout">Sign out</a>
									</div>
								</form>
							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- end header inner -->
	<!-- end header -->
	<!-- banner -->
	<section class="banner_main">
		<div class="container">
			<div class="row d_flex">
				<div class="col-md-12">
					<div class="text-bg">
						<h1>Hello ${name}.</h1>
						<strong>Find the best houses Here for rent</strong> <span>Your
							Adventure Starts With Us</span>
						<p>
							Lorem Ipsum is simply dummy text of the printing and typesetting
							industry. Lorem Ipsum has been the industry's standard dummy text
							ever since theLorem Ipsum is simply dummy text of the printing
						</p>
						<form>
							<a href="#">Read More</a>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- end banner -->
	<!-- car -->
	<div class="car">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="titlepage">
						<h2>VARIETY OF HOUSES</h2>
						<span>Lorem ipsum dolor sit amet, consectetur adipiscing
							elit, sed do eiusmod tempor incididunt ut labore et dolore magna</span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 padding_leri">
					<div class="car_box">
						<figure>
							<img src="img/lake-house.png" alt="#" />
						</figure>
						<h3>Lake House</h3>
					</div>
				</div>
				<div class="col-md-4 padding_leri">
					<div class="car_box">
						<figure>
							<img src="img/beach-house.png" alt="#" />
						</figure>
						<h3>Oceanside</h3>
					</div>
				</div>
				<div class="col-md-4 padding_leri">
					<div class="car_box">
						<figure>
							<img src="img/country-house.png" alt="#" />
						</figure>
						<h3>Country House</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end car -->
	<!-- bestCar -->
	<div id="contact" class="bestCar">
		<div class="container">
			<div class="row">
				<div class="col-md-12"></div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="row">
						<div class="col-md-6 offset-md-6">
							<form class="main_form">
								<div class="titlepage">
									<h2>Find The Best House For Rent</h2>
								</div>
								<div class="row">
									<div class="col-md-12 ">
										<select>
											<option value="0">Choose House Location</option>
											<option value="California">California</option>
											<option value="Washington">Washington</option>
											<option value="Oregon">Oregon</option>
										</select>
									</div>
									<div class="col-md-12">
										<select>
											<option value="0">Price of Rent</option>
											<option value="50-100">$50-100</option>
											<option value="100-200">$100-200</option>
											<option value="200-300">$200-300</option>
											<option value="300+">$300+</option>
										</select>
									</div>
									<div class="col-sm-12">

										<button class="find_btn">Find Now</button>

									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end bestCar -->
	<!-- cutomer -->
	<div class="cutomer">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="titlepage">
						<h2>What is says our cutomer</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div id="myCarousel" class="carousel slide cutomer_Carousel "
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
							<li data-target="#myCarousel" data-slide-to="1"></li>
							<li data-target="#myCarousel" data-slide-to="2"></li>
						</ol>
						<div class="carousel-inner">
							<div class="carousel-item active">
								<div class="container">
									<div class="carousel-caption ">
										<div class="cross_img">
											<figure>
												<img src="img/cross_img.png" alt="#" />
											</figure>
										</div>
										<div class="our cross_layout">
											<div class="test_box">
												<h4>Due markes</h4>
												<span>Rental</span>
												<p>Lorem ipsum dolor sit amet, consectetur adipiscing
													elit, sed do eiusmod tempor incididunt ut labore et dolore
													magna aliqua. Ut enim ad minim veniam, quis nostrud
													exercitation ullamco laboris nisi ut aliquip ex ea commodo
													consequat. Duis aute irure dolor in reprehenderit in
													voluptate velit ess</p>
												<i><img src="img/te1.png" alt="#" /></i>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<div class="container">
									<div class="carousel-caption">
										<div class="cross_img">
											<figure>
												<img src="img/cross_img.png" alt="#" />
											</figure>
										</div>
										<div class="our cross_layout">
											<div class="test_box">
												<h4>Due markes</h4>
												<span>Rental</span>
												<p>Lorem ipsum dolor sit amet, consectetur adipiscing
													elit, sed do eiusmod tempor incididunt ut labore et dolore
													magna aliqua. Ut enim ad minim veniam, quis nostrud
													exercitation ullamco laboris nisi ut aliquip ex ea commodo
													consequat. Duis aute irure dolor in reprehenderit in
													voluptate velit ess</p>
												<i><img src="img/te1.png" alt="#" /></i>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<div class="container">
									<div class="carousel-caption">
										<div class="cross_img">
											<figure>
												<img src="img/cross_img.png" alt="#" />
											</figure>
										</div>
										<div class="our cross_layout">
											<div class="test_box">
												<h4>Due markes</h4>
												<span>Rental</span>
												<p>Lorem ipsum dolor sit amet, consectetur adipiscing
													elit, sed do eiusmod tempor incididunt ut labore et dolore
													magna aliqua. Ut enim ad minim veniam, quis nostrud
													exercitation ullamco laboris nisi ut aliquip ex ea commodo
													consequat. Duis aute irure dolor in reprehenderit in
													voluptate velit ess</p>
												<i><img src="img/te1.png" alt="#" /></i>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<a class="carousel-control-prev" href="#myCarousel" role="button"
							data-slide="prev"> <span class="carousel-control-prev-icon"
							aria-hidden="true"></span> <span class="sr-only">Previous</span>
						</a> <a class="carousel-control-next" href="#myCarousel" role="button"
							data-slide="next"> <span class="carousel-control-next-icon"
							aria-hidden="true"></span> <span class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end cutomer -->
	<!--  footer -->
	<footer>
		<div class="footer">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="cont_call">
							<h3>
								<strong class="multi color_chang"> Call Now</strong><br>
								1-800-MYRENTY
							</h3>
						</div>
					</div>
				</div>
			</div>
			<div class="copyright">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<p>
								© 2019 All Rights Reserved. Design by <a
									href="https://html.design/"> Free Html Templates</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- end footer -->
	<!-- Javascript files-->
	<script src="js/jquery.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="js/jquery-3.0.0.min.js"></script>
	<script src="js/plugin.js"></script>
	<!-- sidebar -->
	<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="js/custom.js"></script>
	<script
		src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
</body>
</html>