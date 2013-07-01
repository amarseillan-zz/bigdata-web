package bigdata.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bigdata.service.MetricService;

import com.google.gson.Gson;

@Controller
@RequestMapping("/metrics")
public class MetricController {

	private MetricService metricService;
	
	@RequestMapping(value = "/rt/{metric_type}", method = RequestMethod.GET)
	protected @ResponseBody String getRTMetrics(@PathVariable String metric_type){
		
		return (new Gson()).toJson(this.metricService.getRealTimeMetrics(metric_type));
	}
	
	@RequestMapping(value = "/batch/top10channels", method = RequestMethod.GET)
	protected @ResponseBody String getTop10Channels(){
		
		return (new Gson()).toJson(this.metricService.getTop10Channels());
	}
	
	@RequestMapping(value = "/batch/top10categories", method = RequestMethod.GET)
	protected @ResponseBody String getTop10Categories(){
		
		return (new Gson()).toJson(this.metricService.getTop10Categories());
	}
	
	@RequestMapping(value = "/batch/avgdurationcategories", method = RequestMethod.GET)
	protected @ResponseBody String getAvgDurationCategories(){
		
		return (new Gson()).toJson(this.metricService.getAvgDurationCategory());
	}
	
	@RequestMapping(value = "/batch/avgdurationchannels", method = RequestMethod.GET)
	protected @ResponseBody String getAvgDurationChannels(){
		
		return (new Gson()).toJson(this.metricService.getAvgDurationChannel());
	}
	
	@RequestMapping(value = "/batch/topchannelads", method = RequestMethod.GET)
	protected @ResponseBody String getTopChannelAds(){
		
		return (new Gson()).toJson(this.metricService.getTopChannelAds());
	}
	
	@RequestMapping(value = "/batch/audiencepertype", method = RequestMethod.GET)
	protected @ResponseBody String getAudiencePerType(){
		
		return (new Gson()).toJson(this.metricService.getAudiencePerType());
	}
	
	@RequestMapping(value = "/batch/audienceperfg", method = RequestMethod.GET)
	protected @ResponseBody String getAudiencePerFamilyGroup(){
		
		return (new Gson()).toJson(this.metricService.getAudiencePerFamilyGroup());
	}
	
	@RequestMapping(value = "/batch/worstshows", method = RequestMethod.GET)
	protected @ResponseBody String getWorstShows(){
		
		return (new Gson()).toJson(this.metricService.getWorstShows());
	}
	

	public void setMetricService(MetricService metricService) {
		this.metricService = metricService;
	}
}
