package jedi.cinepolis;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Operator on 3/27/2017.
 */

public class IMDBTask extends AsyncTask<String, Void, String> {

    // Call back
    private IMDBTask.onIMDBItemAvailable listener = null;



    // Statics
    private static final String TAG = IMDBTask.class.getSimpleName();





    // Constructor, set listener
    public IMDBTask(IMDBTask.onIMDBItemAvailable listener) {
        this.listener = listener;
    }



    @Override
    protected String doInBackground(String... params) {

        InputStream inputStream = null;
        BufferedReader reader = null;
        String urlString = "";

        String response = "";

        for (String url : params) {
            Log.i(TAG, url);
        }

        try {
            URL url = new URL(params[0]);
            URLConnection connection = url.openConnection();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response = reader.readLine().toString();
            String line;
            while ( (line = reader.readLine()) != null ) {
                response += line;
            }

        } catch (MalformedURLException e) {
            Log.e("TAG", e.getLocalizedMessage());
            return null;
        } catch (IOException e) {
            Log.e("TAG", e.getLocalizedMessage());
            return null;
        } catch ( Exception e) {
            Log.e("TAG", e.getLocalizedMessage());
            return null;
        } finally {
            if( reader != null ) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("TAG", e.getLocalizedMessage());
                    return null;
                }
            }
        }

        return response;
    }

    /**
     * onPostExecute verwerkt het resultaat uit de doInBackground methode.
     *
     * @param response
     */
    protected void onPostExecute(String response) {

        Log.i(TAG, "onPostExecute " + response);

        // parse JSON and inform caller
        JSONObject jsonObject;

        try {
            // Top level json object
            jsonObject = new JSONObject(response);
            JSONArray movies = jsonObject.getJSONArray("results");
            // Get all users and start looping


            for(int idx = 0; idx < movies.length(); idx++ )
            {
                // New Spotify item
                IMDBItem item = new IMDBItem();

                // Haal de naam uit de JSON
                String title = movies.getJSONObject(idx).getString("title");
                item.setFilmTitel(title);

                String jaar = movies.getJSONObject(idx).getString("release_date");
                item.setFilmJaar(jaar);

                String rating = movies.getJSONObject(idx).getString("vote_average");
                item.setFilmRating(rating);

                String taal = movies.getJSONObject(idx).getString("original_language");
                item.setFilmTaal(taal);

                String beschrijving = movies.getJSONObject(idx).getString("overview");
                item.setFilmBeschrijving(beschrijving);

                /*
                JSONArray genre = movies.getJSONObject(idx).getJSONArray("genre_ids");
                item.setFilmGenre(genre.getJSONObject(0).toString());
*/


                String poster = movies.getJSONObject(idx).getString("poster_path");
                item.setFilmPosterUrl("https://image.tmdb.org/t/p/w500" + poster);

                String bg = movies.getJSONObject(idx).getString("backdrop_path");
                item.setFilmBg("https://image.tmdb.org/t/p/w500" + bg);

                /*
                JSONArray copyright = tracks.getJSONObject(idx).getJSONArray("copyrights");

                item.setCopyright(copyright.getJSONObject(0).getString("text"));
                /*

                // Haal de image_url van het album op
                /* JSONArray image_urls = tracks.getJSONObject(idx).getJSONArray("images");

                item.setAlbumImageUrl(image_urls.getJSONObject(0).getString("url")); */



                // Geef gevonden item terug via de call back
                listener.onIMDBItemAvailable(item);
            }
        } catch( JSONException ex) {
            Log.e(TAG, "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
    }


    //
    // convert InputStream to String
    //
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    // Call back interface
    interface onIMDBItemAvailable {


        void onIMDBItemAvailable(IMDBItem item);
    }
}

