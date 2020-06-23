package com.covid19.andhrapradesh;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Test_MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng latLng;
    private FusedLocationProviderClient fusedLocationClient;
    private SupportMapFragment mapFragment;
    LocationRequest mLocationRequest;
    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test__maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(30000);
        mLocationRequest.setFastestInterval(30000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        fusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        mMap.setMyLocationEnabled(true);

        // Test Center 1
        final LatLng center1 = new LatLng(16.478114, 77.574737);
        mMap.addMarker(new MarkerOptions().position(center1).title("Area Hospital, Macherla"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center1));

        //Test Center 2
        final LatLng center2 = new LatLng(16.070119, 79.146485);
        mMap.addMarker(new MarkerOptions().position(center2).title("Markapuram Area Government hospital, Markapur"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center2));

        //Test Center 3
        final LatLng center3 = new LatLng(16.572289, 79.985398);
        mMap.addMarker(new MarkerOptions().position(center3).title("Government Area Hospital, Narasaraopeta"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center3));

        //Test Center 4
        final LatLng center4 = new LatLng(16.642125, 80.487461);
        mMap.addMarker(new MarkerOptions().position(center4).title("Guntur Medical College, Kanna Vari Thota, Guntur").snippet("website: http://gunturmedicalcollege.edu.in/"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center4));

        //Test Center 5
        final LatLng center5 = new LatLng(15.819423, 80.121905);
        mMap.addMarker(new MarkerOptions().position(center5).title("RIMS Hospital, Ongole").snippet("website: http://www.rimsongole.org/"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center5));

        //Test Center 6
        final LatLng center6 = new LatLng(16.807806, 80.495229);
        mMap.addMarker(new MarkerOptions().position(center6).title("Government General Hospital, Guntur"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center6));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(6));

        //Test Center 7
        final LatLng center7 = new LatLng(15.636262, 79.843203);
        mMap.addMarker(new MarkerOptions().position(center7).title("District Government Hospital, Kandukur"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center7));

        //Test Center 8
        final LatLng center8 = new LatLng(16.159339, 80.479334);
        mMap.addMarker(new MarkerOptions().position(center8).title("Area Hospital(APVVP) Chirala, Perala, Chirala"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center8));
      //  mMap.animateCamera(CameraUpdateFactory.zoomTo(6));



        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if(marker.getPosition().equals(center4)) {
                    Uri uriUrl = Uri.parse("http://gunturmedicalcollege.edu.in/");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }
                else if(marker.getPosition().equals(center5)) {
                    Uri uriUrl = Uri.parse("http://www.rimsongole.org/");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }


            }
        });
    }
    LocationCallback mLocationCallback=new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for(Location location1 : locationResult.getLocations()) {
                if (getApplicationContext() != null) {
                    mLastLocation = location1;
                    latLng = new LatLng(location1.getLatitude(), location1.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(7));
                }
            }

        }
    };
    public void onBackPressed(){
        stopService(new Intent(getApplicationContext(), Location_Services.class));
        Intent intent = new Intent(Test_MapsActivity.this, home.class);
        startActivity(intent);
        finish();
        return;

    };
}
