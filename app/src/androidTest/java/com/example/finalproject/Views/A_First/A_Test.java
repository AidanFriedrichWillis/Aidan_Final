package com.example.finalproject.Views.A_First;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.app.Activity;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.finalproject.Application.Token;
import com.example.finalproject.HttpServices.RESTFull_services_user;
import com.example.finalproject.R;
import com.example.finalproject.Views.Signup;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class A_Test {
    @Rule
    public ActivityScenarioRule  rule = new ActivityScenarioRule<>(Signup.class);

    /**
     * Set up for testing in the login UI test.
     * @throws Exception When failed
     */
    @Before
    public void setUp() throws Exception {
        Token.deleteToken();
    }

    /**
     * Fails test simulates a failed login
     */
    @Test
    public void FailingSignUp_Test(){

        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.fullnameET)).perform(typeText(""));
        onView(withId(R.id.emailET)).perform(typeText(""));
        onView(withId(R.id.usernameET)).perform(typeText(""));
        onView(withId(R.id.passwordET)).perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.signupBTN)).perform(click());
        onView(withId(R.id.emailET)).check(matches(isDisplayed()));
    }

    /**
     * Pass test with legit data.
     */
    @Test
    public void PassSignUp_Test(){
        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.fullnameET)).perform(typeText("nameTest"));
        onView(withId(R.id.emailET)).perform(typeText("emailTest"));
        onView(withId(R.id.usernameET)).perform(typeText("userNameTest"));
        onView(withId(R.id.passwordET)).perform(typeText("passwordTest"));
        closeSoftKeyboard();
        onView(withId(R.id.signupBTN)).perform(click());
        onView(withId(R.id.usernamesET)).check(matches(isDisplayed()));
        onView(withId(R.id.usernamesET)).perform(typeText("userNameTest"));
        onView(withId(R.id.passwordET)).perform(typeText("passwordTest"));
        closeSoftKeyboard();
        onView(withId(R.id.signinBTN)).perform(click());
        onView(withId(R.id.addExersise1)).check(matches(isDisplayed()));

    }



    @After
    public void tearDown() throws Exception {

    }


}