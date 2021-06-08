package com.example.testCookieProject;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CookieController {


@GetMapping("/preferences")
public String preferencesCart(HttpServletRequest request) {

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        return Arrays.stream(cookies)
                .map(c -> c.getName() + c.getValue())
                .collect(Collectors.joining(", "));
    }

    return "No preferences cart!";
}

}
