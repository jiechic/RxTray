package com.jiechic.android.library.rxtray;

import android.content.Context;

/**
 * Created by jiechic on 2017/10/20.
 */

public class RxAppPreferences extends RxTrayPreferences {
    private static final int VERSION = 1;

    public RxAppPreferences(final Context context) {
        super(context, context.getPackageName(), VERSION);
    }
}
