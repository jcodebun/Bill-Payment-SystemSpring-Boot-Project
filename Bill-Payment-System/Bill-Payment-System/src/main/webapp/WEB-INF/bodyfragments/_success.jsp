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
				<i class="fa fa-arrow-right" aria-hidden="true"></i> Success
			</li>
		</ol>
	</nav>
</div>
<hr>
<div class="container">
	<h3>Invoice</h3>
	<%@ include file="businessMessage.jsp" %>
	<hr>

	<ul class="list-group list-group-flush">
		<li class="list-group-item">InvoiceNo : ${sessionScope.invoice.invoiceNo}</li>
		<li class="list-group-item">Name : ${sessionScope.invoice.user.firstName} ${sessionScope.invoice.user.lastName}</li>
		<li class="list-group-item">Vendor : ${sessionScope.invoice.vendorType}</li>
		<li class="list-group-item">Amount : ${sessionScope.invoice.paidAmount}</li>
	</ul>

</div>

<br>