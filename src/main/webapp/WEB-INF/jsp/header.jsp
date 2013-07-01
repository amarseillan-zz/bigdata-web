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
<script src="/bigdata/js/highcharts.js"></script>
<script src="/bigdata/js/selectchart.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#realtimemetrics').change(function() {
		loadRealtimeChart($('#realtimemetrics').val());
		});
	$('#avgdurationcategory').click(function() {
		loadBatchLineChart("avgdurationcategories");
		});
	$('#avgdurationchannel').click(function() {
		loadBatchLineChart("avgdurationchannels");
		});
	$('#top10channels').click(function() {
		loadBarChart("Top 10 Channels", "top10channels", "Viewers");
		});
	$('#top10categories').click(function() {
		loadBarChart("Top 10 Categories", "top10categories", "Viewers");
		});
	$('#topchannelads').click(function() {
		loadBarChart("Channels With Most Ads", "topchannelads", "Ads");
		});
	$('#audiencepertype').click(function() {
		loadPieChart("Audience Per Type", "audiencepertype");
		});
	$('#audienceperfg').click(function() {
		loadPieChart("Audience Per Family Group", "audienceperfg");
		});
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
	
