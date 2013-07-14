var realTimeSeries = null;
var currentMinute = null;
var hasData = false;
var fetchingData = false;
var lastInterval = null;

var baseMinute = null;

function getInterval() {
	if ($("#interval").val() == undefined || $("#interval").val() == 0) {
		return 2000;
	}
	return $("#interval").val() * 1000;
}
var loadLineChart = function(path, metricName) {
	hasData = false;
	fetchingData = true;
	fetchingData = false;
	currentMinute = null;
	realTimeSeries = null;
	
	baseMinute = null;
	$("#container").html("<img src='../img/loading.gif'/>");
	$.ajax({
		dataType : "json",
		type : "GET",
		url : "metrics/" + path + "/" + metricName + "?minute=-1",

	}).done(
			function(data) {
				$(function() {
					if (data.series.length === 0) {
						currentMinute = -1;
						new Highcharts.Chart({
							chart : {
								renderTo : 'container',
								type : 'line',
								events : {
									load : function() {
										var that = this;
										if( lastInterval != null ){
											clearInterval(lastInterval);
										}
										lastInterval = setInterval(function() {
											addData(that, path, metricName);
										}, getInterval());
									}
								}
							},
							title : {
								text : data.title
							},
							xAxis : {
								type : 'datetime',
								dateTimeLabelFormats : { // don't display the
															// dummy year
									month : '%e. %b',
									year : '%b'
								}
							},
							yAxis : {
								title : {
									text : 'Count'
								},
								min : 0
							},
							tooltip : {
								formatter : function() {
									return '<b>'
											+ this.series.name
											+ '</b><br/>'
											+ Highcharts.dateFormat('%e. %b',
													this.x) + ': ' + this.y
											+ ' m';
								}
							}
						});
					} else {
						currentMinute = data.series[0].data[0][0];
						
						baseMinute = currentMinute;
						realTimeSeries = data;
						hasData = true;
						var fakeSeries = [];
						for ( var i = 0; i < data.series.length; i++) {
							var rts = {
								name : data.series[i].name,
								data : [ data.series[i].data.shift() ]
							};
							fakeSeries.push(rts);
						}
						new Highcharts.Chart({
							chart : {
								renderTo : 'container',
								type : 'line',
								events : {
									load : function() {
										var that = this;
										if( lastInterval != null ){
											clearInterval(lastInterval);
										}
										lastInterval = setInterval(function() {
											addData(that, path, metricName);
										}, getInterval());
									}
								}
							},
							title : {
								text : data.title
							},
							xAxis : {
								type : 'datetime',
								dateTimeLabelFormats : { // don't display the
															// dummy year
									month : '%e. %b',
									year : '%b'
								}
							},
							yAxis : {
								title : {
									text : 'Count'
								},
								min : 0
							},
							tooltip : {
								formatter : function() {
									return '<b>'
											+ this.series.name
											+ '</b><br/>'
											+ Highcharts.dateFormat('%e. %b',
													this.x) + ': ' + this.y
											+ ' m';
								}
							},

							series : fakeSeries,

						});
						fetchingData = false;
					}
				});
			});
	;
};

var privateLoadBatchLineChart = function(path, metricName) {
	$("#container").html("<img src='../img/loading.gif'/>");
	$.ajax({
		dataType : "json",
		type : "GET",
		url : "metrics/" + path + "/" + metricName,

	}).done(
			function(data) {
				$(function() {
					$('#container').highcharts(
							{
								chart : {
									type : 'line'
								},
								title : {
									text : data.title
								},
								xAxis : {
									type : 'datetime',
									dateTimeLabelFormats : { // don't display
																// the dummy
																// year
										month : '%e. %b',
										year : '%b'
									}
								},
								yAxis : {
									title : {
										text : 'Count'
									},
									min : 0
								},
								tooltip : {
									formatter : function() {
										return '<b>'
												+ this.series.name
												+ '</b><br/>'
												+ Highcharts.dateFormat(
														'%e. %b', this.x)
												+ ': ' + this.y + ' m';
									}
								},

								series : data.series,

							});
				});
			});
	;
};

