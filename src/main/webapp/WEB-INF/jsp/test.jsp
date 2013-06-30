<%@ include file="header.jsp"%>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script>
var chart;
var count = 0;
$(document).ready(function() {

    chart = new Highcharts.Chart({
        chart: {
            renderTo: 'graph',
            type: 'line',
            marginRight: 130,
            marginBottom: 25, 
            events: {
                load: function() {
                    setInterval(addData, 1000);
                }
            }
        },
        title: {
            text: 'Total Viewers',
            x: -20 //center
        },
        xAxis: {
            categories: [1,2,3,4,5]
        },
        yAxis: {
            title: {
                text: 'totalViewers'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -10,
            y: 100,
            borderWidth: 0
        }
    });
    $.ajax({
        url: '/bigdata/bin/metrics/test/TotalViewers/22746240',
        type: "GET",
        dataType: "json",
        success: function(data) {
        	var length = data.length, element = null;
        	for (var i = 0; i < length; i++) {
          		element = data[i];
	            chart.addSeries({
	              name: element.metricKey,
	              data: [element.point]
	            });
       		}
        },
        cache: false
    });
});



function addData() {
	count++;
    $.ajax({
        url: '/bigdata/bin/metrics/test/TotalViewers/' + (22746240 + count),
        type: "GET",
        dataType: "json",
        success: function(data) {
        	var length = data.length;
        	for (var i = 0; i < length; i++) {
	        	chart.series[i].addPoint(data[i].point, true, count > 10);
       		}
        },
        cache: false
    });
}
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