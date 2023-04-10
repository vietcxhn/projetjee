package mybootapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/counter")
public class CounterController {

    class CounterBean {
        int value = 0;
    }

    @RequestMapping(value = "/init")
    @ResponseBody
    public String init(HttpSession session) {
        var counter = new CounterBean();
        session.setAttribute("counter", counter);
        return String.format("int counter = %d\n", counter.value);
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public String show(@SessionAttribute(required = false) CounterBean counter) {
        if (counter == null) {
            return ("counter is null\n");
        }
        return String.format("counter = %d\n", counter.value);
    }

    @RequestMapping(value = "/inc")
    @ResponseBody
    public String incCounter(@SessionAttribute CounterBean counter) {
        counter.value++;
        return (show(counter));
    }

}