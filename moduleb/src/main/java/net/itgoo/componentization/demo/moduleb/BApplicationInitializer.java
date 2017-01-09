package net.itgoo.componentization.demo.moduleb;

import android.content.Context;
import android.util.Log;

import net.itgoo.componentization.application.CZApplicationInitializer;
import net.itgoo.componentization.fragment.CZFragmentHelper;
import net.itgoo.componentization.fragment.ModuleBFragmentInitializer;

/**
 * Created by apple on 17/1/6.
 */

public class BApplicationInitializer implements CZApplicationInitializer {
    @Override
    public void init(Context context) {
        Log.v("application", "moduleb");
        CZFragmentHelper.register(new ModuleBFragmentInitializer());
    }
}
