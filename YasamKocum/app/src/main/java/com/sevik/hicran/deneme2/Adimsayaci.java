package com.sevik.hicran.deneme2;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Adimsayaci extends Fragment{
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public Adimsayaci() {
        // Required empty public constructor
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter2 adapter = new ViewPagerAdapter2(getActivity().getSupportFragmentManager());
        adapter.addFragment(new Fragment3(),"Adım Sayacım");
        adapter.addFragment(new Fragment4(), "Kronometre");
        viewPager.setAdapter(adapter);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_adimsayaci, container, false);

        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout)  rootView.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.mipmap.adimsayacim);
        tabLayout.getTabAt(1).setIcon(R.mipmap.kronometre);

        return rootView;




    }

}
