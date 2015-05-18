package qa.com.espressospoonstructure.tests;

import android.app.Activity;
import android.content.Context;
import android.os.PowerManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/* This is the base class for all your tests.
* All general configuration is done here, so no need to duplicate code for each test class.
* This is helpful not only to reduce code duplication, but also to implement useful solutions
* like unlocking the device before running your tests.
* */

@RunWith(AndroidJUnit4.class)
public abstract class BaseTestCase<T extends Activity> extends ActivityInstrumentationTestCase2<T> {
    /* Variables related to the 'locked device' issue addressed below */
    private final long DEFAULT_WAKE_LOCK_DURATION = 60 * 1000;
    private PowerManager.WakeLock wakeLock;

    protected BaseTestCase(Class<T> activityUnderTest) {
        super(activityUnderTest);
    }

    /* Method related to the 'locked device' issue addressed below */
    protected long keepDeviceAwakeForAtLeastMilliseconds() {
        return DEFAULT_WAKE_LOCK_DURATION;
    }


    @Before
    public void setUp() throws Exception {
        super.setUp();

        //Required by JUnit4
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        //Launches your activity
        Activity activity = getActivity();

        // Addresses an issue where if the screen is off or locked Espresso will fail with this error:
        // com.google.android.apps.common.testing.ui.espresso.NoActivityResumedException: No activities in stage RESUMED. Did you forget to launch the activity. (test.getActivity() or similar)?
        // The API docs recommend using LayoutParams.FLAG_TURN_SCREEN_ON, etc, but by the time we
        // have a chance to set them Activity.onCreate() has already been called and the flags will
        // not have an effect on the activity (they must be set prior to calling

        PowerManager pm = (PowerManager) getInstrumentation().getTargetContext().getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, BaseTestCase.class.getSimpleName());
        wakeLock.acquire(keepDeviceAwakeForAtLeastMilliseconds());

    }

    @After
    public void tearDown() throws Exception {
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
        }
        super.tearDown();
    }
}
