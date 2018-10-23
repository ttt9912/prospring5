package ch16.springboot.controller;

import ch16.springboot.data.Singer;
import ch16.springboot.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/*
 * This is not a rest controller
 *
 * Spring injects the Model in each method
 *
 * Methods attach domain object to the view model
 * and return logical view name
 */
@Controller
@RequestMapping(value = "/singers")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @GetMapping
    public String list(Model uiModel) {
        List<Singer> singers = singerService.findAll();
        uiModel.addAttribute("singers", singers);
        return "singers";
    }

    @GetMapping(value = "/{id}")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        Singer singer = singerService.findById(id);
        uiModel.addAttribute("singer", singer);
        return "singer";
    }

    @GetMapping(value = "/edit/{id}")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        Singer singer = singerService.findById(id);
        uiModel.addAttribute("singer", singer);
        return "update";
    }

    @GetMapping(value = "/new")
    public String createForm(Model uiModel) {
        Singer singer = new Singer();
        uiModel.addAttribute("singer", singer);
        return "update";
    }

    @PostMapping
    public String saveSinger(@Valid Singer singer) {
        singerService.save(singer);
        return "redirect:/singers/" + singer.getId();
    }
}
