package fcu.iecs.nicky.travelgo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.id.message;

public class FriendMap extends AppCompatActivity {
    private double currentLatitude = 0;
    private double currentLongitude = 0;
    private LocationManager mLocationManager;
    private static final int LOCATION_UPDATE_MIN_DISTANCE = 1000;
    private static final int LOCATION_UPDATE_MIN_TIME = 50;
    WebView myWebView;
    TextView url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getCurrentLocation();
        myWebView = (WebView) findViewById(R.id.MapWebView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.requestFocus();
        myWebView.setWebViewClient(new MyWebViewClient());
        url = (TextView)findViewById(R.id.url_save);


    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }



    private void getCurrentLocation() {
        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location location = null;
        if (!(isGPSEnabled || isNetworkEnabled)) {
            // location_provider error
        } else {
            if (isNetworkEnabled) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            } else if (isGPSEnabled) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
        if (location != null) {
            Log.d("location",String.format("getCurrentLocation(%f, %f)", location.getLatitude(), location.getLongitude()));
        }
    }

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                String my_x = String.format("%f", location.getLatitude());
                String my_y = String.format("%f", location.getLongitude());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("UserTable/2/");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String main_x = (String)dataSnapshot.child("Loc_x").getValue();
                        String main_y = (String)dataSnapshot.child("Loc_y").getValue();
                        //Toast.makeText(FriendMap.this,"x="+main_x+" y="+main_y,Toast.LENGTH_LONG).show();
                        //url.setText("http://nicky.esy.es/se/index2.html?x_main="+main_x+"&y_main="+main_y);
                        myWebView.loadUrl("http://nicky.esy.es/se/index2.html?x_main="+main_y+"&y_main="+main_x+(String)url.getText());
                        //Toast.makeText(FriendMap.this,"http://nicky.esy.es/se/index2.html?x_main="+main_y+"&y_main="+main_x+(String)url.getText(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });

                //myWebView.loadUrl((String)url.getText()+"&x_me="+my_x+"&y_me="+my_y);
                url.setText("&x_me="+my_x+"&y_me="+my_y);
                String msg = String.format("%f, %f", location.getLatitude(), location.getLongitude());
                //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            } else {
                // Logger.d("Location is null");
                Toast.makeText(getApplicationContext(), "Location is null", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
}
