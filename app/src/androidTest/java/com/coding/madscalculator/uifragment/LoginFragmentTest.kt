package com.coding.madscalculator.uifragment

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.coding.madscalculator.MainActivity
import com.coding.madscalculator.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LoginFragmentTest {

    @Test
    fun testLoginFragmentNavigation() {
        val activity=ActivityScenario.launch(MainActivity::class.java)
        //Blank Credentials
        testLoginFragment()
        testCalculatorFragment()
    }

    private fun testLoginFragment() {
        onView(withId(R.id.user_name_email)).perform(typeText(""))
        onView(withId(R.id.user_password)).perform(typeText(""))
        onView(withId(R.id.login_button)).perform(click())


        //Wrong Credentials Credentials -1
        resetText()
        onView(withId(R.id.user_name_email)).perform(typeText("krishanakant7495@gmail.com"))
        pressBack()
        Thread.sleep(400)
        onView(withId(R.id.user_password)).perform(typeText("12345"))
        pressBack()
        onView(withId(R.id.login_button)).perform(click())

        //Wrong Credentials Credentials -2
        resetText()
        onView(withId(R.id.user_name_email)).perform(typeText("krishanakant7495@gmail.com"))
        pressBack()
        onView(withId(R.id.user_password)).perform(typeText("123456789"))
        pressBack()
        onView(withId(R.id.login_button)).perform(click())

        //write Credentials
        resetText()
        onView(withId(R.id.user_name_email)).perform(typeText("krishnakant7495@gmail.com"))
        pressBack()
        onView(withId(R.id.user_password)).perform(typeText("12345678"))
        pressBack()
        onView(withId(R.id.login_button)).perform(click())
        Thread.sleep(3000)
        //Verify Calculator
        onView(withId(R.id.calculator_fragment)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.user_name_email)).perform()
    }


    private fun testCalculatorFragment() {
        //try Combination 56+92-23
        onView(withId(R.id.n5)).perform(click())
        onView(withId(R.id.n6)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.n9)).perform(click())
        onView(withId(R.id.n2)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.n2)).perform(click())
        onView(withId(R.id.n3)).perform(click())
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.clear)).perform(click())
        Thread.sleep(2000)
        //Combination 20+89/52+52
        onView(withId(R.id.n2)).perform(click())
        onView(withId(R.id.n0)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.n8)).perform(click())
        onView(withId(R.id.n9)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.n5)).perform(click())
        onView(withId(R.id.n2)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.n5)).perform(click())
        onView(withId(R.id.n2)).perform(click())
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.clear)).perform(click())
        Thread.sleep(2000)
        //Combination 50+20/10
        onView(withId(R.id.n5)).perform(click())
        onView(withId(R.id.n0)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.n2)).perform(click())
        onView(withId(R.id.n0)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.n1)).perform(click())
        onView(withId(R.id.n0)).perform(click())
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.clear)).perform(click())
        Thread.sleep(2000)

        //Combination 50/20+5
        onView(withId(R.id.n5)).perform(click())
        onView(withId(R.id.n0)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.n2)).perform(click())
        onView(withId(R.id.n0)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.n5)).perform(click())
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.clear)).perform(click())
        Thread.sleep(2000)

        //Combination 25 -2 * 10
        onView(withId(R.id.n2)).perform(click())
        onView(withId(R.id.n5)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.n2)).perform(click())
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.n1)).perform(click())
        onView(withId(R.id.n0)).perform(click())
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.clear)).perform(click())
        Thread.sleep(2000)

        //Combination 10/2-20
        onView(withId(R.id.n1)).perform(click())
        onView(withId(R.id.n0)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.n2)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.n2)).perform(click())
        onView(withId(R.id.n0)).perform(click())
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.clear)).perform(click())
        Thread.sleep(2000)

        //Combination 10-2-3
        onView(withId(R.id.n1)).perform(click())
        onView(withId(R.id.n0)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.n2)).perform(click())
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.n3)).perform(click())
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.clear)).perform(click())
        Thread.sleep(2000)

        //Combination 10/2/5
        onView(withId(R.id.n1)).perform(click())
        onView(withId(R.id.n0)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.n2)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.n5)).perform(click())
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.clear)).perform(click())
        Thread.sleep(2000)

        //Combination 10/2/4+1
        onView(withId(R.id.n1)).perform(click())
        onView(withId(R.id.n0)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.n2)).perform(click())
        onView(withId(R.id.divide)).perform(click())
        onView(withId(R.id.n4)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.n1)).perform(click())
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.clear)).perform(click())
        Thread.sleep(20000)
    }

    private fun resetText() {
        onView(withId(R.id.user_name_email)).perform(clearText())
        onView(withId(R.id.user_password)).perform(clearText())
    }


}