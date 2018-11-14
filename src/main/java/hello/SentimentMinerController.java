package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SentimentMinerController {

    @GetMapping("/")
    public String displaySentimentData(@RequestParam(name="hashtag", required=false) String hashtag, Model model) {
        model.addAttribute("hashtag", hashtag);
        return "minerDisplay";
    }

}