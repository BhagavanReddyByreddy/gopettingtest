package bhagavan.volley;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by bhagavan on 1/9/2017.
 */

public class VolleyRequest {
    Activity context;
    ProgressDialog pDialog;
    String finalResult = null;


    public MessageCallBack callback;

    public VolleyRequest(Activity applicationContext) {
        this.context = applicationContext;
    }


    public void requestPostService(String url, int method) {


        // Tag used to cancel the request
        String tag_string_req = "string_req";

        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(method,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("onSuccessResponse", response.toString());
                finalResult = response;
                pDialog.hide();
                callback.onSuccess(response);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("onErrorResponse", "Error: " + error.getMessage());
                pDialog.hide();
                callback.onSuccess("null");
            }
        });

// Adding request to request queue
            AppController.getInstance().addToRequestQueue(strReq,tag_string_req);

    }
}
