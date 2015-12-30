package esiea.ultimateteamstar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jalil on 08/12/2015.
 */
public class DefenseurDisplayActivity extends Activity {
    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    static String id = "id";
    static String nom = "nom";
    static String description = "description";
    static String image = "image";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.defenseur_display);
        new DownloadJSONd().execute();
    }

    // DownloadJSON AsyncTask
    private class DownloadJSONd extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(DefenseurDisplayActivity.this);
            mProgressDialog.setTitle("Android JSON Parse");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            arraylist = new ArrayList<HashMap<String, String>>();
            jsonobject = JSONfunctions
                    .getJSONfromURL("http://ultimate-team-star.16mb.com/jsonutsd.txt");

            try {
                jsonarray = jsonobject.getJSONArray("worldfootballeur");

                for (int i = 0; i < jsonarray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);
                    map.put("id", jsonobject.getString("id"));
                    map.put("nom", jsonobject.getString("nom"));
                    map.put("description", jsonobject.getString("description"));
                    map.put("image", jsonobject.getString("image"));
                    arraylist.add(map);
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            listview = (ListView) findViewById(R.id.listview);
            adapter = new ListViewAdapter(DefenseurDisplayActivity.this, arraylist);
            listview.setAdapter(adapter);
            mProgressDialog.dismiss();
        }
    }



    public void buttonOnClickhelp(View v) {
        Button button = (Button) v;


        //ce code génère un TOAST - Jalil !
        Context context = getApplicationContext();
        CharSequence text= "Voici une sélection de defenseurs internationaux, vous avez la possibilité de télécharger les images de ces footballeurs en leur cliquant dessus !";
        int dureetoast = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context,text,dureetoast);
        toast.show();

    }

    /* on gère le bouton retour sur cette page pour faire un retour sur l'activity principal - Jalil */
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // Fermeture de cette activity
            finish();
            /* Lancement de l'animation de fermeture - Jalil */
            overridePendingTransition(R.animator.animationtransitionclose1,R.animator.animationtransitionclose2);
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

}
