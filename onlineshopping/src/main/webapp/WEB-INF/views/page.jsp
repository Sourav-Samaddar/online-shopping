<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Shopping - ${title}</title>
<script type="text/javascript">
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
	//alert(menu);
</script>

<!-- Bootstrap core CSS -->

<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Flatly Theme -->
<link href="${css}/bootstrap-flatly-theme.css" rel="stylesheet">

<!-- Bootstrap Datatable -->
<link href="${css}/dataTables.bootstrap4.css" rel="stylesheet">

<!-- Bootstrap for glyphIcon -->
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->
		<%@ include file="./shared/navbar.jsp"%>

		<!-- Page Content -->
		
		<div class="content">
		
			<!-- Loading the Home content -->
			<c:if test="${userClickedHome == true}">
				<%@ include file="home.jsp"%>
			</c:if>

			<!-- Load only when User clicked about -->
			<c:if test="${userClickedAbout == true}">
				<%@ include file="about.jsp"%>
			</c:if>

			<!-- Load only when user clicked contact -->
			<c:if test="${userClickedContact == true}">
				<%@ include file="contact.jsp"%>
			</c:if>
			
			<!-- Load only when user clicked All product or Category Product -->
			<c:if test="${userClickAllProducts == true or userClickCategoryProducts == true}">
				<%@ include file="listProducts.jsp"%>
			</c:if>
			
			<!-- Load a Single Product -->
			<c:if test="${userClickShowProduct == true}">
				<%@ include file="singleProduct.jsp"%>
			</c:if>
			
			<!-- Load a Manage Products -->
			<c:if test="${userClickManageProducts == true}">
				<%@ include file="manageProducts.jsp"%>
			</c:if>
		</div>
		
		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/jquery.min.js"></script>
		
		<!-- Jquery Validator -->
		<script src="${js}/jquery.validate.js"></script>
		
		<script src="${js}/bootstrap.bundle.min.js"></script>
		
		<!-- Datatable Plugin -->
		<script src="${js}/jquery.dataTables.js"></script>
		
		<!-- Datatable bootstarp Plugin -->
		<script src="${js}/dataTables.bootstrap4.js"></script>
		
		<!-- BootBox -->
		<script src="${js}/bootbox.min.js"></script>

		<!-- Self coded Java Script -->
		<script src="${js}/myapp.js"></script>

	</div>
</body>

</html>
