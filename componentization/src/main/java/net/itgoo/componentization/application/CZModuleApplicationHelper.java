package net.itgoo.componentization.application;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 17/1/3.
 */

public class CZModuleApplicationHelper {

    private static List<CZModuleApplicationInitializer> sApplications = new ArrayList<>();

    public static void register(CZModuleApplicationInitializer czModuleApplicationInitializer) {
        sApplications.add(czModuleApplicationInitializer);
    }

    public static void initApplications(Context context) {
        for (CZModuleApplicationInitializer applicationInitializer : sApplications) {
            applicationInitializer.init(context);
        }
    }

    public static void deInitApplications(Context context) {
        for (CZModuleApplicationInitializer applicationInitializer : sApplications) {
            applicationInitializer.deInit(context);
        }
    }

    public static void attachApplications(Context context) {
        for (CZModuleApplicationInitializer applicationInitializer : sApplications) {
            applicationInitializer.attach(context);
        }
    }
}
