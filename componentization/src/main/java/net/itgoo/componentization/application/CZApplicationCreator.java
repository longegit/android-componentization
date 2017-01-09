package net.itgoo.componentization.application;

import java.util.Map;

/**
 * Created by apple on 17/1/6.
 */

public interface CZApplicationCreator {

    void init(Map<String, Class<? extends CZApplicationInitializer>> applications);
}
