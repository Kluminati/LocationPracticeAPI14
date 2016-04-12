package ness.android.eduard.locationpracticeapi14;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = new SupportMapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.mapFragmentContainer,mapFragment).commit();
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
        mMap.setOnMapClickListener(this);
        // Add a marker in Sydney and move the camera
        LatLng beerSheva = new LatLng(31.2632364, 34.8108068);

        mMap.addMarker(new MarkerOptions().
                position(beerSheva).
                icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)).
                title("Hello").
                snippet("Snippet").
                title("Marker in Sydney"));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(beerSheva, 16));
    }

    @Override
    public void onMapClick(LatLng latLng)
    {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().title("Location set").snippet("The new loction").position(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
    }
}
