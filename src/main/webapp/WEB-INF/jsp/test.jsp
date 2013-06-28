<%@ include file="header.jsp"%>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script>$(function () {
    $('#graph').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: 'Snow depth in the Vikjafjellet mountain, Norway'
        },
        subtitle: {
            text: 'An example of irregular time data in Highcharts JS'
        },
        xAxis: {
            type: 'datetime',
            dateTimeLabelFormats: { // don't display the dummy year
                month: '%e. %b',
                year: '%b'
            }
        },
        yAxis: {
            title: {
                text: 'Snow depth (m)'
            },
            min: 0
        },
        tooltip: {
            formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+
                    Highcharts.dateFormat('%e. %b', this.x) +': '+ this.y +' m';
            }
        },
        
        series: [      	<c:forEach var="real" items="${rts}" varStatus="loop">
  		{ name: '${real.metricKey}', data:[[${real.minute}, ${real.quantity}],[${real.minute}+1, ${real.quantity}+20],[${real.minute}+2, ${real.quantity}+10]]}
  		<c:if test="${!loop.last}">,</c:if>
  	</c:forEach>]
    });
});

</script>
<div class="container">

	<!-- Main hero unit for a primary marketing message or call to action -->
	<div class="hero-unit">
		<h1>Bigdata visualization</h1>
		<p>
			This is a Pink elephant TV statistics viewer, feel free to send us
			your <a href="/contact/">recommendations </a>!
		</p>
		<p>
			<a href="#" class="btn btn-primary btn-large">Learn more »</a>
		</p>
	</div>
	<div id="graph"></div>

	<!-- Example row of columns -->


</div>
<%@ include file="footer.jsp"%>