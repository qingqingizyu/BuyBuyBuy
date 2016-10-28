package com.buybuybuy.ui.sort;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.buybuybuy.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsMainFragment extends Fragment {
    private String name = "女装";

    private GridView gridView;
    private List<KindMain> kindMains;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            kindMains = (List<KindMain>) msg.obj;
            DetailsAdapter detailsAdapter  = new DetailsAdapter(kindMains,name,getContext());
            gridView.setAdapter(detailsAdapter);
            detailsAdapter.notifyDataSetChanged();
        }
    };

    public DetailsMainFragment() {
    }

    public DetailsMainFragment(String names) {
        this.name = names;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = (GridView) view.findViewById(R.id.grid_main);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                KindMain kindMain = (KindMain) parent.getItemAtPosition(position);
                String cid = kindMain.getCid();
                String nameDet = kindMain.getName();
                Intent intent = new Intent(getActivity(),DetialsActivity.class);
                intent.putExtra("names",nameDet);
                intent.putExtra("cid",cid);
                startActivity(intent);
            }
        });
       new Thread(
       ){
           @Override
           public void run(){
               Request request = new Request.Builder().get().url(Contasts.KIND_MAIN).build();
               new OkHttpClient().newCall(request).enqueue(new Callback() {

                   @Override
                   public void onFailure(Call call, IOException e) {
                   }
                   @Override
                   public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        try {
                            Message msg = Message.obtain();
                            byte[] datas = response.body().bytes();
                            String json = new String(datas);
                            JSONObject object = new JSONObject(json);
                            JSONArray array = object.getJSONArray("data");
                            List<String> list = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                JSONArray array1 = array.getJSONArray(i);
                                 JSONObject nameObj = (JSONObject) array1.get(0);
                                String nameObjString = nameObj.getString("name");
                                if (nameObjString.equals(name)){
                                    JSONArray array2 = array1.getJSONArray(1);
                                    for (int j = 0; j < array2.length(); j++) {
                                        String jsons = array2.getString(j);
                                        list.add(jsons);
                                    }
                                }
                            }
                            Gson gson = new Gson();
                                kindMains = gson.fromJson(list.toString(), new TypeToken<List<KindMain>>() {
                                }.getType());
                            msg.obj = DetailsMainFragment.this.kindMains;
                            mHandler.sendMessage(msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                   }
               });
           }
       }.start();
    }
}
