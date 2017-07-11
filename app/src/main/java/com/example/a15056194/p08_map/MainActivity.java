package com.example.a15056194.p08_map;

import android.Manifest;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btnN, btnC, btnE;
    private GoogleMap map;
    Marker mNorth, mEast, mCentral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnN = (Button) findViewById(R.id.btnNorth);
        btnC = (Button) findViewById(R.id.btnCentral);
        btnE = (Button) findViewById(R.id.btnEast);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        final LatLng sg = new LatLng(1.3521, 103.8198);
        final LatLng north = new LatLng(1.448134, 103.819879);
        final LatLng central = new LatLng(1.370104, 103.848295);
        final LatLng east = new LatLng(1.333590, 103.960653);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(sg, 15));
                map.moveCamera(CameraUpdateFactory.zoomTo(10));

                mNorth = map.addMarker(new MarkerOptions()
                        .position(north)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654\n" +
                                "Operating hours: 10am-5pm\n" +
                                "Tel:65433456")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.rsz_download)));

                mCentral = map.addMarker(new MarkerOptions()
                        .position(central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                mEast = map.addMarker(new MarkerOptions()
                        .position(east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

                UiSettings ui = map.getUiSettings();
                ui.setZoomControlsEnabled(true);
                ui.setCompassEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast toast = Toast.makeText(MainActivity.this,marker.getTitle(),Toast.LENGTH_SHORT);
                        toast.show();
                        return false;
                    }
                });
            }
        });

        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(north, 14));

            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(east, 14));
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(central, 14));
            }
        });
    }
}
