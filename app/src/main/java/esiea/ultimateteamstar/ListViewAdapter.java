package esiea.ultimateteamstar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jalil on 30/12/2015.
 */
public class ListViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView id;
        TextView nom;
        TextView description;
        ImageView image;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview_item, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        id = (TextView) itemView.findViewById(R.id.id);
        nom = (TextView) itemView.findViewById(R.id.nom);
        description = (TextView) itemView.findViewById(R.id.description);

        // Locate the ImageView in listview_item.xml
        image = (ImageView) itemView.findViewById(R.id.image);

        // Capture position and set results to the TextViews
        id.setText(resultp.get(AttaquantDisplayActivity.id));
        nom.setText(resultp.get(AttaquantDisplayActivity.nom));
        description.setText(resultp.get(AttaquantDisplayActivity.description));
        imageLoader.DisplayImage(resultp.get(AttaquantDisplayActivity.image), image);
        // Capture ListView item click
        itemView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, SingleItemView.class);

                intent.putExtra("id", resultp.get(AttaquantDisplayActivity.id));

                intent.putExtra("nom", resultp.get(AttaquantDisplayActivity.nom));

                intent.putExtra("description",resultp.get(AttaquantDisplayActivity.description));

                intent.putExtra("image", resultp.get(AttaquantDisplayActivity.image));

                context.startActivity(intent);

            }
        });
        return itemView;
    }
}
