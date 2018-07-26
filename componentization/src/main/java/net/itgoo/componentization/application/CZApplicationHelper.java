package net.itgoo.componentization.application;

import android.content.Context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by apple on 17/1/3.
 */

public class CZApplicationHelper {
    private static Map<String, Class<? extends CZApplicationInitializer>> sApplications = new HashMap<>();

    public static void register(CZApplicationCreator applicationCreator) {
        applicationCreator.init(sApplications);
    }

    public static CZApplicationInitializer getApplicationCreator(String name) throws IllegalAccessException, InstantiationException {
        if (!sApplications.containsKey(name)) {
            throw new NullPointerException(String.format("application creator %s is not exist", name));
        }
        return sApplications.get(name).newInstance();
    }

    public static void initApplications(Context context) {
        Iterator<Map.Entry<String, Class<? extends CZApplicationInitializer>>> it = sApplications.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Class<? extends CZApplicationInitializer>> entry = (Map.Entry<String, Class<? extends CZApplicationInitializer>>) it.next();
            try {
                CZApplicationInitializer applicationInitializer = entry.getValue().newInstance();
                applicationInitializer.init(context);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deInitApplications(Context context) {
        Iterator<Map.Entry<String, Class<? extends CZApplicationInitializer>>> it = sApplications.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Class<? extends CZApplicationInitializer>> entry = (Map.Entry<String, Class<? extends CZApplicationInitializer>>) it.next();
            try {
                CZApplicationInitializer applicationInitializer = entry.getValue().newInstance();
                applicationInitializer.deInit(context);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
