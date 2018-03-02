package com.example.guusvdham.geocash;

import android.app.TabActivity;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private SupportMapFragment fragment;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;

    TabHost host;
    LinearLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_maps);

        //create marker tab for information/user stories and hide it until a marker is clicked
        tabLayout = (LinearLayout)findViewById(R.id.tabLayout);
        host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Information Tab
        TabHost.TabSpec spec = host.newTabSpec("Information");
        spec.setContent(R.id.information_tab);
        spec.setIndicator("Information");
        host.addTab(spec);

        //User Stories Tab
        spec = host.newTabSpec("User Stories");
        spec.setContent(R.id.user_stories_tab);
        spec.setIndicator("User Stories");
        host.addTab(spec);
        //hide marker tab
        tabLayout.setVisibility(LinearLayout.GONE);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //set markerclicklistener
        mMap.setOnMarkerClickListener(this);

        // Add a marker in Glasgow and move the camera
        LatLng glasgow = new LatLng(56, -4);

        mMap.addMarker(new MarkerOptions().position(glasgow).title("Marker in Glasgow"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(glasgow));

        //MenuButton
        ImageButton menuButton = (ImageButton) findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //display menu here
                
            }
        });

        //Add GeoMarkerButton
        ImageButton addGeoMarkerButton = (ImageButton) findViewById(R.id.addGeoMarkerButton);
        addGeoMarkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open the add geomarker window here

            }
        });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        //opens markertab
        tabLayout.setVisibility(LinearLayout.VISIBLE);
        return true;
    }
}
