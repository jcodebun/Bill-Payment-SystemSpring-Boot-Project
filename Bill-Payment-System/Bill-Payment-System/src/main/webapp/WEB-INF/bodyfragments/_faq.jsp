<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<br>
<div class="container">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item linkSize"><i
				class="fas fa-tachometer-alt"></i> <a class="link-dark"
				href="<c:url value="/welcome"/>">Home</a></li>
			<li class="breadcrumb-item linkSize active" aria-current="page">
				<i class="fa fa-arrow-right" aria-hidden="true"></i> FAQ'S
			</li>
		</ol>
	</nav>
</div>
<hr>
<div class="container">
	<h3>FAQ'S</h3>
	<hr>

	<ul class="list-group list-group-flush">
		<li class="list-group-item">An item</li>
		<li class="list-group-item">A second item</li>
		<li class="list-group-item">A third item</li>
		<li class="list-group-item">A fourth item</li>
		<li class="list-group-item">And a fifth one</li>
	</ul>

</div>

<br>