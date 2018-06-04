package jedi.cinepolis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Operator on 3/28/2017.
 */

public class GenreAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<String> genres;
    private ArrayList<Integer> mThumbIds;

    public GenreAdapter(Context c, ArrayList<String> genres, ArrayList<Integer> mThumbIds) {
        mContext = c;
        this.genres = genres;
        this.mThumbIds = mThumbIds;
    }

//    public int getCount() {
//        return mThumbIds.length;
//    }
    public int getCount() {
        return genres.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listView;

        if (convertView == null) {

            listView = new View(mContext);

            // get layout from text_item.xml
            listView = inflater.inflate(R.layout.genreview_item, null);

            // set value into textview
            ImageView imageview = (ImageView) listView
                    .findViewById(R.id.genreBg);
            imageview.setImageResource(mThumbIds.get(position));

            TextView texteview = (TextView) listView.findViewById(R.id.genreTxt);
            texteview.setText(genres.get(position));

            TextView genreIdTv = (TextView) listView.findViewById(R.id.genreId);
            genreIdTv.setText(genres.get(position));

        } else {
            listView = (View) convertView;
        }

        return listView;
    }



    // references to our images
//    private Integer[] mThumbIds = {
//            R.drawable.beauty_and_the_beast, R.drawable.get_out,
//            R.drawable.kong_skull_island, R.drawable.lego_batman_movie,
//            R.drawable.logan, R.drawable.shack,
//            R.drawable.power, R.drawable.fist_fight, R.drawable.construction
//    };

//    // references to our images
//    private String[] mGenres = {
//            "Actie", "Avontuur",
//            "Comedy", "Drama",
//            "Fantasie", "Horror",
//            "Misdaad", "Romantiek"
//    };
//
//    private String[] mGenreIds = {
//            "Actie", "Avontuur",
//            "Comedy", "Drama",
//            "Fantasie", "27",
//            "Misdaad", "Romantiek"
//    };


}


