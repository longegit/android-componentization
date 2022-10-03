package net.itgoo.componentization.application;

import android.content.Context;

/**
 * Created by apple on 17/1/6.
 */

public interface CZApplicationInitializer {

    void init(Context context);

    void deInit(Context context);

    void attach(Context base);
}
