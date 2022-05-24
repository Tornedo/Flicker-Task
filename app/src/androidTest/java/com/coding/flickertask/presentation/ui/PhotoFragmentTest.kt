package com.coding.flickertask.presentation.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.coding.flickertask.R
import org.junit.Rule
import org.junit.Test


class PhotoFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun check_List_View_Is_There_When_Application_Start() {
         onView(withId(R.id.list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun check_Search_Query_Upon_Seach_Button_Clicked() {
        onView(withId(R.id.searchInputText)).perform(ViewActions.typeText("hello"))
        onView(withId(R.id.search_button)).perform(click())
        Espresso.closeSoftKeyboard()
        onView(withText("hello")).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun check_AutoComplete_Item_Clicked() {
        onView(withId(R.id.searchInputText)).perform(ViewActions.typeText("qwerty" ))
        onView(withId(R.id.search_button)).perform(click())
        onView(withId(R.id.searchInputText)).perform(ViewActions.replaceText("qwe" ))
        onView(withText("qwerty"))
            .inRoot(RootMatchers.isPlatformPopup())
            .perform(click())
    }

    @Test
    fun check_Loading_Indication_On_Search_Button_Clicked() {
        onView(withId(R.id.searchInputText)).perform(ViewActions.typeText("hello" ))
        onView(withId(R.id.search_button)).perform(click())
        onView(withId(R.id.pullToRefresh))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}