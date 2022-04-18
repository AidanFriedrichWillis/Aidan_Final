package com.example.finalproject.Views.C;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.finalproject.Application.Token;
import com.example.finalproject.HttpServices.RESTFull_services_user;
import com.example.finalproject.R;
import com.example.finalproject.Views.Signup;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class Z_last {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(Signup.class);
    @Before
    public void setUp() throws Exception {
        RESTFull_services_user rest = new RESTFull_services_user("user");
        rest.deleteRequest();
        Token.deleteToken();
    }
    @Test
    public void CreatingWorkoutFail_Test(){



    }

    @After
    public void tearDown() throws Exception {

    }

}