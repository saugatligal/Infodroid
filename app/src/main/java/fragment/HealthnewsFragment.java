package fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.saugatligal.infodroid.FullNewsActivity;
import com.saugatligal.infodroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import adapter.NewsAdapter;
import apptext.AppText;
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

        RetrieveFeeds feeds = new RetrieveFeeds();
        feeds.execute();

        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), FullNewsActivity.class);
                i.putExtra("title",newsArrayList.get(position).getNewsTitle());
                i.putExtra("newsurl",newsArrayList.get(position).getNewsUrl());
                i.putExtra("newsDescription",newsArrayList.get(position).getDescription());
                i.putExtra("imageurl",newsArrayList.get(position).getImageUrl());
                startActivity(i);
            }
        });

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


        /*JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
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
        GlobalClass.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);*/


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        newsArrayList.clear();
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

    public class RetrieveFeeds extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            newsArrayList.clear();
        }

        @Override
        protected Object doInBackground(Object[] params) {

            try {

                URL url = new URL(mParam1);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getElementsByTagName("item");

                /** Assign textview array lenght by arraylist size */
                //   name = new TextView[nodeList.getLength()];
                //   website = new TextView[nodeList.getLength()];
                //  category = new TextView[nodeList.getLength()];

                for (int i = 0; i < nodeList.getLength(); i++) {

                    Node node = nodeList.item(i);


                    Element fstElmnt = (Element) node;
                    NodeList nameList = fstElmnt.getElementsByTagName("title");
                    Element nameElement = (Element) nameList.item(0);
                    nameList = nameElement.getChildNodes();



                    Element secondElement = (Element) node;
                    NodeList imageurl = secondElement.getElementsByTagName("media:content");
                    Element imageElement = (Element) imageurl.item(0);
                   // nameList = imageElement.getChildNodes();

                    Element secondElement1 = (Element) node;
                    NodeList nodeDescription = secondElement1.getElementsByTagName("description");
                    Element newsDescription = (Element) nodeDescription.item(0);
                    nodeDescription = newsDescription.getChildNodes();

                    Element secondElement2= (Element) node;

                     NodeList newsUrl = secondElement2.getElementsByTagName("link");
                    Element newsUrlElement = (Element) newsUrl.item(0);
                    newsUrl = newsUrlElement.getChildNodes();

                    String description = ( (Node)nodeDescription.item(0)).getNodeValue();
                    String imageUrl = imageElement.getAttribute("url");
                    String newsTitle = ( (Node)nameList.item(0)).getNodeValue();
                    String newsLinkUrl = ( (Node)newsUrl.item(0)).getNodeValue();
                    Log.d("fulldata",description +" "+ newsLinkUrl);
                    news= new News(newsTitle,imageUrl,description,newsLinkUrl);
                    newsArrayList.add(news);

                }

            } catch (Exception e) {
                System.out.println("XML Pasing Excpetion = " + e);
                Log.e("MESSAGE", e.toString());
            }


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            newsAdapter = new NewsAdapter(getActivity(),newsArrayList);
            newsList.setAdapter(newsAdapter);
        }
    }

}
