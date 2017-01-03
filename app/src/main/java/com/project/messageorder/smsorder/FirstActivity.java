package com.project.messageorder.smsorder;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    BaseAdapter adapter;
    ListView listView;
    ArrayList<item> items;
    TextView messageview;
    final int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        listView = (ListView) findViewById(R.id.listView);
        //setview();
        ActivityCompat.requestPermissions(FirstActivity.this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
        items=new ArrayList<item>();
        setListView();
        
    }

    private void setListView() {
        items = new ArrayList<item>();
        adapter = new BaseAdapter() {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            @Override
            public int getCount() {
                return items.size();
            }

            @Override
            public Object getItem(int position) {
                return items.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = layoutInflater.inflate(R.layout.listviewitem, null);

                }

                messageview = (TextView) convertView.findViewById(R.id.itemview);


                messageview.setText(items.get(position).getMessagebody());

                return convertView;


            }
        };
        listView.setAdapter(adapter);

        Uri sms=Uri.parse("content://sms/inbox");
        try{

            Cursor cursor=getContentResolver().query(sms,null,null,null,null);
            //item arrayListElements = new item("new sms 2");
            while (cursor.moveToNext()){

                String smsbody=cursor.getString(cursor.getColumnIndexOrThrow("body"));
                item arrayListElements = new item(smsbody);
                items.add(arrayListElements);


            }
        }catch (Exception e)
        {
            Log.d("checkmessage",""+e);
        }



        adapter.notifyDataSetChanged();


    }
}
