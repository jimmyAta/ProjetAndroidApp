package emse.lightcontroller;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;


public class RoomContextHttpManager  {


    final ContextManagementActivity contextManagementActivity;

    public RoomContextHttpManager(ContextManagementActivity ctxtactivity){
        this.contextManagementActivity=ctxtactivity;
    }

    public void switchLight(Context context, final String room) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://mighty-plains-77473.herokuapp.com/api/rooms/" + room + "/switchLight/";

        //get room sensed context
        JsonArrayRequest contextRequest = new JsonArrayRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonObject = new JSONObject();
                            for (int i = 0; i < response.length(); i++) {
                                if (response.getJSONObject(i).getString("id").toString().equals(room)) {
                                    jsonObject = response.getJSONObject(i);
                                }
                            }

                            String id = jsonObject.getString("id").toString();
                            int lightLevel = Integer.parseInt(jsonObject.getJSONObject("light").get("level").toString());
                            String lightStatus = jsonObject.getJSONObject("light").get("status").toString();

                            int noiseLevel = Integer.parseInt(jsonObject.getJSONObject("noise").get("level").toString());
                            String noiseStatus = jsonObject.getJSONObject("noise").get("status").toString();

                            System.out.println(lightLevel);

                            RoomContextState roomstate = new RoomContextState(id, lightStatus, lightLevel, noiseLevel);
                            contextManagementActivity.onUpdate(roomstate);


                            // do something with results...
                            // notify main activity for update...
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Some error to access URL : Room may not exists...
                    }
                });
        queue.add(contextRequest);


    }

    public void switchNoise(Context context, final String room) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://mighty-plains-77473.herokuapp.com/api/rooms/" + room + "/switch_noise/";

        //get room sensed context
        JsonArrayRequest contextRequest = new JsonArrayRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject jsonObject = new JSONObject();
                            for (int i = 0; i < response.length(); i++) {
                                if (response.getJSONObject(i).getString("id").toString().equals(room)) {
                                    jsonObject = response.getJSONObject(i);
                                }
                            }

                            String id = jsonObject.getString("id").toString();
                            int lightLevel = Integer.parseInt(jsonObject.getJSONObject("light").get("level").toString());
                            String lightStatus = jsonObject.getJSONObject("light").get("status").toString();

                            int noiseLevel = Integer.parseInt(jsonObject.getJSONObject("noise").get("level").toString());
                            String noiseStatus = jsonObject.getJSONObject("noise").get("status").toString();

                            System.out.println(lightLevel);

                            RoomContextState roomstate = new RoomContextState(id, lightStatus, lightLevel, noiseLevel);
                            contextManagementActivity.onUpdate(roomstate);


                            // do something with results...
                            // notify main activity for update...
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Some error to access URL : Room may not exists...
                    }
                });
        queue.add(contextRequest);


    }


    public void retrieveRoomContextState(String room, Context context) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://mighty-plains-77473.herokuapp.com/api/rooms/" + room + "/content";

        //get room sensed context
        JsonObjectRequest contextRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String id = response.getString("id").toString();
                            int lightLevel = Integer.parseInt(response.getJSONObject("light").get("level").toString());
                            String lightStatus = response.getJSONObject("light").get("status").toString();

                            int noiseLevel = Integer.parseInt(response.getJSONObject("noise").get("level").toString());
                            String noiseStatus = response.getJSONObject("noise").get("status").toString();

                            System.out.println("asdfsdjfh");
                            System.out.println(lightLevel);

                            RoomContextState roomstate = new RoomContextState(id, lightStatus, lightLevel, noiseLevel);
                            contextManagementActivity.onUpdate(roomstate);


                            // do something with results...
                            // notify main activity for update...
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Some error to access URL : Room may not exists...
                    }
                });
        queue.add(contextRequest);


    }

}