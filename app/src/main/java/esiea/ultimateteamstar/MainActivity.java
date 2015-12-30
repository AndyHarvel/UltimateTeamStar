package esiea.ultimateteamstar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void buttonOnClick(View v) {

        Button button = (Button) v;
        //((Button) v).setText("GO !");
        Intent intent = new Intent(MainActivity.this, AttaquantDisplayActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.animationtransitionopen1,R.animator.animationtransitionopen2);
    }

    public void buttonOnClick2(View v) {
        Button button2 = (Button) v;
        //((Button) v).setText("GO 2 !");
        Intent intent = new Intent(MainActivity.this, MillieuDisplayActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.animationtransitionopen1,R.animator.animationtransitionopen2);
    }

    public void buttonOnClick3(View v) {
        Button button3 = (Button) v;
        //((Button) v).setText("GO 3!");
        Intent intent = new Intent(MainActivity.this, DefenseurDisplayActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.animationtransitionopen1,R.animator.animationtransitionopen2);

    }

    public void buttonOnClick4(View v) {
        Button button4 = (Button) v;
        //((Button) v).setText("GO 4!");
        Intent intent = new Intent(MainActivity.this, GardienDisplayActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.animationtransitionopen1,R.animator.animationtransitionopen2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            //Ask the user if they want to quit
            AlertDialog show = new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.quit)
                    .setMessage(R.string.really_quit)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //Stop the activity
                            MainActivity.this.finish();
                        }

                    })
                    .setNegativeButton(R.string.no, null)
                    .show();

            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }

    }



}
