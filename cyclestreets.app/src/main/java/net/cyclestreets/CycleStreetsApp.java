package net.cyclestreets;

import android.app.Application;

import com.posthog.android.PostHogAndroid;
import com.posthog.android.PostHogAndroidConfig;
import com.posthog.android.replay.PostHogSessionReplayConfig;

public class CycleStreetsApp extends Application {
    private String POSTHOG_API_KEY = "phc_pQ70jJhZKHRvDIL5ruOErnPy6xiAiWCqlL4ayELj4X8";
    private String POSTHOG_HOST = "https://us.posthog.com";

    @Override
    public void onCreate() {
        var config = new PostHogAndroidConfig(
                this.POSTHOG_API_KEY, this.POSTHOG_HOST,
                true, true, true, new PostHogSessionReplayConfig(
                false, false, true)
        );
        config.setSessionReplay(true);
        config.setDebug(true);

        PostHogAndroid.Companion.setup(this, config);

        super.onCreate();

        CycleStreetsAppSupport.initialise(this, R.xml.prefs);
    }
}
