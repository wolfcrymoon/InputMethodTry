import android.app.Activity;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by rob on 2015/7/20.
 */
public class InputMethodTest {
    private static Activity context;
    public static EditText language;
    public static Spinner languageSpinner;

    public static Spinner getLanguageSpinner() {
        return languageSpinner;
    }

    public static void setLanguageSpinner(Spinner languageSpinner) {
        InputMethodTest.languageSpinner = languageSpinner;
    }

}
