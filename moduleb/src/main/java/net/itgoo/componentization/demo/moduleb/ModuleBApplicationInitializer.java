package net.itgoo.componentization.demo.moduleb;

import android.content.Context;
import android.util.Log;

import net.itgoo.componentization.application.CZModuleApplicationInitializer;
import net.itgoo.componentization.fragment.CZFragmentHelper;
import net.itgoo.componentization.fragment.ModuleBFragmentInitializer;

/**
 * Created by apple on 17/1/6.
 */
public class ModuleBApplicationInitializer implements CZModuleApplicationInitializer {

    @Override
    public void init(Context context) {
        Log.v("application", "moduleb");
        CZFragmentHelper.register(new ModuleBFragmentInitializer());
    }

    @Override
    public void deInit(Context context) {

    }

    @Override
    public void attach(Context base) {

    }
}
