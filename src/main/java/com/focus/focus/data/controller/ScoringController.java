package com.focus.focus.data.controller;

import com.focus.focus.data.ScoringRepository;
import com.focus.focus.data.dto.ScoringDto;
import com.focus.focus.data.entity.Scoring;
import com.focus.focus.data.entity.User;
import com.focus.focus.service.ScoringService;
import com.focus.focus.service.UserService;
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
    private UserService userService;

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

    @GetMapping("scoring/addform")
    public String showAddScoringForm(@RequestParam("uid") long userId, Model model) {
        // 유저 정보를 모델에 추가
        model.addAttribute("user", userService.findById(userId));
        return "addform";
    }

    @GetMapping("/addform")
    public String showAddForm(@RequestParam("uid") long userId, Model model) {
        // 유저 정보를 모델에 추가
        model.addAttribute("user", userService.findById(userId));
        return "addform";
    }
    @RequestMapping("/scoring/add")
    public String add(@ModelAttribute ScoringDto scoringDto, @RequestParam("userId") Long userId) {
        // 유저 정보 가져오기
        User user = userService.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }
        // ScoringDto에 유저 설정
        scoringDto.setUser(user);
        scoringService.save(scoringDto);

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


}
