package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.saugatligal.infodroid.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

import model.News;

/**
 * Created by macmini on 12/14/15.
 */
public class NewsAdapter  extends BaseAdapter{

    Context context;
    ArrayList<News> newsArrayList = new ArrayList<News>() ;
    LayoutInflater inflator ;
    ImageView image;
    TextView title;


    public NewsAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
        inflator =   LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return newsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 //inflater =(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) LayoutInflater
                    .from(context);
            convertView = inflater.inflate(R.layout.listview_news_row, parent, false);
          //  convertView= convertView.inflate(context, R.layout.listview_news_row,parent);
        }
        image = (ImageView)convertView.findViewById(R.id.image_news);
        title = (TextView)convertView.findViewById(R.id.main_news);



        title.setText(newsArrayList.get(position).getNewsTitle());

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(R.mipmap.logo)
                .showImageOnFail(R.mipmap.logo)
                .showImageOnLoading(R.mipmap.logo).build();



//download and display image from url
        imageLoader.displayImage(newsArrayList.get(position).getImageUrl(), image, options);

        return convertView;
    }
}
