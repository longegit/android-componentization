package net.itgoo.componentization.demo;

import android.app.Application;
import android.content.Context;

import net.itgoo.componentization.application.CZModuleApplicationHelper;
import net.itgoo.componentization.demo.modulea.ModuleAApplicationInitializer;
import net.itgoo.componentization.demo.moduleb.ModuleBApplicationInitializer;

/**
 * Created by apple on 17/1/9.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CZModuleApplicationHelper.register(new ModuleAApplicationInitializer());
        CZModuleApplicationHelper.register(new ModuleBApplicationInitializer());
        CZModuleApplicationHelper.initApplications(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        CZModuleApplicationHelper.deInitApplications(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        CZModuleApplicationHelper.attachApplications(base);
    }
}
