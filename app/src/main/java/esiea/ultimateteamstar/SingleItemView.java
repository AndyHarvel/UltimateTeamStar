package esiea.ultimateteamstar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Jalil on 30/12/2015.
 */
public class SingleItemView extends Activity {

    String id;
    String nom;
    String description;
    String image;
    String position;
    ImageLoader imageLoader = new ImageLoader(this);
    ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview);

        Intent i = getIntent();
        id = i.getStringExtra("id");
        nom = i.getStringExtra("nom");
        description = i.getStringExtra("description");
        image = i.getStringExtra("image");

        // Locate the TextViews in singleitemview.xml
        TextView txtid = (TextView) findViewById(R.id.id);
        TextView txtnom = (TextView) findViewById(R.id.nom);
        TextView txtdescription = (TextView) findViewById(R.id.description);

        // Locate the ImageView in singleitemview.xml
        ImageView imgimage = (ImageView) findViewById(R.id.image);

        /* Set results to the TextViews */
        txtid.setText(id);
        txtnom.setText(nom);
        txtdescription.setText(description);

        imageLoader.DisplayImage(image, imgimage);

        /* -----------  ------------- ---------- Jalil */

        mProgressDialog = new ProgressDialog(SingleItemView.this);
        mProgressDialog.setMessage("Téléchargement de l'image...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        DownloadFile downloadFile = new DownloadFile();
        downloadFile.execute("http://ultimate-team-star.16mb.com/images/"+id+".png");



    }

    private class DownloadFile extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... sUrl) {
            try {
                URL url = new URL(sUrl[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // this will be useful so that you can show a typical 0-100% progress bar
                int fileLength = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream("/sdcard/Download/"+id+".png");

                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // Progression de telechargement - Jalil */
                    publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
            }
            return null;
        }


        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }


        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            mProgressDialog.setProgress(progress[0]);
        }

    }




    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // Fermeture de cette activity
            finish();
            // Lancement de l'animation
            overridePendingTransition(R.animator.animationtransitionclose1,R.animator.animationtransitionclose2);
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}
