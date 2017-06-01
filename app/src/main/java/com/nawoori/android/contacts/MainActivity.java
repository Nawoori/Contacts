package com.nawoori.android.contacts;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nawoori.android.contacts.domain.Data;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> datas = new ArrayList<>();

    //다른 액티비티와 데이터를 주고받을 때 사용하는 키를 먼저 정의해둔다.
    public static final String DATA_KEY = "ContantActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.데이터
        datas = Loader.getDatas(this);
        //2.아답터
        CustomAdapter adapter = new CustomAdapter(datas, this);
        //3.연결
        ListView.listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);



        ((Button) findViewById(R.id.btnContact)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CheckPermissionActivity.class);
                startActivity(intent);

            }
        });
    }
}
    class CustomAdapter extends BaseAdapter {
        //1. 데이터
        ArrayList<Data> datas;
        //2. 인플레이터
        LayoutInflater inflater;
        //3. 컨텍스트
        Context context

        public CustomAdapter(ArrayList<Data> datas, Context context) {
            this.datas = datas;
            this.context = context;
            this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //1. 컨버터뷰를 체크해서 null 일경우만 item layout을 생성해준다.
            Holder holder;
            if (convertView == null) {
                convertView =inflater.inflate(R.layout.item_list, null);
                holder = new Holder(convertView, context);
                convertView.setTag(holder);
            }else{
                holder = (holder) convertView.getTag()
            }
            //2.내 아이템에 해당하는 데이터를 가져온다.
            Data data = datas.get(position);
            //3.뷰에 데이터를 세팅한다.
            holder.setName(data.name);
            holder.setTel(data.tel);
            holder.setAddress(data.address);
            holder.setEmail(data.email)

            return  convertView;

        }

        class Holder {
            int position;
            TextView no;
            TextView title;
            TextView adress;







        }
    }