<%@ page contentType="text/html"%>
<%@ page pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/bigdata/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="/bigdata/css/chosen.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.pack.js"></script>
<script src="/bigdata/js/chosen.jquery.js"></script>
<script src="/bigdata/js/bootstrap.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("select").chosen();
});
</script>
</head>
<body>
	<div class="navbar container">
	    <div class="navbar-inner">
	    <a class="brand" href="/bigdata/bin/index">BigData-Pink Elephant TV</a>
	    <ul class="nav">
	    </ul>
	    </div>
	</div>
	<div class="container">
	
