package com.covid19.project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class StateActivity extends AppCompatActivity {

    private List<Data> dataList;
    DataAdapter adapter;
    RecyclerView recyclerView;
    AppCompatEditText etSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataList = new ArrayList<>();
        // setup recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new DataAdapter(this, dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        getData();
        etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    adapter.getFilter().filter(s.toString());
                } else {
                    getData();
                }

            }
        });
    }

    void getData() {
        dataList.clear();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    String URL = "https://covidtracking.com/api/states";

                    new ApiConnection().connect(new OnApiResponseListener() {
                        @Override
                        public void onSuccess(JSONArray jsonArray) {
                            try {


                                for (int j = 0; j < jsonArray.length(); j++) {

                                    JSONObject jsonObject = jsonArray.getJSONObject(j);

                                    Data data = new Data(convertToState(jsonObject.getString("state")), jsonObject.getString("positive"), jsonObject.getString("negative"),
                                            jsonObject.getString("recovered"), jsonObject.getString("death"), jsonObject.getString("total"),
                                            jsonObject.getString("lastUpdateEt"));

                                    dataList.add(data);
                                }


                                adapter.notifyDataSetChanged();


                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        }

                        @Override
                        public void onFailed(String message) {
                        }
                    }, null, URL);

                } catch (Exception e) {

                }
            }
        });
    }

    private String convertToState(String stateCode) {

        String state = "";

        String stateName = "{\n" +
                "    \"AL\": \"Alabama\",\n" +
                "    \"AK\": \"Alaska\",\n" +
                "    \"AS\": \"American Samoa\",\n" +
                "    \"AZ\": \"Arizona\",\n" +
                "    \"AR\": \"Arkansas\",\n" +
                "    \"CA\": \"California\",\n" +
                "    \"CO\": \"Colorado\",\n" +
                "    \"CT\": \"Connecticut\",\n" +
                "    \"DE\": \"Delaware\",\n" +
                "    \"DC\": \"District Of Columbia\",\n" +
                "    \"FM\": \"Federated States Of Micronesia\",\n" +
                "    \"FL\": \"Florida\",\n" +
                "    \"GA\": \"Georgia\",\n" +
                "    \"GU\": \"Guam\",\n" +
                "    \"HI\": \"Hawaii\",\n" +
                "    \"ID\": \"Idaho\",\n" +
                "    \"IL\": \"Illinois\",\n" +
                "    \"IN\": \"Indiana\",\n" +
                "    \"IA\": \"Iowa\",\n" +
                "    \"KS\": \"Kansas\",\n" +
                "    \"KY\": \"Kentucky\",\n" +
                "    \"LA\": \"Louisiana\",\n" +
                "    \"ME\": \"Maine\",\n" +
                "    \"MH\": \"Marshall Islands\",\n" +
                "    \"MD\": \"Maryland\",\n" +
                "    \"MA\": \"Massachusetts\",\n" +
                "    \"MI\": \"Michigan\",\n" +
                "    \"MN\": \"Minnesota\",\n" +
                "    \"MS\": \"Mississippi\",\n" +
                "    \"MO\": \"Missouri\",\n" +
                "    \"MT\": \"Montana\",\n" +
                "    \"NE\": \"Nebraska\",\n" +
                "    \"NV\": \"Nevada\",\n" +
                "    \"NH\": \"New Hampshire\",\n" +
                "    \"NJ\": \"New Jersey\",\n" +
                "    \"NM\": \"New Mexico\",\n" +
                "    \"NY\": \"New York\",\n" +
                "    \"NC\": \"North Carolina\",\n" +
                "    \"ND\": \"North Dakota\",\n" +
                "    \"MP\": \"Northern Mariana Islands\",\n" +
                "    \"OH\": \"Ohio\",\n" +
                "    \"OK\": \"Oklahoma\",\n" +
                "    \"OR\": \"Oregon\",\n" +
                "    \"PW\": \"Palau\",\n" +
                "    \"PA\": \"Pennsylvania\",\n" +
                "    \"PR\": \"Puerto Rico\",\n" +
                "    \"RI\": \"Rhode Island\",\n" +
                "    \"SC\": \"South Carolina\",\n" +
                "    \"SD\": \"South Dakota\",\n" +
                "    \"TN\": \"Tennessee\",\n" +
                "    \"TX\": \"Texas\",\n" +
                "    \"UT\": \"Utah\",\n" +
                "    \"VT\": \"Vermont\",\n" +
                "    \"VI\": \"Virgin Islands\",\n" +
                "    \"VA\": \"Virginia\",\n" +
                "    \"WA\": \"Washington\",\n" +
                "    \"WV\": \"West Virginia\",\n" +
                "    \"WI\": \"Wisconsin\",\n" +
                "    \"WY\": \"Wyoming\"\n" +
                "}";

        try {
            JSONObject jsonObject = new JSONObject(stateName);
            state = jsonObject.getString(stateCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return state;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
