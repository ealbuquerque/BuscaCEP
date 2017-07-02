package com.br.buscacep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.br.buscacep.util.CustomMasks;
import com.br.buscacep.util.LoadingUtil;
import com.br.buscacep.util.ToastUtil;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText textCEP;
    private Button buttonSearch;
    private Button buttonHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCEP = (EditText) findViewById(R.id.editTextCEP);
        textCEP.addTextChangedListener(CustomMasks.insert(CustomMasks.CEP_MASK, textCEP));

        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = textCEP.getText().length();
                if (length == 0) {
                    ToastUtil.show(MainActivity.this, "Digite o CEP");
                } else if (length < 9) {
                    ToastUtil.show(MainActivity.this, "CEP Inválido");
                } else {
                    doRequest(textCEP.getText().toString());
                }
            }
        });

        buttonHistory = (Button) findViewById(R.id.buttonHistory);
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });
    }

    private void doRequest(String cep) {
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        RequestQueue queue = new RequestQueue(cache, network);
        queue.start();

        LoadingUtil.show(MainActivity.this);
        JsonObjectRequest request = new JsonObjectRequest("https://viacep.com.br/ws/" + cep + "/json/", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Intent intent = new Intent(MainActivity.this, SearchCepActivity.class);

                            // pegar todos parâmetros
                            Bundle params = new Bundle();
                            params.putString("cep", response.getString("cep"));
                            params.putString("street", response.getString("logradouro"));
                            params.putString("neighborhood", response.getString("bairro"));
                            params.putString("city", response.getString("localidade"));
                            params.putString("state", response.getString("uf"));
                            intent.putExtras(params);

                            startActivityForResult(intent, 0);
                        } catch (Exception e) {
                            ToastUtil.show(MainActivity.this, "Ops... Ocorreu um erro. Por favor tente, novamente.");
                            Log.d("onResponse response", response.toString());
                            e.printStackTrace();
                        } finally {
                            LoadingUtil.hide();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ToastUtil.show(MainActivity.this, "Ops... Ocorreu um erro. Por favor tente, novamente.");
                        Log.d("onErrorResponse error", error.getMessage());
                        LoadingUtil.hide();
                    }
                });
        queue.add(request);
    }
}