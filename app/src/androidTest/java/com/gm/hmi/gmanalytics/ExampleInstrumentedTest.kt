package com.gm.hmi.gmanalytics

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented dashboard_overview_list, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under dashboard_overview_list.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.gm.hmi.gmanalytics", appContext.packageName)
    }
}
