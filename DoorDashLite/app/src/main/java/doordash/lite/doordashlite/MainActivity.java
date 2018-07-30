package doordash.lite.doordashlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import doordash.lite.doordashlite.model.Restaurant;
import doordash.lite.doordashlite.ui.RestaurantAdapter;
import doordash.lite.doordashlite.utilities.HttpHandler;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;

    ArrayList<Restaurant> restaurants;
    RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurants = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new GetRestaurants().execute();
    }

    private class GetRestaurants extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://api.doordash.com/v2/restaurant/?lat=37.422740&lng=-122.139956&offset=0&limit=50";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {

                    JSONArray contacts = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String name = c.getJSONObject("business").getString("name");
                        String description = c.getString("description");
                        String coverImgUrl = c.getString("coverImgUrl");
                        String status = c.getString("status");
                        String deliveryFee = c.getString("deliveryFee");

                        Log.e(TAG, "name: " + name);

                        Restaurant res= new Restaurant();
                        res.setId(Integer.parseInt(id));
                        res.setName(name);
                        res.setCoverImgUrl(coverImgUrl);
                        res.setDescription(description);
                        res.setStatus(status);
                        res.setDeliveryFee(Float.valueOf(deliveryFee));

                        restaurants.add(res);

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

           adapter = new RestaurantAdapter(MainActivity.this,restaurants);

            lv.setAdapter(adapter);

        }

    }
}
