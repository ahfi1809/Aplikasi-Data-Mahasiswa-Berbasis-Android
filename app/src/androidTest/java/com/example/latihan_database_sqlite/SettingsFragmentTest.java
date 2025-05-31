package com.example.latihan_database_sqlite;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import com.example.latihan_database_sqlite.presentation.ui.fragment.SettingsFragment;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SettingsFragmentTest {

    @Before
    public void setUp() {
        FragmentScenario.launchInContainer(SettingsFragment.class, null, R.style.Theme_Latihan_database_sqlite);
    }

    @Test
    public void testImageViewDisplayed() {
        onView(withId(R.id.image_light)).check(matches(isDisplayed()));
    }

    @Test
    public void testTextStatusDisplayed() {
        onView(withId(R.id.text_light_status))
                .check(matches(isDisplayed()))
                .check(matches(withText("Mendeteksi cahaya...")));
    }

    @Test
    public void testTextLightValueDisplayed() {
        onView(withId(R.id.text_light_value))
                .check(matches(isDisplayed()))
                .check(matches(withText("Lux: -")));
    }
}
