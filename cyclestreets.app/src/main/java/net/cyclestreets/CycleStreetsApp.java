package net.cyclestreets;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mikepenz.iconics.IconicsDrawable;
import com.posthog.android.PostHogAndroid;
import com.posthog.android.PostHogAndroidConfig;
import com.posthog.android.replay.PostHogDrawableConverter;
import com.posthog.android.replay.PostHogSessionReplayConfig;

public class CycleStreetsApp extends Application {
    private String POSTHOG_API_KEY = "phc_pQ70jJhZKHRvDIL5ruOErnPy6xiAiWCqlL4ayELj4X8";
    private String POSTHOG_HOST = "https://app.posthog.com";

    @Override
    public void onCreate() {
        super.onCreate();

        var config = new PostHogAndroidConfig(
                this.POSTHOG_API_KEY, this.POSTHOG_HOST,
                true, true, true, new PostHogSessionReplayConfig(
                false, false, true, new PostHogDrawableConverter() {
            @Nullable
            @Override
            public Bitmap convert(@NonNull Drawable drawable) {
                if (drawable instanceof IconicsDrawable) {
                    return ((IconicsDrawable) drawable).toBitmap();
                }
                return null;
            }
        })
        );
        config.setSessionReplay(true);
        config.setDebug(true);
        config.setFlushAt(5);

        PostHogAndroid.Companion.setup(this, config);

        CycleStreetsAppSupport.initialise(this, R.xml.prefs);
    }
}
