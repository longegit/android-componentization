package net.itgoo.componentization.demo;

import android.app.Application;

import net.itgoo.componentization.annotation.CZApplication;
import net.itgoo.componentization.application.AppApplicationCreator;
import net.itgoo.componentization.application.CZApplicationHelper;

/**
 * Created by apple on 17/1/9.
 */

@CZApplication(name = "App")
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CZApplicationHelper.register(new AppApplicationCreator());
        CZApplicationHelper.initApplications(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        CZApplicationHelper.deInitApplications(this);
    }
}
