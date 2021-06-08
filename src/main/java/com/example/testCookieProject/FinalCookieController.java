package com.example.testCookieProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class FinalCookieController {
    @GetMapping("/")
    public Cart readCookie(@CookieValue(value = "cart", defaultValue = "empty") Cart cart) {
        return cart;
    }

    @GetMapping("/Cookie")
    public String setCookie(HttpServletResponse response) {

        // tworzenie cookie
        Cookie cookie = new Cookie("cart", "addItem");
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/"); // dostep globalny zmiana na cart dostep w cart itp

        //dodaje cookie do responsa
        response.addCookie(cookie);

        return "Create Cookie";
    }

    @GetMapping("/deleteCookie")
    public String deleteCookie(HttpServletResponse response) {

        // tworzy cookie
        Cookie cookie = new Cookie("cart", null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        //dodaje cookie do responsa z racji ze jego "zycie" = 0 usuwa cookie
        response.addCookie(cookie);

        return "Cookie is deleted!";
    }

    @GetMapping("/allCookies")
    public String readAllCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .map(c -> c.getName() + c.getValue()).collect(Collectors.joining(", "));
        }

        return "No cookies";
    }
}
