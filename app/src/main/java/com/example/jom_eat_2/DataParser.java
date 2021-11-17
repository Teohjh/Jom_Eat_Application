package com.example.jom_eat_2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser
{
    private HashMap<String, String> getSingleRestaurantPlace(JSONObject googleRestaurantJson)
    {
        HashMap<String, String> googleRestaurantMap = new HashMap<String, String>();
        String restaurantPlaceName = "-NA-";
        String vicinity = "-NA-";
        String latitude = "";
        String longitude = "";
        String reference = "";

        try
        {
            if (!googleRestaurantJson.isNull("name"))
            {
                restaurantPlaceName = googleRestaurantJson.getString("name");
            }

            if (!googleRestaurantJson.isNull("vicinity"))
            {
                vicinity = googleRestaurantJson.getString("vicinity");
            }

            latitude = googleRestaurantJson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googleRestaurantJson.getJSONObject("geometry").getJSONObject("location").getString("lng");

            reference = googleRestaurantJson.getString("reference");

            googleRestaurantJson.put("restaurant place name" , restaurantPlaceName);
            googleRestaurantJson.put("vicinity" , vicinity);
            googleRestaurantJson.put("lat" , latitude);
            googleRestaurantJson.put("lng" , longitude);
            googleRestaurantJson.put("reference" , reference);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return googleRestaurantMap;
    }

    private List<HashMap<String, String >> getAllNearbyRestaurantPlaces(JSONArray jsonArray)
    {
        int counter = jsonArray.length();
        List<HashMap<String,String>> restaurantPlacesList = new ArrayList<>();
        HashMap<String,String> restaurantPlaceMap = null;

        for (int i=0; i<counter; i++)
        {
            try
            {
                restaurantPlaceMap = getSingleRestaurantPlace((JSONObject) jsonArray.get(i));
                restaurantPlacesList.add(restaurantPlaceMap);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        return restaurantPlacesList;
    }

    public List<HashMap<String, String >> parse(String jsonData)
    {
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        try
        {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("results");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return getAllNearbyRestaurantPlaces(jsonArray);
    }
}
