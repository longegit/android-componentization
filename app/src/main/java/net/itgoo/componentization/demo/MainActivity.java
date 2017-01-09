package net.itgoo.componentization.demo;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import net.itgoo.componentization.fragment.CZFragmentEntity;
import net.itgoo.componentization.fragment.CZFragmentHelper;
import net.itgoo.tabbadgeview.TabBadgeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabBar();
    }

    /**
     * 初始化TabBar
     */
    private void initTabBar() {
        FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.fragment);
        tabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);

        String[] names = {"modulea", "moduleb"};

        for (String name : names) {
            CZFragmentEntity entity = CZFragmentHelper.getFragment(name);
            int stringID = CZFragmentHelper.getTitleId(this, name);
            int drawableID = CZFragmentHelper.getIconId(this, name);

            TabHost.TabSpec tabSpec = tabHost.newTabSpec(name).setIndicator(new TabBadgeView(this, name, drawableID, stringID));
            tabHost.addTab(tabSpec, entity.getFragment(), null);
        }
    }
}
