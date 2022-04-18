package com.example.finalproject.Models;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import org.junit.Test;

import java.util.ArrayList;

public class UserTest {

    @Test
    public void getAllWorkouts(){
        assertThat(User.getWorkouts(),instanceOf(ArrayList.class));
    }

    @Test
    public void getCurrentWorkout() {
        assertThat(User.getCurrentWorkout(),instanceOf(Workout.class));
    }

    @Test
    public void setWorkouts() {
    }

    @Test
    public void allENames() {
    }

    @Test
    public void fromJson() {
    }
}