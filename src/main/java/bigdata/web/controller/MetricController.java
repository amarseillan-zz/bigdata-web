package bigdata.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bigdata.model.RealTimeResult;
import bigdata.service.MetricService;

@Controller
@RequestMapping("/metrics")
public class MetricController {

	private MetricService metricService;
	
	@RequestMapping(value = "/{metricType}/{metricID}", method = RequestMethod.GET)
	protected ModelAndView getMetric() {
		ModelAndView mav = new ModelAndView("index");
		
		return mav;
	}

	@RequestMapping(value = "/batch", method = RequestMethod.GET)
	protected ModelAndView getBatch(){
		ModelAndView mav = new ModelAndView("batch");
		return mav;
	}
	
	@RequestMapping(value = "/test/{metric_type}/{minute}", method = RequestMethod.GET)
	protected ModelAndView getTest(@PathVariable String metric_type, @PathVariable String minute){
		ModelAndView mav = new ModelAndView("test");
		
		List<RealTimeResult> rts = this.metricService.getRealTimeMetrics(metric_type, minute);
		mav.addObject("rts", rts);
		return mav;
	}

	public void setMetricService(MetricService metricService) {
		this.metricService = metricService;
	}
}
