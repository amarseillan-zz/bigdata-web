var loadLineChart = function(path, metricName) {
	$("#container").html("<img src='/bigdata/img/loading.gif'/>");
	$.ajax({
			dataType: "json",
			type: "GET",
			url: "metrics/" + path + "/" + metricName,
			
		}).done(function( data ) {
			$(function () {
		        $('#container').highcharts({
		            chart: {
		                type: 'line'
		            },
		            title: {
		                text: data.title
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
		                    text: 'Count'
		                },
		                min: 0
		            },
		            tooltip: {
		                formatter: function() {
		                        return '<b>'+ this.series.name +'</b><br/>'+
		                        Highcharts.dateFormat('%e. %b', this.x) +': '+ this.y +' m';
		                }
		            },
		            
		            series: data.series,
		            
		        });
		    });
		});;
}

var loadBarChart = function(title, metricName, yaxis) {
	$("#container").html("<img src='/bigdata/img/loading.gif'/>");
	$.ajax({
			dataType: "json",
			type: "GET",
			url: "metrics/batch/" + metricName,
			
		}).done(function( data ) {
			$(function () {
		        $('#container').highcharts({
		            chart: {
		                type: 'column'
		            },
		            title: {
		                text: title
		            },
		            xAxis: {
		                categories: data.categories
		            },
		            yAxis: {
		                min: 0,
		                title: {
		                    text: 'Quantity',
		                    align: 'high'
		                },
		                labels: {
		                    overflow: 'justify'
		                }
		            },
		            
		            series: [{
		            	name: yaxis,
		            	data: data.series
		            }],
		            
		        });
		    });
		});;
}

var loadPieChart = function(title, metricName) {
	$("#container").html("<img src='/bigdata/img/loading.gif'/>");
	$.ajax({
			dataType: "json",
			type: "GET",
			url: "metrics/batch/" + metricName,
			
		}).done(function( data ) {
			$(function () {
				$('#container').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false
			        },
			        title: {
			            text: title
			        },
			        tooltip: {
			    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    color: '#000000',
			                    connectorColor: '#000000',
			                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
			                }
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: 'percentage',
			            data: data.series
			        }]
			    });
		    });
		});;
}

var loadRealtimeChart = function(metricName) {
	loadLineChart("rt", metricName);
}

var loadBatchLineChart = function(metricName) {
	loadLineChart("batch", metricName);
}