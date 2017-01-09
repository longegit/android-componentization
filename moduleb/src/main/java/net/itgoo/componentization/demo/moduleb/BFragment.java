package net.itgoo.componentization.demo.moduleb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.itgoo.componentization.annotation.CZFragment;


/**
 * A simple {@link Fragment} subclass.
 */
//@CZFragment(name = "moduleb", titleName = "moduleBTitle", iconName = "tab_b_selector")
public class BFragment extends Fragment {


    public BFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

}
