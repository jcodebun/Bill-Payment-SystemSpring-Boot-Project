<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav class="navbar navbar-expand-lg bgcolor"
	style="height: 59px;">
	<div class="container-fluid">
		<a class="navbar-brand " href="#"
			style="font-size: 26px; font-family: serif; color: white;"> 
			<span>Bill-Payment-System</span>
		</a>

		<form class="container-fluid">
			
		</form>

		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			<c:if test="${sessionScope.user != null}">
				<span class="navbar-text"
					style="color: white; font-size: 14px; font-variant-caps: petite-caps;">
					Hello,${sessionScope.user.firstName}</span>
			</c:if>
			
			<c:if test="${sessionScope.vendor != null}">
				<span class="navbar-text"
					style="color: white; font-size: 14px; font-variant-caps: petite-caps;">
					Hello,${sessionScope.vendor.name}</span>
			</c:if>
			<c:if test="${sessionScope.user == null && sessionScope.vendor == null}">
				<span class="navbar-text"
					style="color: white; font-size: 14px; font-variant-caps: petite-caps;">
					Hello,SignIn </span>
			</c:if>
			</ul>
			
		</div>
	</div>
</nav>
<div class="shadow bg-body rounded">
	<nav class="navbar navbar-expand-lg "
		style="height: 39px; background-color: #0f1e3afc;">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item linkSize"><a
						class="nav-link active link-light" aria-current="page"
						href="<c:url value="/welcome"/>">Home</a></li>
					<c:if test="${sessionScope.user != null}">
						<c:if test="${sessionScope.user.roleId == 1}">
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/vendor/search"/>">Vendor List</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/user/search"/>">User List</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/help/search"/>">Help List</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/feedBack/admin"/>">Add FeedBacks</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/feedBack/user/search"/>">UserFeedBack</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/feedBack/vendor/search"/>">VendorFeedBack</a></li>
							
						</c:if>

						<c:if test="${sessionScope.user.roleId == 2}">

							<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/bill/search"/>">Bill List</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/help"/>">Help</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/help/search"/>">Help Report</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/FAQ"/>">FAQ's</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/feedBack/user"/>">Add FeedBack</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/feedBack/vendor/search"/>">VendorFeedBack</a></li>
								
									<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/invoice/search"/>">Invoice</a></li>
							
						</c:if>
						
						

					</c:if>
					
					<c:if test="${ sessionScope.vendor != null}">
									<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/bill"/>">Generate Bill</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/bill/search"/>">Bill Report</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/FAQ"/>">FAQ's</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/feedBack/vendor"/>">Add FeedBack</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/feedBack/user/search"/>">UserFeedBack</a></li>
								
								<li class="nav-item linkSize"><a class="nav-link link-light"
								href="<c:url value="/ctl/invoice/search"/>">Invoice</a></li>
								
								
						</c:if>
					<c:if test="${sessionScope.user == null || sessionScope.vendor == null}">
					
						<li class="nav-item linkSize"><a class="nav-link link-light"
							href="<c:url value="/aboutUs"/>">AboutUs</a></li>
						<li class="nav-item linkSize"><a class="nav-link link-light"
							href="<c:url value="/contactUs"/>">ContactUs</a></li>
					</c:if>
				</ul>
			</div>
			
			
			<ul class="nav justify-content-end">
				<c:if test="${sessionScope.user != null }">

					<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/user/profile"/>">My
							Profile</a></li>
					<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/user/changepassword"/>">Change
							Password</a></li>

					<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/user/login"/>">Logout</a></li>
				</c:if>
				
				<c:if test="${ sessionScope.vendor != null}">
					<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/vendor/profile"/>">My
							Profile</a></li>
					<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/vendor/changepassword"/>">Change
							Password</a></li>
				<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/vendor/login"/>">Logout</a></li>
				</c:if>
				<c:if test="${sessionScope.user == null && sessionScope.vendor == null}">
					<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/user/login"/>">User SignIn</a></li>
					<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/user/signUp"/>"> User SignUp</a></li>
						
						<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/vendor/login"/>">Vendor SignIn</a></li>
					<li class="nav-item linkSize"><a class="nav-link link-light"
						style="padding: 6px;" href="<c:url value="/vendor/signUp"/>">Vendor SignUp</a></li>
				</c:if>

			</ul>
		</div>
	</nav>
</div>
