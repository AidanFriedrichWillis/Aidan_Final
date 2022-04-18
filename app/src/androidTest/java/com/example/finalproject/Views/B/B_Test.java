package com.example.finalproject.Views.B;

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

public class B_Test {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(Signup.class);
    @Before
    public void setUp() throws Exception {

    }
    @Test
    public void CreatingWorkoutFail_Test(){

        onView(withId(R.id.addExersise1)).perform(click());
        onView(withId(R.id.exersizeNameET)).perform(typeText(""));
        onView(withId(R.id.repsET)).perform(typeText(""));
        onView(withId(R.id.setsET)).perform(typeText("5"));
        onView(withId(R.id.weightET)).perform(typeText("100"));
        closeSoftKeyboard();
        onView(withId(R.id.finishAddBtn)).perform(click());
        onView(withId(R.id.finishAddBtn)).check(matches(isDisplayed()));

    }
    @Test
    public void CreatingWorkoutPass_Test(){
        onView(withId(R.id.addExersise1)).perform(click());
        onView(withId(R.id.exersizeNameET)).perform(typeText("testEx"));
        onView(withId(R.id.repsET)).perform(typeText("5"));
        onView(withId(R.id.setsET)).perform(typeText("5"));
        onView(withId(R.id.weightET)).perform(typeText("100"));
        closeSoftKeyboard();
        onView(withId(R.id.finishAddBtn)).perform(click());
        onView(withId(R.id.addExersise1)).check(matches(isDisplayed()));
    }

    @Test
    public void SavingWorkoutFail_Test(){
        onView(withId(R.id.workoutnameET)).perform(typeText(String.valueOf(R.string.BAD_LONG_String)));
        onView(withId(R.id.finishworkoutBTN1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.addExersise1)).check(matches(isDisplayed()));
    }

    @Test
    public void SavingWorkoutPass_Test(){
        onView(withId(R.id.workoutnameET)).perform(typeText("THIS NAME IS JUST RIGHT"));
        onView(withId(R.id.finishworkoutBTN1)).perform(click());
        closeSoftKeyboard();
        onView(withId(R.id.addExersise1)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {

    }

}