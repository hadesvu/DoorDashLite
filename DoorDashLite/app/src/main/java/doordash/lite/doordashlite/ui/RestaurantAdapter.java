package doordash.lite.doordashlite.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import doordash.lite.doordashlite.MainActivity;
import doordash.lite.doordashlite.R;
import doordash.lite.doordashlite.model.Restaurant;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    private Context context;

    ArrayList<Restaurant> restaurants;;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurants) {
        super(context, R.layout.list_item, restaurants);
        this.context = context;
        this.restaurants = restaurants;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RestaurantHolder holder = null;

        if(convertView == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            holder = new RestaurantHolder();
            holder.coverImgUrl = (ImageView)convertView.findViewById(R.id.cover);
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.description = (TextView)convertView.findViewById(R.id.description);
            holder.status = (TextView)convertView.findViewById(R.id.status);

            convertView.setTag(holder);
        }
        else
        {
            holder = (RestaurantHolder)convertView.getTag();
        }

        Restaurant restaurant = restaurants.get(position);
        holder.name.setText(restaurant.getName());
        holder.description.setText(restaurant.getDescription());
        holder.status.setText(restaurant.getStatus());
        Picasso.get().load(restaurant.getCoverImgUrl()).fit().into(holder.coverImgUrl);



        return convertView;
    }

    static class RestaurantHolder
    {
        ImageView coverImgUrl;
        TextView name;
        TextView description;
        TextView status;
    }

}
