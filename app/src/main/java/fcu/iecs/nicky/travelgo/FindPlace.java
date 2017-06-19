package fcu.iecs.nicky.travelgo;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class FindPlace extends MapsActivity {

    private EditText searchInputAddress,intiAddress;
    private Button searchAddressBut,goToMapBut;
    public static String inputSearchAddress,inputIntiAddress;
    public static double intiLatitude,intiLongitude,searchLatitude,searchLongitude;
    private Geocoder intiGeocoder,searchGeocoder;
    List<android.location.Address> intiAddresses,searchAddresses;
    private boolean isOk=true;
    public static int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_place);

        intiAddress = (EditText)findViewById(R.id.intiLocation);
        searchInputAddress = (EditText) findViewById(R.id.searchAddressEdit);
        searchAddressBut = (Button) findViewById(R.id.searchAddressBut);
        searchAddressBut.setOnClickListener(input_Search_Listener);
        goToMapBut = (Button)findViewById(R.id.gotoMap);
        goToMapBut.setOnClickListener(go_To_Map_Listener);
        i=0;
    }

    View.OnClickListener input_Search_Listener = new View.OnClickListener() {

        public void onClick(View v) {
            inputSearchAddress = searchInputAddress.getText().toString().trim();
            inputIntiAddress = intiAddress.getText().toString().trim();

            if(inputIntiAddress.isEmpty()){
                intiLatitude=-200;
                intiLongitude=-200;
            }else{
                intiGeocoder = new Geocoder(FindPlace.this);
                try{
                    intiAddresses = intiGeocoder.getFromLocationName(inputIntiAddress,1);
                }catch (IOException e){
                    Log.e("AddressToGP", e.toString());
                }

                android.location.Address intiAddress = intiAddresses.get(0);

                intiLatitude=intiAddress.getLatitude();
                intiLongitude = intiAddress.getLongitude();
            }

            if (inputSearchAddress.length() > 0) {
                searchGeocoder = new Geocoder(FindPlace.this);
                try {
                    searchAddresses = searchGeocoder.getFromLocationName(inputSearchAddress, 1);
                } catch (IOException e) {
                    Log.e("AddressToGP", e.toString());
                }

                if (searchAddresses == null || searchAddresses.isEmpty()) {

                    isOk=false;
                } else {
                    android.location.Address searchAddress = searchAddresses.get(0);

                    searchLatitude = searchAddress.getLatitude();
                    searchLongitude = searchAddress.getLongitude();
                }
                if(isOk==false){
                    Toast.makeText(FindPlace.this, R.string.search_error_or_empty, Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent();
                    intent.setClass(FindPlace.this,MapsActivity.class);
                    startActivity(intent);
                    i=1;
                }

            }

        }
    };

    View.OnClickListener go_To_Map_Listener = new View.OnClickListener(){
        public void onClick(View v){
            intiLatitude=-300;
            intiLongitude=-300;
            searchLatitude = -300;
            searchLongitude = -300;
            Intent intent = new Intent();
            intent.setClass(FindPlace.this,MapsActivity.class);
            startActivity(intent);
            i=2;
        }
    };
}