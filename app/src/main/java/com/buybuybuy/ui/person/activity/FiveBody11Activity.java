package com.buybuybuy.ui.person.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.buybuybuy.R;

import java.util.ArrayList;
import java.util.List;

public class FiveBody11Activity extends AppCompatActivity {
    private EditText etName;
    private Button btn_reg;
    private Button btn_move;
    private ListView lv;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private List<String> list;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_five_body11);
        initItem();

        sp = getSharedPreferences("config",MODE_PRIVATE);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list);
        lv.setAdapter(adapter);

        registerForContextMenu(lv);
    }
    //读取数据
    private void initItem() {
        etName = (EditText) findViewById(R.id.ed_name);
        btn_reg = (Button) findViewById(R.id.btn_reg);
        btn_move = (Button) findViewById(R.id.btn_move);
        lv = (ListView) findViewById(R.id.lv);
        list = new ArrayList<>();

    }

    /* *
       * @author: ZTC
       * @Description:  注册按钮
       * @data:  2016/8/24  20:52
       * @version: V1.0
       */
    public void register(View view) {
        String name = etName.getText().toString();
        //创建一个编辑器
        edit = sp.edit();
        //存储信息
        edit.putString("name",name);
        edit.commit();
        //循环遍历是否有相同数据
        boolean flag = true;
        for (int i = 0; i < list.size(); i++) {
            if (name.equals(list.get(i))) {
                flag =false;
            }
        }
        if (flag){
            Toast.makeText(FiveBody11Activity.this, "反馈意见成功成功", Toast.LENGTH_SHORT).show();
            list.add(name);
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(FiveBody11Activity.this, "您反馈的意见重复", Toast.LENGTH_SHORT).show();
        }
    }

    //删除
    public void remove(View view) {
        String str = etName.getText().toString();
        for (int i= 0; i <list.size() ; i++) {
            if(str.equals(list.get(i))){
                list.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(FiveBody11Activity.this, "已经撤回意见", Toast.LENGTH_SHORT).show();
            }
        }

    }
    //注册上下文
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_k:
                ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                int position = info.position;
                list.remove(position);
                adapter.notifyDataSetChanged();
                break;
            case R.id.menu_f:

                break;
        }
        return super.onContextItemSelected(item);
    }
}
