package fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.saugatligal.infodroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.NewsAdapter;
import model.News;
import utilities.GlobalClass;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HealthnewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HealthnewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HealthnewsFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    News news;

    ArrayList<News> newsArrayList = new ArrayList<News>();
    NewsAdapter newsAdapter;
    ListView newsList;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HealthnewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HealthnewsFragment newInstance(String param1, String param2) {
        HealthnewsFragment fragment = new HealthnewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HealthnewsFragment() {
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
       View view = inflater.inflate(R.layout.fragment_healthnews, container, false);
        newsList = (ListView)view.findViewById(R.id.news_listview);
        newsAdapter = new NewsAdapter(getActivity(),newsArrayList);
        newsList.setAdapter(newsAdapter);
return view;
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
       /* try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
        String tag_json_obj = "json_obj_req";

        String url = "https://www.kimonolabs.com/api/37m80m76?apikey=1BUuF7TLyOcPd7RrjGDh0G43fGaYpaPV";



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response)
                        {
                            Log.d("RESPONSE", response.toString());

                            try {
                                String jsonObject = response.getString("results");
                                JSONObject jsonObject1 = new JSONObject(jsonObject);

                                JSONArray jsonArray = jsonObject1.getJSONArray("data");
                                for(int i = 0 ; i<jsonArray.length();i++){
                                    JSONObject c = jsonArray.getJSONObject(i);

                                    //     String id = c.getString("Title");
                                  //  JSONObject titleObject = c.getJSONObject("title");
                                 //   String title = titleObject.getString("text");


                                    JSONObject imageurlObject = c.getJSONObject("image");
                                    String title = imageurlObject.getString("alt");
                                    String imageurl = imageurlObject.getString("src");

                                    //   String desc = c.getString(TAG_ADDRESS);
                                    //  String gender = c.getString(TAG_GENDER);
                                    news= new News(title,imageurl,"hello");
                                    newsArrayList.add(news);
                                }



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.getMessage());
                // hide the progress dialog

            }
        });

// Adding request to request queue
        GlobalClass.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

}
