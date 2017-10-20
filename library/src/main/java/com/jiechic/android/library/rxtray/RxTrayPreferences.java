package com.jiechic.android.library.rxtray;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import net.grandcentrix.tray.TrayPreferences;
import net.grandcentrix.tray.core.ItemNotFoundException;
import net.grandcentrix.tray.core.OnTrayPreferenceChangeListener;
import net.grandcentrix.tray.core.TrayItem;
import net.grandcentrix.tray.core.TrayStorage;

import java.util.Collection;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Function;

/**
 * Created by jiechic on 2017/10/20.
 */

public class RxTrayPreferences extends TrayPreferences {
    public RxTrayPreferences(@NonNull Context context, @NonNull String module, int version, TrayStorage.Type type) {
        super(context, module, version, type);
    }


    public RxTrayPreferences(@NonNull Context context, @NonNull String module, int version) {
        super(context, module, version);
    }

    public Observable<String> getStringObservable(@NonNull final String key) {
        return createObservable(key, new Query<String>() {
            @Override
            String run() throws ItemNotFoundException {
                return getString(key);
            }
        });
    }

    public Observable<String> getStringObservable(@NonNull final String key, @Nullable final String defaultValue) {
        return createObservable(key, new Query<String>() {
            @Override
            String run() throws ItemNotFoundException {
                return getString(key, defaultValue);
            }
        });
    }

    public Observable<Integer> getIntObservable(@NonNull final String key) {
        return createObservable(key, new Query<Integer>() {
            @Override
            Integer run() throws ItemNotFoundException {
                return getInt(key);
            }
        });
    }

    public Observable<Integer> getIntObservable(@NonNull final String key, @Nullable final int defaultValue) {
        return createObservable(key, new Query<Integer>() {
            @Override
            Integer run() throws ItemNotFoundException {
                return getInt(key, defaultValue);
            }
        });
    }

    public Observable<Float> getFloatObservable(@NonNull final String key) {
        return createObservable(key, new Query<Float>() {
            @Override
            Float run() throws ItemNotFoundException {
                return getFloat(key);
            }
        });
    }

    public Observable<Float> getFloatObservable(@NonNull final String key, @Nullable final float defaultValue) {
        return createObservable(key, new Query<Float>() {
            @Override
            Float run() throws ItemNotFoundException {
                return getFloat(key, defaultValue);
            }
        });
    }

    public Observable<Long> getlongObservable(@NonNull final String key) {
        return createObservable(key, new Query<Long>() {
            @Override
            Long run() throws ItemNotFoundException {
                return getLong(key);
            }
        });
    }

    public Observable<Long> getLongObservable(@NonNull final String key, @Nullable final long defaultValue) {
        return createObservable(key, new Query<Long>() {
            @Override
            Long run() throws ItemNotFoundException {
                return getLong(key, defaultValue);
            }
        });
    }

    public Observable<Boolean> getBooleanObservable(@NonNull final String key) {
        return createObservable(key, new Query<Boolean>() {
            @Override
            Boolean run() throws ItemNotFoundException {
                return getBoolean(key);
            }
        });
    }

    public Observable<Boolean> getBooleanObservable(@NonNull final String key, @Nullable final boolean defaultValue) {
        return createObservable(key, new Query<Boolean>() {
            @Override
            Boolean run() throws ItemNotFoundException {
                return getBoolean(key, defaultValue);
            }
        });
    }

    private <T> Observable<T> createObservable(@NonNull final String key, final Query<T> query) {
        return Observable
                .create(new ObservableOnSubscribe<Query<T>>() {
                    @Override
                    public void subscribe(@io.reactivex.annotations.NonNull final ObservableEmitter<Query<T>> e) throws Exception {
                        final OnTrayPreferenceChangeListener listener = new OnTrayPreferenceChangeListener() {
                            @Override
                            public void onTrayPreferenceChanged(Collection<TrayItem> items) {
                                for (TrayItem item : items) {
                                    if (TextUtils.equals(key, item.key())) {
                                        e.onNext(query);
                                    }
                                }
                            }
                        };
                        registerOnTrayPreferenceChangeListener(listener);
                        e.setCancellable(new Cancellable() {
                            @Override
                            public void cancel() throws Exception {
                                unregisterOnTrayPreferenceChangeListener(listener);
                            }
                        });
                        e.onNext(query);
                    }
                })
                .map(new Function<Query<T>, T>() {
                    @Override
                    public T apply(@io.reactivex.annotations.NonNull Query<T> query) throws Exception {
                        return query.run();
                    }
                })
                .distinctUntilChanged();

    }

    private abstract class Query<T> {
        abstract T run() throws ItemNotFoundException;
    }
}
