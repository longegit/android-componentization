package net.itgoo.componentization.demo;

import android.app.Application;
import android.content.Context;

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

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        CZApplicationHelper.attachApplications(base);
    }
}
