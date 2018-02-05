package pnodder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pnodder.formatters.BikeFormatter;
import pnodder.model.Order;
import pnodder.services.BikeService;
import pnodder.services.OrderService;

@Controller
public class OrderController {

    private BikeService bikeService;
    private OrderService orderService;

    public OrderController(BikeService bikeService, OrderService orderService) {
        this.bikeService = bikeService;
        this.orderService = orderService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new BikeFormatter(bikeService));
    }

    @ModelAttribute
    public void populateModel(Model model) {
        model.addAttribute("allBikes", bikeService.findAll());
        model.addAttribute("order", new Order());
    }

//    @GetMapping("/home")
//    public String getHome() {
//        return "home";
//    }
//
//    @GetMapping("/bikes")
//    public String getBikes() {
//        return "bikes";
//    }

    @GetMapping("/order")
    public String getOrder() {
        return "order";
    }

    @PostMapping("/submitorder")
    public String submitOrder(Order order, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            orderService.submitOrder(order);
            return "redirect:/order";
        } else {
            return "order";
        }
    }

//    @PostMapping("/saveOrder")
//    public String saveResident(Order order) {
//        orderRepository.save(order);
//        return "index";
//    }
    
    
}
