package com.br.buscacep;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by ezequ on 13/06/2017.
 */

public class SearchCepActivity extends Activity {
    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_cep_activity);

        mTvResult = (TextView) findViewById(R.id.textViewCep);

        Button btnJsonRequest = (Button) findViewById(R.id.button);
        btnJsonRequest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue;

// Instantiate the cache
                Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
                Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
                queue = new RequestQueue(cache, network);

// Start the queue
                queue.start();

                JsonObjectRequest myReq = new JsonObjectRequest(Method.GET,
                        "https://viacep.com.br/ws/01001000/json/",
                        null,
                        createMyReqSuccessListener(),
                        createMyReqErrorListener());

                queue.add(myReq);
            }
        });
    }


    private Response.Listener<JSONObject> createMyReqSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // pegar pela key
                    // mTvResult.setText(response.getString("logradouro"));
                    mTvResult.setText(response.toString());
                } catch (Exception e) {
                    mTvResult.setText("Parse error");
                }
            }
        };
    }


    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTvResult.setText(error.getMessage());
            }
        };
    }
}