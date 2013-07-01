package bigdata.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping(value = "index", method = RequestMethod.GET)
	protected ModelAndView index(@RequestParam( required = false ) Long interval) {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("interval", interval);
		return mav;
	}

}
