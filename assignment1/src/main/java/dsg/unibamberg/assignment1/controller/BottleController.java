package dsg.unibamberg.assignment1.controller;

import dsg.unibamberg.assignment1.form.AddBottleForm;
import dsg.unibamberg.assignment1.form.AddToCartForm;
import dsg.unibamberg.assignment1.model.Bottle;
import dsg.unibamberg.assignment1.service.IBottleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "bottles")
public class BottleController {

    private final IBottleService bottleService;

    @Autowired
    public BottleController(IBottleService bottleService) {
        this.bottleService = bottleService;
    }

    @GetMapping
    public String showAllBottles(Model model) {
        List<Bottle> bottles = this.bottleService.findAll();
        model.addAttribute("bottles", bottles);
        model.addAttribute("addToCartForm", new AddToCartForm());
        return "bottles";
    }

    @GetMapping("/add")
    public String showAddBottle(Model model) {
        model.addAttribute("addBottleForm", new AddBottleForm());
        return "add_new_bottle";
    }

    @PostMapping("/add")
    public String addNewBottle(@Valid AddBottleForm form, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            log.info("Adding bottle failed. Has following errors: " + form.toString());
            return "add_new_bottle";
        }
        // TODO: Check error and show respective error message later..
        try {
            this.bottleService.createBottle(form);
            return "redirect:/bottles";
        } catch (Exception e) {
            return "add_new_bottle";
        }
    }

}
