var realTimeSeries = null;

function getInterval(){
	if( $("#interval").val() == undefined || $("#interval").val() == 0 ){
		return 2000;
	}
	return $("#interval").val() * 1000;
}
var loadLineChart = function(path, metricName) {
	$("#container").html("<img src='../img/loading.gif'/>");
	$.ajax({
			dataType: "json",
			type: "GET",
			url: "metrics/" + path + "/" + metricName,
			
		}).done(function( data ) {
			$(function () {
				realTimeSeries = data;
				var fakeSeries = [];
				for( var i = 0; i < data.series.length; i++ ){
					var rts = {name:data.series[i].name, data:[data.series[i].data.shift()]};
					fakeSeries.push(rts);
				}
				new Highcharts.Chart({
		            chart: {
		            	renderTo:'container',
		                type: 'line',
		                events: {
		                    load: function() {
		                    	var that = this;
		                        setInterval(function() {addData(that);}, getInterval());
		                    }
		                }
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
		            
		            series: fakeSeries,
		            
		        });
		    });
		});;
};

var privateLoadBatchLineChart = function(path, metricName) {
	$("#container").html("<img src='../img/loading.gif'/>");
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
};

function addData(chart) {
	if (typeof chart.series == 'undefined' || typeof chart.series[0].data == 'undefined' || typeof chart.series[0].data[0] == 'undefined'){
		return
	}
	var time = realTimeSeries.series[0].data[0];
	for (var i = 0; i < chart.series.length; i++) {
		if( time[0] === realTimeSeries.series[i].data[0][0]){
			chart.series[i].addPoint(realTimeSeries.series[i].data.shift(), true, chart.series[i].data.length > 10);
		}
	}
}

var loadBarChart = function(title, metricName, yaxis) {
	$("#container").html("<img src='../img/loading.gif'/>");
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
};

var loadPieChart = function(title, metricName) {
	$("#container").html("<img src='../img/loading.gif'/>");
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
};

var loadRealtimeChart = function(metricName) {
	loadLineChart("rt", metricName);
};

var loadBatchLineChart = function(metricName) {
	privateLoadBatchLineChart("batch", metricName);
};