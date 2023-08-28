package dsg.unibamberg.assignment1.controller;

import dsg.unibamberg.assignment1.form.AddToCartForm;
import dsg.unibamberg.assignment1.model.Bottle;
import dsg.unibamberg.assignment1.model.Crate;
import dsg.unibamberg.assignment1.service.IBottleService;
import dsg.unibamberg.assignment1.service.ICrateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    private final IBottleService bottleService;
    private final ICrateService crateService;

    public HomeController(IBottleService bottleService, ICrateService crateService) {
        this.bottleService = bottleService;
        this.crateService = crateService;
    }

    @GetMapping
    public String getHome(Model model) {
        List<Bottle> bottles = this.bottleService.findAll();
        List<Crate> crates = this.crateService.findAll();
        model.addAttribute("bottles", bottles);
        model.addAttribute("crates", crates);
        model.addAttribute("addToCartForm", new AddToCartForm());

        return "home";
    }
}
