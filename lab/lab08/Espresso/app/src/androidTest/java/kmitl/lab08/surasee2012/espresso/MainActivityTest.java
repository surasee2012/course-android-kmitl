package kmitl.lab08.surasee2012.espresso;


import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.base.Default;
import android.support.test.espresso.core.deps.guava.annotations.Beta;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.project.demorecord.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Before
    public void start() {
        File root = InstrumentationRegistry.getTargetContext().getFilesDir().getParentFile();
        String[] sharedPreferencesFileNames = new File(root, "shared_prefs").list();
        for (String fileName : sharedPreferencesFileNames) {
            InstrumentationRegistry.getTargetContext().getSharedPreferences(fileName.replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit();
        }

        mActivityTestRule.launchActivity(new Intent());
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Test
    public void TestCase1() {
        /*
        input Name = null, Age = null then press "ADDED" button
        if true app will have text popup and text is "Please Enter user info"
        */
        onView(withId(R.id.editTExtName)).perform(replaceText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText(""), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }

    @Test
    public void TestCase2() {
        /*
        input Name = null, Age = 20 then press "ADDED" button
        if true app will have text popup and text is "Please Enter user info"
        */
        onView(withId(R.id.editTExtName)).perform(replaceText(""), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }

    @Test
    public void TestCase3() {
        /*
        input Name = null, Age = null then press "GO TO LIST" button
        if true view will change to new view and have only one text and text is "Not Found"
        */
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        onView(withText("Not Found")).check(matches(isDisplayed()));
    }

    @Test
    public void TestCase4() {
        /*
        input Name = Ying, Age = null then press "ADDED" button
        if true app will have text popup and text is "Please Enter user info"
        */
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText(""), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }

    @Test
    public void TestCase5() {
        /*
        input Name = Ying, Age = 20 then press "ADDED" button then press "GO TO LIST" button
        if true view will change to new view and have "Name : Ying\nAge : 20" on 1st listview"
        */

        // add Name = Ying, Age = 20
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // click "GO TO LIST" button and check test condition
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textName)).check(matches(withText("Ying")));
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textAge)).check(matches(withText("20")));
    }

    @Test
    public void TestCase6() {
        /*
        input Name = Ladarat, Age = 20 then press "ADDED" button then press "GO TO LIST" button
        if true view will change to new view and have "Name : Ladarat\nAge : 20" on 2nd listview"
        */

        // add Name = Ying, Age = 20
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // add Name = Ladarat, Age = 20
        onView(withId(R.id.editTExtName)).perform(replaceText("Ladarat"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // click "GO TO LIST" button and check test condition
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textName)).check(matches(withText("Ladarat")));
        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textAge)).check(matches(withText("20")));
    }

    @Test
    public void TestCase7() {
        /*
        input Name = Somkait, Age = 80 then press "ADDED" button then press "GO TO LIST" button
        if true view will change to new view and have "Name : Somkait\nAge : 80" on 3rd listview"
        */

        // add Name = Ying, Age = 20
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // add Name = Ladarat, Age = 20
        onView(withId(R.id.editTExtName)).perform(replaceText("Ladarat"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // add Name = Somkait, Age = 80
        onView(withId(R.id.editTExtName)).perform(replaceText("Somkait"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("80"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // click "GO TO LIST" button and check test condition
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textName)).check(matches(withText("Somkait")));
        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textAge)).check(matches(withText("80")));
    }

    @Test
    public void TestCase8() {
        /*
        input Name = Prayoch, Age = 60 then press "ADDED" button then press "GO TO LIST" button
        if true view will change to new view and have "Name : Prayoch\nAge : 60" on 4th listview"
        */

        // add Name = Ying, Age = 20
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // add Name = Ladarat, Age = 20
        onView(withId(R.id.editTExtName)).perform(replaceText("Ladarat"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // add Name = Somkait, Age = 80
        onView(withId(R.id.editTExtName)).perform(replaceText("Somkait"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("80"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // add Name = Prayoch, Age = 60
        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("60"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // click "GO TO LIST" button and check test condition
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textName)).check(matches(withText("Prayoch")));
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textAge)).check(matches(withText("60")));
    }

    @Test
    public void TestCase9() {
        /*
        input Name = Prayoch, Age = 50 then press "ADDED" button then press "GO TO LIST" button
        if true view will change to new view and have "Name : Prayoch\nAge : 50" on 5th listview"
        */

        // add Name = Ying, Age = 20
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // add Name = Ladarat, Age = 20
        onView(withId(R.id.editTExtName)).perform(replaceText("Ladarat"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // add Name = Somkait, Age = 80
        onView(withId(R.id.editTExtName)).perform(replaceText("Somkait"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("80"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // add Name = Prayoch, Age = 60
        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("60"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // add Name = Prayoch, Age = 50
        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("50"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        // click "GO TO LIST" button and check test condition
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        onView(withRecyclerView(R.id.list).atPositionOnView(4, R.id.textName)).check(matches(withText("Prayoch")));
        onView(withRecyclerView(R.id.list).atPositionOnView(4, R.id.textAge)).check(matches(withText("50")));
    }
}
