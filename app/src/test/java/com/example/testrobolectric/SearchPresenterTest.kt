package com.example.testrobolectric

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testrobolectric.view.details.DetailsActivity
import com.example.testrobolectric.view.search.MainActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class SearchPresenterTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var context: Context

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityButton_NotNull() {
        scenario.onActivity {
            val toDetailsButton = it.findViewById<Button>(R.id.toDetailsActivityButton)
            TestCase.assertNotNull(toDetailsButton)
        }
    }

    @Test
    fun activityButton_IsVisible() {
        scenario.onActivity {
            val toDetailsButton = it.findViewById<Button>(R.id.toDetailsActivityButton)
            TestCase.assertEquals(View.VISIBLE, toDetailsButton.visibility)
        }
    }

    @Test
    fun activityButtons_AreVisible() {
        scenario.onActivity {
            TestCase.assertTrue(it.isPresenterAttached()) // Не придумал, как проверить, не распривачивая важные поля
        }
    }

    @Test
    fun activityCreateIntent_NotNull() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val intent = DetailsActivity.getIntent(context, 0)
        assertNotNull(intent)
    }

    @Test
    fun activityCreateIntent_HasExtras() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val intent = DetailsActivity.getIntent(context, 0)
        val bundle = intent.extras
        assertNotNull(bundle)
    }

    @Test
    fun activityButtonToDetails_IsWorking() {
        scenario.onActivity {
            val toDetailsButton = it.findViewById<Button>(R.id.toDetailsActivityButton)
            toDetailsButton.performClick()
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activityButtonToDetails_Detaching() {
        scenario.onActivity {
            val toDetailsButton = it.findViewById<Button>(R.id.toDetailsActivityButton)
            toDetailsButton.performClick()
            assertFalse(it.isPresenterAttached())
        }
    }

    @Test
    fun activityEditText_isWork() {
        scenario.onActivity {
            val editText = it.findViewById<EditText>(R.id.searchEditText)
            editText.setText("text", TextView.BufferType.EDITABLE)
            assertNotNull(editText.text)
            assertEquals("text", editText.text.toString())
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}
