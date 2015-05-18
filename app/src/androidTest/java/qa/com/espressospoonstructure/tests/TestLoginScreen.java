package qa.com.espressospoonstructure.tests;

import org.junit.Test;

import qa.com.espressospoonstructure.screenObjects.LoginScreen;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

public class TestLoginScreen extends BaseTestCase<MainActivity> {
    /* Create the variable that will contain the instance of our Screen Object class*/
    private LoginScreen mLoginScreen;

    /* Create our instance of Login Screen and any other screen object we might require.
    * Normally, you'll have to go thorugh several screens before running a test.*/
    public TestLoginScreen(){
        super(MainActivity.class);
        mLoginScreen = new LoginScreen();
    }

    /* This example doesn't hold one, but it's normal to have a @Before method that navigates to the screen
    * before executing it. Being able to use that annotation is one of the advantages of using JUnit4*/

    /* All tests have the @Test annotation.
    * Notice that the method name describes exactly the expected behavior for the specified action.
    * Also notice that the assertion is performed here and it does not rely on the Screen Object class.
    * */
    @Test
    public void shouldDisplayErrorWhenSubmittingInvalidPassword(){
        mLoginScreen.doLogin("ValidUsername", "InvalidPassword");
        LoginScreen.LOGIN_VALIDATION_INVALID_PASSWORD.check(matches(isDisplayed()));
    }

    /* Notice that we reuse method 'doLogin'.
    * Difference lies on the test data we use and the assertion implemented.
    * */
    @Test
    public void shouldDisplayErrorWhenSubmittingInvalidUsernameFormat(){
        mLoginScreen.doLogin("InvalidUsername", "ValidPasswordFormat");
        mLoginScreen.LOGIN_VALIDATION_USERNAME_INVALID_FORMAT.check(matches(isDisplayed()));
    }
}