function addData(chart, path, metricName) {
	if ( (!hasData || realTimeSeries.series[0].data.length === 8) && !fetchingData ){
		if( $('#container').highcharts() == undefined ){
			return;
		}
		fetchingData = true;
		$.ajax(
						{
							dataType : "json",
							type : "GET",
							url : "metrics/" + path + "/" + metricName
									+ "?minute=" + (hasData? currentMinute + (8*60000):-1),

						})
				.done(
						function(data) {
							for ( var i = 0; i < data.series.length; i++) {
								if( !hasData ){
									currentMinute = data.series[0].data[0][0];

									baseMinute = currentMinute;
									hasData = true;
									for ( var i = 0; i < data.series.length; i++) {
										var rts = {
											name : data.series[i].name,
											data : [ data.series[i].data.shift() ]
										};
										chart.addSeries(rts);
									}
									realTimeSeries = data;
								}
								if (i >= realTimeSeries.series.length) {
									realTimeSeries.series.push(data.series[i]);
								} else {
									realTimeSeries.series[i].data = realTimeSeries.series[i].data
											.concat(data.series[i].data);
								}
								fetchingData = false;
							}
						});
	}
	if (hasData) {
		if (typeof chart.series == 'undefined'
				|| typeof chart.series[0].data == 'undefined'
				|| typeof chart.series[0].data[0] == 'undefined'
				|| typeof realTimeSeries.series[0].data[0] == 'undefined') {
			return;
		}

		normalize(realTimeSeries.series, currentMinute);
		for ( var i = 0; i < chart.series.length; i++) {
			if (realTimeSeries.series[i].data[0] != undefined && currentMinute === realTimeSeries.series[i].data[0][0]) {
				chart.series[i].addPoint(realTimeSeries.series[i].data.shift(),
						true, chart.series[i].data.length > 10);
			}
		}
	}
	if (hasData) {
		currentMinute += 60000;
	}
}

function normalize(series, time){
	for( var i = 0; i<series.length; i++){
		while( series[i].data.length != 0 && series[i].data[0][0] < time ){
			series[i].data.shift();
		}
	}
}

var loadBarChart = function(title, metricName, yaxis) {
	$("#container").html("<img src='../img/loading.gif'/>");
	$.ajax({
		dataType : "json",
		type : "GET",
		url : "metrics/batch/" + metricName,

	}).done(function(data) {
		$(function() {
			$('#container').highcharts({
				chart : {
					type : 'column'
				},
				title : {
					text : title
				},
				xAxis : {
					categories : data.categories
				},
				yAxis : {
					min : 0,
					title : {
						text : 'Quantity',
						align : 'high'
					},
					labels : {
						overflow : 'justify'
					}
				},

				series : [ {
					name : yaxis,
					data : data.series
				} ],

			});
		});
	});
	;
};

var loadPieChart = function(title, metricName) {
	$("#container").html("<img src='../img/loading.gif'/>");
	$
			.ajax({
				dataType : "json",
				type : "GET",
				url : "metrics/batch/" + metricName,

			})
			.done(
					function(data) {
						$(function() {
							$('#container')
									.highcharts(
											{
												chart : {
													plotBackgroundColor : null,
													plotBorderWidth : null,
													plotShadow : false
												},
												title : {
													text : title
												},
												tooltip : {
													pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
												},
												plotOptions : {
													pie : {
														allowPointSelect : true,
														cursor : 'pointer',
														dataLabels : {
															enabled : true,
															color : '#000000',
															connectorColor : '#000000',
															format : '<b>{point.name}</b>: {point.percentage:.1f} %'
														}
													}
												},
												series : [ {
													type : 'pie',
													name : 'percentage',
													data : data.series
												} ]
											});
						});
					});
	;
};

var loadRealtimeChart = function(metricName) {
	loadLineChart("rt", metricName);
};

var loadBatchLineChart = function(metricName) {
	privateLoadBatchLineChart("batch", metricName);
};