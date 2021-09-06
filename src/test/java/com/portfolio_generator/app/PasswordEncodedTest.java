package com.portfolio_generator.app;

import com.portfolio_generator.app.models.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class PasswordEncodedTest {
    User user;

    public PasswordEncodedTest(User user) {
        this.user = user;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "password";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("Raw password is " + rawPassword);
        System.out.println("Encoded password is " + encodedPassword);

        boolean isPasswordMatch = encoder.matches(rawPassword, encodedPassword);
        System.out.println("Password " + rawPassword + " is password match " + isPasswordMatch);

        rawPassword = "parola";

        isPasswordMatch = encoder.matches(rawPassword, encodedPassword);
        System.out.println("Password " + rawPassword + " is password match " + isPasswordMatch);


    }

}
