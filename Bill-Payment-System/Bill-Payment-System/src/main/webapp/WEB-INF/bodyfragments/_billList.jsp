<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="crt"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page isELIgnored="false"%>

<c:url var="addUrl" value="/ctl/bill" />

<c:url var="addSearch" value="/ctl/bill" />

<c:url var="editUrl" value="/ctl/bill?id=" />


<c:url var="invo" value="/ctl/invoice?iId=" />

<br>
<div class="container"> 
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item linkSize"><i class="fas fa-tachometer-alt"></i> <a class="link-dark" href="<c:url value="/home"/>">Home</a></li>
    <li class="breadcrumb-item linkSize active" aria-current="page"> <i class="fa fa-arrow-right" aria-hidden="true"></i> Bill Reports</li>
  </ol>
</nav>
</div>
<hr>
	<sf:form method="post"
		action="${pageContext.request.contextPath}/ctl/bill/search"
		modelAttribute="form">
		<div class="card">
			<h5 class="card-header"
				style="background-color: #00061df7; color: white;">Bill Report</h5>
			<div class="card-body">
				<div class="row g-3">
						
				<div class="col-md-6">
					<s:bind path="vendorType">
						<sf:select class="form-control" path="${status.expression}">
									<sf:option value="" label="Select" />
									<sf:options   items="${type}" />
								</sf:select>
						<font color="red" style="font-size: 13px"><sf:errors
								path="${status.expression}" /></font>
					</s:bind>
				</div>
					
					<div class="col">
						<input type="submit" class="btn btn btn-outline-primary"
							name="operation" value="Search"></input> or <input type="submit"
							class="btn btn btn-outline-secondary" name="operation"
							value="Reset">
					</div>
				</div>
				<b><%@ include file="businessMessage.jsp"%></b>
				<br>
				<sf:input type="hidden" path="pageNo" />
				<sf:input type="hidden" path="pageSize" />

				<sf:input type="hidden" path="listsize" />
				<sf:input type="hidden" path="total" />
				<sf:input type="hidden" path="pagenosize" />

				<table class="table table-bordered border-primary">
					<thead>
						<tr>
						
							<th scope="col">#</th>
							<th scope="col">Customer Name</th>
							<th scope="col">Vendor Name</th>
							<th scope="col">Vendor Type</th>							
							<th scope="col">Amount</th>
							<th scope="col">Date</th>
							<th scope="col">Status</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="bl" varStatus="bill">
							<tr>
								<td scope="row">${bill.index+1}</td>
								<td scope="row">${bl.user.firstName} ${us.user.lastName}</td>
								<td scope="row">${bl.vendor.name}</td>
								<td scope="row">${bl.vendorType}</td>
								<td scope="row">${bl.amount}</td>
								<td scope="row">${bl.date}</td>
								<c:if test="${sessionScope.vendor !=null}">
								<td scope="row">${bl.status}</td>
								</c:if>
								<c:if test="${sessionScope.user !=null}">
									<c:if test="${bl.status =='Paid'}">
								<td scope="row">${bl.status}</td>
								</c:if>
								<c:if test="${bl.status =='Pending'}">
								<td scope="row"><a href="${invo}${bl.id}" class="btn btn-info">Pay</a></td>
								</c:if>
								</c:if>
								</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="clearfix">
				<%-- <c:if test="${sessionScope.user.roleId == 1}">
				<input type="submit" name="operation"
								class="btn btn-sm btn-danger float-start" 	<c:if test="${listsize == 0}">disabled="disabled"</c:if>
								value="Delete">
				</c:if> --%>
				
				
			<nav aria-label="Page navigation example float-end">
				<ul class="pagination justify-content-end" style="font-size: 13px">
					<li class="page-item"><input type="submit" name="operation"
								class="page-link" 	<c:if test="${form.pageNo == 1}">disabled="disabled"</c:if>
								value="Previous"></li>
								 <c:forEach var = "i" begin = "1" end = "${(listsize/10)+1}">
								 <c:if test="${i== pageNo}">
								<li class="page-item active"><a class="page-link activate" href="${addSearch}?pageNo=${i}">${i}</a></li>
								</c:if>
								<c:if test="${i != pageNo}">
								<li class="page-item"><a class="page-link" href="${addSearch}?pageNo=${i}">${i}</a></li>
								</c:if>
								</c:forEach>
					<li class="page-item"><input type="submit" name="operation"
								class="page-link" <c:if test="${total == pagenosize  || listsize < pageSize   }">disabled="disabled"</c:if>
								value="Next"></li>
				</ul>
			</nav>
			</div>
				
				
			</div>

		</div>
	</sf:form>
