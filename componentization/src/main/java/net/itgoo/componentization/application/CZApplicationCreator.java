package net.itgoo.componentization.application;

import java.util.List;
import java.util.Map;

/**
 * Created by apple on 17/1/6.
 */

public interface CZApplicationCreator {

    void init(List<Class<? extends CZApplicationInitializer>> applications);
}
