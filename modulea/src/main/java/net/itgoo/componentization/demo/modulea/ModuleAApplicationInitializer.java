package net.itgoo.componentization.demo.modulea;

import android.content.Context;
import android.util.Log;

import net.itgoo.componentization.application.CZModuleApplicationInitializer;
import net.itgoo.componentization.fragment.CZFragmentHelper;
import net.itgoo.componentization.fragment.ModuleAFragmentInitializer;

/**
 * Created by apple on 17/1/6.
 */

public class ModuleAApplicationInitializer implements CZModuleApplicationInitializer {
    @Override
    public void init(Context context) {
        Log.v("application", "modulea");
        CZFragmentHelper.register(new ModuleAFragmentInitializer());
    }

    @Override
    public void deInit(Context context) {

    }

    @Override
    public void attach(Context base) {

    }
}
