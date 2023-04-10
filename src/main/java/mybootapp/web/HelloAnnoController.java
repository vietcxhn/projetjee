package mybootapp.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/tests")
public class HelloAnnoController {

    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView sayHello() {
        String now = (new Date()).toString();
        logger.info("Running " + this);
        return new ModelAndView("hello", "now", now);
    }

    @RequestMapping(value = "/counter", method = RequestMethod.GET)
    public ModelAndView counter(HttpSession session, HttpServletRequest request,
            HttpServletResponse response) {
    	int counter = session.getAttribute("counter") == null? 0 : (int)session.getAttribute("counter");
    	counter++;
    	session.setAttribute("counter", counter);
        String now = (new Date()).toString();
        logger.info("Running " + this);
        return new ModelAndView("hello", "now", now);
    }
    
    @RequestMapping(value = "/plus10", method = RequestMethod.GET)
    public ModelAndView plus10(
            @RequestParam(value = "num", defaultValue = "100") Integer value,
            @RequestParam(value = "date", defaultValue = "100")@DateTimeFormat() Date date) {
        logger.info("Running plus10 controler with param = " + value);
        return new ModelAndView("hello", "now", value + 10);
    }

    @RequestMapping(value = "/voir/{param}", method = RequestMethod.GET)
    public ModelAndView voir(@PathVariable("param") Integer param) {
        logger.info("Running param controler with param=" + param);
        return new ModelAndView("hello", "now", param);
    }
    
    @RequestMapping(value = "/matrix/{param}", method = RequestMethod.GET)
    @ResponseBody
    public String testMatrix(//
            @PathVariable("param") String param, //
            @MatrixVariable(name = "a", defaultValue = "A") String a, //
            @MatrixVariable(name = "b", defaultValue = "1") Integer b//
    ) {
        return String.format("param=%s, a=%s, b=%d", param, a, b);
    }
    
    @RequestMapping(value = "/matrix2/{param}", method = RequestMethod.GET)
    @ResponseBody
    public String testMatrix(//
            @PathVariable("param") String param, //
            @MatrixVariable() Map<String,String> a //
    ) {
    	String b = "param=" + param + ", ";
    	for (String key : a.keySet()) {
    		b += key + "=" + a.get(key) + ", ";
    	}
        return b.substring(0, b.length()-2);
    }
}