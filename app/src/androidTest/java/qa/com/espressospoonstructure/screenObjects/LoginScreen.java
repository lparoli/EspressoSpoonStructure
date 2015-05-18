package qa.com.espressospoonstructure.screenObjects;

import android.support.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class LoginScreen {

    /* UI Mapping:
    * Each screen has several UI elements that will be represented as ViewInteraction elements.
    * The locators for these elements should only be defined once and in one place. Here!
    * */

    private static final ViewInteraction USERNAME_EDIT = onView(withId(R.id.login_username));
    private static final ViewInteraction PASSWORD_EDIT = onView(withId(R.id.login_password));
    private static final ViewInteraction LOGIN_BUTTON = onView(withId(R.id.login_button));
    public static final ViewInteraction LOGIN_VALIDATION_USERNAME_INVALID_FORMAT = onView(withId(R.id.login_validation_username));
    public static final ViewInteraction LOGIN_VALIDATION_INVALID_PASSWORD = onView(withId(R.id.login_validation_password));

    /* Modelling basic features:
    * The Login screen allows the user to enter text into the username field.
    * This is the only place that "knows" how to make that happen.
    * */
    public void typeUsername(String username) {
        /*Notice that clearText() implemented before typeText().
        * This is considered to be a 'safe' operation, since in the case there is text already there
        * it will be deleted first, preventing the test from returning a failure for a different scenario.*/
        USERNAME_EDIT.perform(clearText(), typeText(username));
    }

    public void typePassword(String password) {
        PASSWORD_EDIT.perform(clearText(), typeText(password));
    }

    public void clickLoginButton() {
        LOGIN_BUTTON.perform(scrollTo(), click());
    }

    /* Espresso allow us to directly press the IME (Input Method Editor) action button.
    * That's something we should always account for in our tests.
    * You can use pressImeActionButton() for that purpose.
    * */


    /* Login screen allows user to perform login, which is the sum of multiple actions.
    * Notice that this method is not validating whether the login was successful or not.
    * Validations will occur in test classes. Screen Object classes should not have validations.
    * Notice we are reusing our existing functions. Should the way Login is done ever change, we just change
    * it in one single place.
    * */
    public void doLogin(String username, String password) {
        typeUsername(username);
        typePassword(password);
        clickLoginButton();
    }

}
