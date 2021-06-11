package com.coding.madscalculator.uifragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.coding.madscalculator.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LoginFragmentTest {

    @Test
    fun InvalidCredentialCheck() {
        onView(withId(R.id.user_name_email)).perform(typeText("krishnakant.com"))
        onView(withId(R.id.user_password)).perform(typeText("12345"))
    }

    @Test
    fun loginFunctionalityCheck() {
        onView(withId(R.id.user_name_email)).perform(typeText("krishnakant7495@gmail.com"))
        onView(withId(R.id.user_password)).perform(typeText("12345"))
    }
}