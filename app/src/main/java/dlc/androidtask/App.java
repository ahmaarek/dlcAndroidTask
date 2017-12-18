package dlc.androidtask;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by ahmedmaarek on 14/12/2017.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/montserrat_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}