package com.saugatligal.infodroid;

import android.app.*;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;


import apptext.AppText;
import fragment.HealthnewsFragment;
import fragment.SportnewsFragment;
import transforms.*;
import utilities.GlobalClass;


/**
 * A simple {@link } subclass.
 * Activities that contain this fragment must implement the
 * {@link MainNewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainNewsFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ViewPager mPager;
    private PageAdapter mAdapter;
    private static final ArrayList<TransformerItem> TRANSFORM_CLASSES;
    TextView titleText;
    String[] title = {"WORLD NEWS","SPORTS NEWS","ENTERTAINMENT","FEMAIL","SCIENCE AND TECHNOLOGY","HEALTH NEWS"};
    static {
        TRANSFORM_CLASSES = new ArrayList<>();
        TRANSFORM_CLASSES.add(new TransformerItem(DefaultTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(AccordionTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(BackgroundToForegroundTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(CubeInTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(CubeOutTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(DepthPageTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(FlipHorizontalTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(FlipVerticalTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(ForegroundToBackgroundTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(RotateDownTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(RotateUpTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(ScaleInOutTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(StackTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(TabletTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(ZoomInTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(ZoomOutSlideTransformer.class));
        TRANSFORM_CLASSES.add(new TransformerItem(ZoomOutTranformer.class));
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainNewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainNewsFragment newInstance(String param1, String param2) {
        MainNewsFragment fragment = new MainNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MainNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View hiddenInfo = inflater.inflate(R.layout.fragment_main_news, container, false);

        titleText = (TextView) hiddenInfo.findViewById(R.id.title_text_id);
        mPager = (ViewPager) hiddenInfo.findViewById(R.id.fragmentcontainer);



        AdView mAdView = (AdView)hiddenInfo.findViewById(R.id.adView);

      //  mAdView.setAdSize(AdSize.BANNER);
      //  AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(GlobalClass.getInstance().getDeviceId())
                .build());


        //  return inflater.inflate(R.layout.fragment_main_news, container, false);
        return hiddenInfo;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
// TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        if(GlobalClass.getInstance().isConnectingToInternet()){
            setupViewPager(mPager);
        }else{
            titleText.setText("NO INTERNET CONNECTION");
            Toast.makeText(getActivity(),"No internet connection",Toast.LENGTH_LONG).show();
        }


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }



    private static final class PageAdapter extends FragmentPagerAdapter {
        List<android.support.v4.app.Fragment> fragmentList = new ArrayList<>();
        public PageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return   fragmentList.get(position);








        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String name) {
            fragmentList.add(fragment);

        }

    }

    private static final class TransformerItem {

        final String title;
        final Class<? extends ViewPager.PageTransformer> clazz;

        public TransformerItem(Class<? extends ViewPager.PageTransformer> clazz) {
            this.clazz = clazz;
            title = clazz.getSimpleName();
        }

        @Override
        public String toString() {
            return title;
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        PageAdapter viewPagerAdapter = new PageAdapter(getChildFragmentManager());



        viewPagerAdapter.addFragment(HealthnewsFragment.newInstance(AppText.worldNewsLink,"World News"),"");
        viewPagerAdapter.addFragment(HealthnewsFragment.newInstance(AppText.worldSportsLink,"Sports News"),"");
        viewPagerAdapter.addFragment(HealthnewsFragment.newInstance(AppText.worldEntertainmentLink,"Entertainment News"),"");
        viewPagerAdapter.addFragment(HealthnewsFragment.newInstance(AppText.worldFemailLink,"Femail News"),"");
        viewPagerAdapter.addFragment(HealthnewsFragment.newInstance(AppText.worldScienceLink,"Science News"),"");
        viewPagerAdapter.addFragment(HealthnewsFragment.newInstance(AppText.worldHealthLink,"Health News"),"");


        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                titleText.setText(title[position]);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        try {
            mPager.setPageTransformer(true, TRANSFORM_CLASSES.get(13).clazz.newInstance());
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        viewPager.setAdapter(viewPagerAdapter);
    }

}
