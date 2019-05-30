package net.itgoo.componentization.application;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 17/1/3.
 */

public class CZApplicationHelper {

    private static List<CZApplicationInitializer> sApplications = new ArrayList<>();

    public static void register(CZApplicationCreator applicationCreator) {
        List<Class<? extends CZApplicationInitializer>> list = new ArrayList<>();
        applicationCreator.init(list);
        for (Class<? extends CZApplicationInitializer> cls : list) {
            try {
                sApplications.add(cls.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void initApplications(Context context) {
        for (CZApplicationInitializer applicationInitializer : sApplications) {
            applicationInitializer.init(context);
        }
    }

    public static void deInitApplications(Context context) {
        for (CZApplicationInitializer applicationInitializer : sApplications) {
            applicationInitializer.deInit(context);
        }
    }

    public static void attachApplications(Context context) {
        for (CZApplicationInitializer applicationInitializer : sApplications) {
            applicationInitializer.attach(context);
        }
    }
}
