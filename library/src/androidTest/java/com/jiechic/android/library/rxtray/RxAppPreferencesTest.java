package com.jiechic.android.library.rxtray;

import android.support.test.InstrumentationRegistry;

import org.junit.Before;

/**
 * Created by jiechic on 2017/10/20.
 */

public class RxAppPreferencesTest extends RxTrayPreferencesTest {
    @Before
    public void setup() {
        appContext = InstrumentationRegistry.getTargetContext();
        rxTrayPreferences = new RxAppPreferences(appContext);
    }
}