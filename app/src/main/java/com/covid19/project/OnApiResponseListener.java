package com.covid19.project;

import org.json.JSONArray;


public interface OnApiResponseListener {
    void onSuccess(JSONArray jsonArray);
    void onFailed(String message);
}
