package dev.johnmorgan.hikerswatch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;
    TextView latitudeTextView;
    TextView longitudeTextView;
    TextView accuracyTextView;
    TextView altitudeTextView;
    TextView locationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitudeTextView = findViewById(R.id.latitudeTextView);
                longitudeTextView = findViewById(R.id.longitudeTextView);
                accuracyTextView = findViewById(R.id.accuracyTextView);
                altitudeTextView = findViewById(R.id.altitudeTextView);
                locationTextView = findViewById(R.id.locationTextView);
                latitudeTextView.setText("Latitude: " + location.getLatitude());
                longitudeTextView.setText("Longitude: " + location.getLongitude());
                accuracyTextView.setText("Accuracy: " + location.getAccuracy());
                altitudeTextView.setText("Altitude: " + location.getAltitude());

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                String address = "Could not find location";
                try {
                    List<Address> listLocation = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if (listLocation != null && listLocation.size() > 0) {
                        address = "Address: ";
                        if (listLocation.get(0).getThoroughfare() != null) {
                            address += listLocation.get(0).getThoroughfare();
                        }
                        if (listLocation.get(0).getLocality() != null) {
                            address += "\n" + listLocation.get(0).getLocality();
                        }
                        if (listLocation.get(0).getAdminArea() != null) {
                            address += "\n" + listLocation.get(0).getAdminArea();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                locationTextView.setText(address);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }
}
