package com.focus.focus.data.controller;

import com.focus.focus.data.dto.ScoringDto;
import com.focus.focus.service.ScoringService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@NoArgsConstructor
@AllArgsConstructor
public class ScoringController {

    @Autowired
    ScoringService scoringService;
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

}
