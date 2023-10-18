package se1606_prm392_group5.example.se1606_prm392_group05.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.se1606_prm392_group05.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class ContactActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mMap;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        //set type
        /*mMap.getUiSettings().setMyLocationButtonEnabled(true);
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
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);*/

        mMap.setMapType(1);
//        mMap.setMapType(1);
//        mMap.setMapType(2);
//        mMap.setMapType(3);
//        mMap.setMapType(4);
        // Add a marker in Sydney and move the camera

        LatLng latLng1 = new LatLng(10.014351444089163, 105.73195414291693);
        LatLng latLng2 = new LatLng(10.0131325944814, 105.73308205124621);
        LatLng latLng3 = new LatLng(10.011835104384474, 105.73156486482098);
        LatLng latLng4 = new LatLng(10.01346492786372, 105.73270696463516);
        LatLng latLng5 = new LatLng(10.012503509021645, 105.73062660479485);
        LatLng latLng6 = new LatLng(10.01273941621197, 105.73026727116783);
        LatLng latLng7 = new LatLng(10.013073617771363, 105.73047688245028);
        PolylineOptions polylineOptions = new PolylineOptions()
                .add(latLng1).add(latLng2).add(latLng3).add(latLng4).add(latLng5).add(latLng6).add(latLng7).add(latLng1)
                .color(Color.RED)
                .width(20);


        PolygonOptions polygonOptions1 = new PolygonOptions()
                .add(latLng1).add(latLng2).add(latLng3).add(latLng4).add(latLng5).add(latLng6).add(latLng7).add(latLng1)
                .strokeColor(Color.RED)
                .fillColor(Color.YELLOW)
                .strokeWidth(20);

        Polyline polyline=mMap.addPolyline(polylineOptions);
        Polygon polygon1=mMap.addPolygon(polygonOptions1);
        //Circel
        Circle circle = mMap.addCircle(new CircleOptions().center(latLng1).radius(50).fillColor(Color.GREEN).strokeColor(Color.BLUE));
        Circle circle1 = mMap.addCircle(new CircleOptions().center(latLng2).radius(50).fillColor(Color.GREEN).strokeColor(Color.BLUE));
        Circle circle2 = mMap.addCircle(new CircleOptions().center(latLng3).radius(50).fillColor(Color.GREEN).strokeColor(Color.BLUE));
        Circle circle3 = mMap.addCircle(new CircleOptions().center(latLng4).radius(50).fillColor(Color.GREEN).strokeColor(Color.BLUE));
        Circle circle4 = mMap.addCircle(new CircleOptions().center(latLng5).radius(50).fillColor(Color.GREEN).strokeColor(Color.BLUE));
   /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        /* */
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng1,15));
    }

}