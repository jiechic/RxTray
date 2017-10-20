package com.jiechic.android.library.rxtray;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import net.grandcentrix.tray.core.ItemNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jiechic on 2017/10/20.
 */
@RunWith(AndroidJUnit4.class)
public class RxTrayPreferencesTest {

    Context appContext = null;

    RxTrayPreferences rxTrayPreferences = null;

    @Before
    public void setup() {
        appContext = InstrumentationRegistry.getTargetContext();
        rxTrayPreferences = new RxTrayPreferences(appContext, appContext.getPackageName(), 1);
    }

    @Test
    public void getStringObservable() throws Exception {

        final String key = "stringkey";
        TestObserver<String> testObservable = TestObserver.create();
        rxTrayPreferences.remove(key);
        rxTrayPreferences.getStringObservable(key).subscribe(testObservable);
        testObservable.assertError(ItemNotFoundException.class);

        testObservable = TestObserver.create();
        rxTrayPreferences.put(key, key);
        rxTrayPreferences.getStringObservable(key).subscribe(testObservable);
        testObservable.assertNoErrors();
        testObservable.assertNotComplete();
    }

    @Test
    public void getStringObservable1() throws Exception {
        final String key = "stringkey";
        final String defaultValue = "defaultValue";
        TestObserver<String> testObservable = TestObserver.create();
        rxTrayPreferences.remove(key);
        rxTrayPreferences.getStringObservable(key, defaultValue)
                .subscribeOn(Schedulers.io())
                .subscribe(testObservable);
        Thread.sleep(1000);
        testObservable.assertNoErrors();
        rxTrayPreferences.put(key, key);
        Thread.sleep(1000);
        rxTrayPreferences.put(key, key);
        Thread.sleep(1000);
        rxTrayPreferences.put(key, defaultValue);
        Thread.sleep(1000);
        testObservable.assertNoErrors();
        for (String string : testObservable.values()) {
            System.out.println(string);
        }
        testObservable.assertValueCount(3);
        testObservable.assertNotComplete();
    }

    @Test
    public void getIntObservable() throws Exception {

    }

    @Test
    public void getIntObservable1() throws Exception {

    }

    @Test
    public void getFloatObservable() throws Exception {

    }

    @Test
    public void getFloatObservable1() throws Exception {

    }

    @Test
    public void getlongObservable() throws Exception {

    }

    @Test
    public void getLongObservable() throws Exception {

    }

    @Test
    public void getBooleanObservable() throws Exception {

    }

    @Test
    public void getBooleanObservable1() throws Exception {

    }

}