package app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SentimentMinerController {

    @GetMapping("/")
    public String displayHomePage() {
        return "home";
    }

    @PostMapping("/")
    public String displaySentimentData(@RequestParam(name="hashtag", required = true) String hashtag, Model model) {

        String dataset = "{positive: 10, negative: 30, neutral: 60 }";
        model.addAttribute("dataset", dataset);
        return "minerDisplay";
    }

}