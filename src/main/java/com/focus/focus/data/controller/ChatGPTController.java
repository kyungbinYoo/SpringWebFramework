package com.focus.focus.data.controller;

import com.focus.focus.service.GptService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
public class ChatGPTController {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;
    private final RestTemplate template;
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatGPTController.class);

    @Autowired
    private GptService gptService;

    /*@GetMapping("/focus/recommendation")
    public String getFocusRecommendation(Model model) {
        LOGGER.info("GET /focus/recommendation 요청 처리 시작");


        String recommendation = gptService.getGptResponse();
        LOGGER.info("추천 결과: {}", recommendation);

        model.addAttribute("recommendation", recommendation);
        return "main"; // main.html로 View 반환

    }*/

    @GetMapping("/focus/recommendation")
    @ResponseBody
    public String getFocusRecommendation() {
        // GPT에서 추천 결과를 가져옵니다.
        return gptService.getGptResponse(); // 문자열 결과를 반환
    }

}
