package dsg.unibamberg.assignment1.controller;

import dsg.unibamberg.assignment1.form.AddCrateForm;
import dsg.unibamberg.assignment1.form.AddToCartForm;
import dsg.unibamberg.assignment1.service.IBottleService;
import dsg.unibamberg.assignment1.service.ICrateService;
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


@Slf4j
@Controller
@RequestMapping(value = "/crates")
public class CrateController {

    private final ICrateService crateService;
    private final IBottleService bottleService;

    @Autowired
    public CrateController(ICrateService crateService, IBottleService bottleService) {
        this.crateService = crateService;
        this.bottleService = bottleService;
    }

    @GetMapping
    public String getCrates(Model model) {
        model.addAttribute("crates", this.crateService.findAll());
        model.addAttribute("addToCartForm", new AddToCartForm());
        return "crates";
    }

    @GetMapping("/add")
    public String showAddCrate(Model model) {
        model.addAttribute("bottles", this.bottleService.findAll());
        model.addAttribute("addCrateForm", new AddCrateForm());
        return "add_new_crate";
    }

    @PostMapping("/add")
    public String addNewCrate(@Valid AddCrateForm form, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            log.info("Adding crate failed. Has following errors: " + form.toString());
            return "add_new_crate";
        }
        //TODO: - Fix later. has error
        try {
            this.crateService.createCrate(form);
            return "redirect:/crates";
        } catch (Exception e) {
            return "add_new_crate";
        }
    }
}
