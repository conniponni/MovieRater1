package se.iths.connie.movierater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.connie.movierater.model.Director;
import se.iths.connie.movierater.service.DirectorService;

@Controller
@RequestMapping("/directors")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    private String getAllDirectors(Model model) {
        model.addAttribute("directors", directorService.getAllDirectors());
        return "directors";
    }

    @GetMapping("/new")
    public String showCreateSide() {
        return "create- director";
    }

    @PostMapping
    public String createDirector(@ModelAttribute Director director) {
        Director director1 = directorService.createDirector(director);
        return "redirect:/directors";
    }

    @GetMapping("/{id}")
    public String getDirector(@PathVariable Long id, Model model) {
        model.addAttribute("director", directorService.getDirector(id));
        return "director";
    }

    @DeleteMapping("/{id}")
    public String deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
        return "redirect:/directors";
    }

    @PutMapping("/{id}")
    public String updateDirector(@PathVariable Long id, @ModelAttribute Director director) {
        Director director1 = directorService.updateDirector(id, director);
        return "redirect:/directors";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Director director = directorService.getDirector(id);
        model.addAttribute("director", director);
        return "edit-director";
    }
}
