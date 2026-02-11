package se.iths.connie.movierater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.connie.movierater.model.Review;
import se.iths.connie.movierater.service.ReviewService;

import java.time.LocalDate;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getAllReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "review-list";
    }

    @GetMapping("/{id}")
    public String getReview(@PathVariable Long id, Model model) {
        model.addAttribute("review", reviewService.getReviewById(id));
        return "review-view";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("review", new Review());
        return "review-create";
    }

    @PostMapping
    public String createReview(@ModelAttribute Review review) {
        review.setCreatedAt(LocalDate.now());
        reviewService.createReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("review", reviewService.getReviewById(id));
        return "review-edit";
    }

    @PutMapping("/{id}")
    public String updateReview(@PathVariable Long id, @ModelAttribute Review review) {
        reviewService.updateReview(id, review);
        return "redirect:/reviews";
    }

    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }
}
