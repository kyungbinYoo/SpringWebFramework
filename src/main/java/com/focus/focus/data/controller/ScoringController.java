package com.focus.focus.data.controller;

import com.focus.focus.data.ScoringRepository;
import com.focus.focus.data.dto.ScoringDto;
import com.focus.focus.data.entity.Scoring;
import com.focus.focus.service.ScoringService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Optional;


@Controller
@NoArgsConstructor
@AllArgsConstructor
public class ScoringController {

    @Autowired
    ScoringService scoringService;
    @Autowired
    ScoringRepository scoringRepository;
    @RequestMapping("/scoring")
    public String list(Model model) {

        model.addAttribute("scoring", scoringService.findAll());
        return "list";
    }
    @RequestMapping("/scoring/{id}")
    public String read(@PathVariable long id, Model model) {

        model.addAttribute("scoring", scoringService.findById(id));
        return "read";
    }
    @RequestMapping("/scoring/delete/{id}")
    public String delete(@PathVariable long id)  {
        scoringService.deleteById(id);
        return "redirect:/scoring";
    }

    @RequestMapping("/scoring/addform")
    public String addform()  {
        return "addForm";
    }
    @RequestMapping("/scoring/add")
    public String add(@ModelAttribute ScoringDto scoring)  {
        scoringService.save(scoring);
        return "redirect:/scoring";
    }
    @RequestMapping("/scoring/updateForm/{id}")
    public String updateScoring(@PathVariable Long id,  Model model) {
        ScoringDto scoring = scoringService.findById(id);
        model.addAttribute("scoring", scoring);
        return "updateForm";
    }
    @RequestMapping("/scoring/update")
    public String update(@ModelAttribute ScoringDto scoring)  {
        scoringService.save(scoring);
        return "redirect:/scoring";
    }

    @GetMapping("/scoring/search")
    public String searchByContent(@RequestParam("content") String content, Model model) {
        // content로 검색
        List<ScoringDto> results = scoringService.searchByContent(content);
        // 검색 결과를 모델에 추가
        model.addAttribute("scoringResults", results);

        return "scoringSearchResults"; // 검색 결과를 표시할 Thymeleaf 템플릿 이름
    }

}
