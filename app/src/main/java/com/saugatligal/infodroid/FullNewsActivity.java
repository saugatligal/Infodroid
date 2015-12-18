package com.saugatligal.infodroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import model.News;
import utilities.GlobalClass;

public class FullNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);

        Bundle i = getIntent().getExtras();
        String title = i.getString("title");
     final   String newUrl = i.getString("newsurl");
        String description = i.getString("newsDescription");
        String imageUrl = i.getString("imageurl");

        Log.e("DATA",title+" "+newUrl +" "+description+" "+imageUrl);

        ImageView imageView = (ImageView)findViewById(R.id.full_news_imageid);
        TextView newsView = (TextView)findViewById(R.id.full_news_testid);

        Button newsButton = (Button)findViewById(R.id.webview_id);
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(R.mipmap.logo)
                .showImageOnFail(R.mipmap.logo)
                .showImageOnLoading(R.mipmap.logo).build();



//download and display image from url
        imageLoader.displayImage(imageUrl, imageView, options);
       // imageView.setImageURI(Uri.parse(imageUrl));
      //  imageView.setImageURI(Uri.parse(imageUrl));


        newsView.setText(description);
        newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), WebviewComplete.class);
                i.putExtra("URL", newUrl);

                if(GlobalClass.getInstance().isConnectingToInternet()){
                    startActivity(i);
                }else{
                    Toast.makeText(getApplication(),"No internet connection!!",Toast.LENGTH_LONG).show();
                }


            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_full_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
