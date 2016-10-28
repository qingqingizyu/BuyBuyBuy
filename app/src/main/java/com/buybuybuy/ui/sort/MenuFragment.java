package com.buybuybuy.ui.sort;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.buybuybuy.R;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    private String name;
    private ListView lvMain;
    private ArrayAdapter<String> arrayAdapter;
    private String[] kinds;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            name = (String) msg.obj;
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            DetailsMainFragment fragment = new DetailsMainFragment(name);
            transaction.replace(R.id.fr_grid,fragment);
            transaction.commit();
        }
    };

    public MenuFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> list = new ArrayList<>();
        kinds = getResources().getStringArray(R.array.kinds);
        for (int i = 0; i < kinds.length; i++) {
            list.add(kinds[i]);
        }
        lvMain = (ListView) view.findViewById(R.id.lv_main);
        lvMain.setBackgroundResource(R.color.whites);
        arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,list);
        lvMain.setAdapter(arrayAdapter);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message msg = Message.obtain();
                String name = kinds[position];
                msg.obj= name;
                mHandler.sendMessage(msg);
            }
        });
    }
}
