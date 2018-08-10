package com.example.statslibrarytest.controller;

import org.apache.commons.math3.distribution.PoissonDistribution;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Controller
public class WebController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("answer")
    public String answer(ModelMap modelMap, @RequestParam("mean") int mean, @RequestParam("b") int b) {
        modelMap.addAttribute("mean", mean);
        modelMap.addAttribute("b", b);
        PoissonDistribution poi = new PoissonDistribution(mean);
        double result =poi.probability(b);

        BigDecimal bd = new BigDecimal(String.valueOf(result));
        BigDecimal bd3 = bd.setScale(4, RoundingMode.HALF_UP);
        result = bd3.doubleValue()*100;

        modelMap.addAttribute("result", result);
        return "answer";
    }

    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    }
}
