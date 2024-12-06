package com.focus.focus.service;

import com.focus.focus.data.dto.RequestChatGPT;
import com.focus.focus.data.dto.ResponseChatGPT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GptService {

    private final RestTemplate restTemplate;
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    public GptService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(GptService.class);


    public String getGptResponse() {
        String prompt = "지금 집중력을 향상시키기 위해 어떤 활동이나 카테고리가 도움이 될까요?" +
                " 운동, 학습, 명상 등 여러 카테고리 중에서 나에게 가장 적합한 것을 추천해 주세요.";

        LOGGER.info("GPT API 호출 시작: prompt='{}'", prompt);

        try {
            RequestChatGPT request = new RequestChatGPT(model, prompt);
            ResponseChatGPT response = restTemplate.postForObject(apiURL, request, ResponseChatGPT.class);

            if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
                String gptResponse = response.getChoices().get(0).getMessage().getContent();
                LOGGER.info("GPT API 응답: {}", gptResponse);
                return gptResponse.trim();
            } else {
                LOGGER.error("GPT API 응답이 비어 있습니다: response={}", response);
                return "추천 결과를 가져오는 데 실패했습니다.";
            }
        } catch (Exception e) {
            LOGGER.error("GPT API 호출 중 오류 발생", e);
            return "추천 결과를 가져오는 데 실패했습니다.";
        }
    }

}
