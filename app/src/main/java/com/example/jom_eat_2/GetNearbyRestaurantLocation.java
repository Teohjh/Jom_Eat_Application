package com.example.jom_eat_2;
//map activity page for find nearby food location
import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearbyRestaurantLocation extends AsyncTask<Object, String , String>
{
    private String googleRestaurantPlacesData , url;
    private GoogleMap mGoogleMap;

    @Override
    protected String doInBackground(Object... objects)
    {
        try
        {
            mGoogleMap = (GoogleMap) objects[0];
            url = (String) objects[1];
            com.example.jom_eat_2.DownloadURL downloadURL = new com.example.jom_eat_2.DownloadURL();
            googleRestaurantPlacesData = downloadURL.readTheUrl(url);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return googleRestaurantPlacesData;
    }

    @Override
    protected void onPostExecute(String s)
    {
        List<HashMap<String,String>> nearbyRestaurantPlaceList = null;
        com.example.jom_eat_2.DataParser mDataParser = new com.example.jom_eat_2.DataParser();
        nearbyRestaurantPlaceList = mDataParser.parse(s);
        displayRestaurantNearbyPlaces(nearbyRestaurantPlaceList);
    }

    public void displayRestaurantNearbyPlaces(List<HashMap<String,String>> nearbyRestaurantPlaceList)
    {
        for (int i=0; i<nearbyRestaurantPlaceList.size(); i++)
        {
            MarkerOptions mO = new MarkerOptions();
            HashMap<String,String> googleRestaurantNearbyPlaces = nearbyRestaurantPlaceList.get(i);

            String restaurantPlaceName = googleRestaurantNearbyPlaces.get("restaurant place name");
            String vicinity = googleRestaurantNearbyPlaces.get("vicinity");
            double lat = Double.parseDouble(googleRestaurantNearbyPlaces.get("lat"));
            double lng = Double.parseDouble(googleRestaurantNearbyPlaces.get("lng"));

            LatLng l = new LatLng(lat, lng);
            mO.position(l);
            mO.title(restaurantPlaceName + ":" + vicinity);
            mO.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

            mGoogleMap.addMarker(mO);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(l));
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        }
    }
}
