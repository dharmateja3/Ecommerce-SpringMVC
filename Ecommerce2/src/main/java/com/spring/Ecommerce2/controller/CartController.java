package com.spring.Ecommerce2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.spring.Ecommerce2.entity.Cart;
import com.spring.Ecommerce2.entity.User;
import com.spring.Ecommerce2.service.CartService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Cart> cartItems = cartService.getCartItems(user.getId());
        double totalAmount = cartItems.stream()
                                      .mapToDouble(cart -> cart.getProduct().getPrice())
                                      .sum();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartCount", cartItems.size());
        model.addAttribute("totalAmount", totalAmount);
        return "cart";
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam Long productId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        cartService.addToCart(user.getId(), productId);
        return "redirect:/products";
    }

    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam Long productId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        cartService.removeFromCart(user.getId(), productId);
        return "redirect:/cart";
    }

    @GetMapping("/buyNow")
    public String buyNow(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Cart> cartItems = cartService.getCartItems(user.getId());
        double totalAmount = cartItems.stream()
                                      .mapToDouble(cart -> cart.getProduct().getPrice())
                                      .sum();
        model.addAttribute("user", user);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        return "orderDetails";
    }

    @GetMapping("/orderPlaced")
    public String orderPlaced(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("message", "Order placed successfully!");
        return "orderConfirmation";
    }
    
    @PostMapping("/placeOrder")
    public String placeOrder(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Cart> cartItems = cartService.getCartItems(user.getId());
        double totalAmount = cartItems.stream()
                                      .mapToDouble(cart -> cart.getProduct().getPrice())
                                      .sum();
        cartService.placeOrder(user, totalAmount);
        model.addAttribute("message", "Order placed successfully!");
        return "orderConfirmation";
    }
}
