package net.itgoo.componentization.fragment;

/**
 * Created by apple on 17/1/4.
 */

public class CZFragmentEntity {
    private String name;
    private String titleName;
    private String iconName;
    private Class fragment;

    public CZFragmentEntity(String name, String titleName, String iconName, Class fragment) {
        this.name = name;
        this.titleName = titleName;
        this.iconName = iconName;
        this.fragment = fragment;
    }

    public String getName() {
        return name;
    }

    public String getTitleName() {
        return titleName;
    }

    public String getIconName() {
        return iconName;
    }

    public Class getFragment() {
        return fragment;
    }
}
