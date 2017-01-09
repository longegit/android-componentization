package net.itgoo.componentization.fragment;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 17/1/3.
 */

public class CZFragmentHelper {
    private static Map<String, CZFragmentEntity> sFragments = new HashMap<>();

    public static void register(CZFragmentInitializer fragmentInitializer) {
        fragmentInitializer.init(sFragments);
    }

    public static CZFragmentEntity getFragment(String name) {
        if (!sFragments.containsKey(name)) {
            throw new NullPointerException(String.format("fragment %s is not exist", name));
        }
        return sFragments.get(name);
    }

    public static int getTitleId(Context context, String name) {
        CZFragmentEntity entity = getFragment(name);
        return context.getResources().getIdentifier(entity.getTitleName(), "string", context.getPackageName());
    }

    public static int getIconId(Context context, String name) {
        CZFragmentEntity entity = getFragment(name);
        return context.getResources().getIdentifier(entity.getIconName(), "drawable", context.getPackageName());
    }
}
