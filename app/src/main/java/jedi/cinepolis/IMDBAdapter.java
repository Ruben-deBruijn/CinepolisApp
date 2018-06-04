package jedi.cinepolis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Operator on 3/27/2017.
 */

public class IMDBAdapter extends ArrayAdapter {

    private Context context;

    public IMDBAdapter(Context context, ArrayList<IMDBItem> imdbItems) {
        super(context, 0, imdbItems);

        this.context = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        IMDBItem item = (IMDBItem) getItem(position);

        if( convertView == null ) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.gridview_item, parent, false);
        }
/*
        // Display album name in list
        TextView filmTitel = (TextView) convertView.findViewById(R.id.infoTitel);
        filmTitel.setText(item.getFilmTitel());

        // Spotify
        TextView genre = (TextView) convertView.findViewById(R.id.infoGenre);
        genre.setText("ID : " + item.getFilmGenre());

        TextView datum = (TextView) convertView.findViewById(R.id.infoDatum);
        datum.setText(item.getFilmJaar());

        TextView taal = (TextView) convertView.findViewById(R.id.infoTaal);
        taal.setText(item.getFilmTaal());

        TextView acteurs = (TextView) convertView.findViewById(R.id.infoActeurs);
        acteurs.setText(item.getFilmActeurs());
*/
        ImageView poster = (ImageView) convertView.findViewById(R.id.gvThumb);
        Picasso.with(context).load(item.filmPosterUrl).into(poster);



        //ImageView bg = (ImageView) convertView.findViewById(com.example.daniel.spotifyTentamen.R.id.albumRowImageViewBG);
        //Picasso.with(context).load(item.albumImageUrl).into(bg);

        /* TextView rd = (TextView) convertView.findViewById(R.id.albumReleaseDate);
        rd.setText(item.getReleaseDate()); */


        return convertView;
    }
}
