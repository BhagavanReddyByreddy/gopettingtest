package bhagavan.gopettingtest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bhagavan.Adapters.EventsAdapter;
import bhagavan.model.EventDetails;
import bhagavan.volley.MessageCallBack;
import bhagavan.volley.VolleyRequest;

/**
 * Created by bhagavan on 2/24/2017.
 */

public class HomeActivity  extends AppCompatActivity implements MessageCallBack {

    ProgressDialog pDialog;
    VolleyRequest request;
    RecyclerView list;
    ArrayList<EventDetails> eventsArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        list = (RecyclerView) findViewById(R.id.recyclerView);
        savedInstanceState = getIntent().getExtras();
        String nameOfUser = savedInstanceState.getString("userName");
        Toast.makeText(this, "welcome "+nameOfUser, Toast.LENGTH_SHORT).show();

        String url = Constants.event_info_url;
        request = new VolleyRequest(HomeActivity.this);
        request.callback = this;
        request.requestPostService(url, Request.Method.GET);

    }

    @Override
    public void onSuccess(String result) {
        if(!(result.trim().equals("null"))) {
            try {
                JSONObject eventInfo = new JSONObject(result);
                JSONArray eventList = eventInfo.getJSONArray("data");
                eventsArrayList = new ArrayList<EventDetails>();
                for(int i = 0 ; i < eventList.length();i++){
                    EventDetails events = new EventDetails();
                    JSONObject eachEvent = eventList.getJSONObject(i);
                    events.setEventName(eachEvent.getString("name"));
                    events.setEventEndDate(eachEvent.getString("endDate"));
                    events.setEventPic(eachEvent.getString("icon"));
                    eventsArrayList.add(events);
                }
                sendToRecyclerView(eventsArrayList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this,"something went wrong please try again",Toast.LENGTH_LONG).show();
        }
    }

    private void sendToRecyclerView(ArrayList<EventDetails> eventsArrayList) {

        EventsAdapter adapter = new EventsAdapter(this,eventsArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(mLayoutManager);
        list.setAdapter(adapter);
    }
}
