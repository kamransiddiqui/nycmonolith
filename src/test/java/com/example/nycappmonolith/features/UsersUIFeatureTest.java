package com.example.nycappmonolith.features;

import com.example.nycappmonolith.models.User;
import com.example.nycappmonolith.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UsersUIFeatureTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldAllowFullCrudFunctionalityForAUser() throws Exception {

        User firstUser = new User(
                "user1",
                "userFirst",
                "userLast",
                "email",
                "job"
        );

        firstUser = userRepository.save(firstUser);
        Long firstUserId = firstUser.getId();

        User secondUser = new User(
                "user2",
                "user2First",
                "user2Last",
                "second email",
                "second job"
        );

        secondUser = userRepository.save(secondUser);
        Long secondUserId = secondUser.getId();

        System.setProperty("selenide.browser", "Chrome");

        open("http://localhost:3000");

        // There should only be two users
        $$("[data-user-display]").shouldHave(size(2));

        // Test that all data shows up for each user
        $("#user-" + firstUserId + "-user-name").shouldHave(text("user1"));
        $("#user-" + firstUserId + "-first-name").shouldHave(text("userFirst"));
        $("#user-" + firstUserId + "-last-name").shouldHave(text("userLast"));
        $("#user-" + firstUserId + "-email").shouldHave(text("email"));
        $("#user-" + firstUserId + "-job").shouldHave(text("job"));

        $("#user-" + secondUserId + "-user-name").shouldHave(text("user2"));
        $("#user-" + secondUserId + "-first-name").shouldHave(text("user2First"));
        $("#user-" + secondUserId + "-last-name").shouldHave(text("user2Last"));
        $("#user-" + secondUserId + "-email").shouldHave(text("second email"));
        $("#user-" + secondUserId + "-job").shouldHave(text("second job"));

        // Visit the new user page
        $("#new-user-link").click();

// Make sure the link worked and the form is now showing
        $("#new-user-form").should(appear);

// Add a new user
        $("#new-user-user-name").sendKeys("user3");
        $("#new-user-first-name").sendKeys("user3First");
        $("#new-user-last-name").sendKeys("user3Last");
        $("#new-user-email").sendKeys("third email");
        $("#new-user-job").sendKeys("third job");
        $("#new-user-submit").click();

// Make sure we're now on the users page again
        $("#users-wrapper").should(appear);

// Now there should be three Users
        $$("[data-user-display]").shouldHave(size(3));

        refresh();

// Now there should be three Users again after the refresh
        $$("[data-user-display]").shouldHave(size(3));

// Check that the data is showing up for the third User
        Long thirdUserId = secondUserId + 1;
        $("#user-" + thirdUserId + "-user-name").shouldHave(text("user3"));
        $("#user-" + thirdUserId + "-first-name").shouldHave(text("user3First"));
        $("#user-" + thirdUserId + "-last-name").shouldHave(text("user3Last"));
        $("#user-" + thirdUserId + "-email").shouldHave(text("third email"));
        $("#user-" + thirdUserId + "-job").shouldHave(text("third job"));

        // Test Deleting the first user
        $("#user-" + firstUserId).should(exist);
        $$("[data-user-display]").shouldHave(size(3));

        $("#delete-user-" + firstUserId).click();
        $("#user-" + firstUserId).shouldNot(exist);

        $$("[data-user-display]").shouldHave(size(2));

        refresh();
        
    }
}
