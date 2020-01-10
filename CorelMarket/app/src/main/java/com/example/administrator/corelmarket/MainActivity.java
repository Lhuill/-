package com.example.administrator.corelmarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.corelmarket.bean.CartBean;
import com.corelmarket.database.SQLiteHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteHelper sqLiteHelper;
    TextView content_name, content_price, content_introduction;
    Button btn;
    ImageView pic3;
    List<CartBean> list;
    ListView mListView;
    String[] titles = {
            "MacBook 6 Pro", "MacBook 7 Pro", "MacBook 8 Pro", "MacBook 5 Pro", "MacBook 6", "NoteBook 6 Pro", "Dell 6 Pro", "MacBook 7", "iPad", "MacBook 8"};
    String[] introductions = {
            "专业级笔记本",
            "旗舰级笔记本 ",
            "专业级笔记本 ",
            "旗舰级笔记本 ",
            "旗舰级笔记本 ",
            "旗舰级笔记本",
            "旗舰级笔记本",
            "旗舰级笔记本",
            "掌上电脑 ",
            "旗舰级笔记本"

    };
    String[] prices = {
            "￥9999起",
            "￥9999起",
            "￥9999起",
            "￥7100起",
            "￥6990起",
            "￥6879起",
            "￥9888起",
            "￥4399起",
            "￥6100起",
            "￥6999起"
    };
    int[] pics = {
            R.drawable.table,
            R.drawable.apple,
            R.drawable.cake,
            R.drawable.wireclothes,
            R.drawable.kiwifruit,
            R.drawable.scarf,
            R.drawable.timg,
            R.drawable.hefe,
            R.drawable.blick,
            R.drawable.mot
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        mListView = (ListView) findViewById(R.id.list);
        btn = (Button) findViewById(R.id.content_btn);
        pic3 = (ImageView) findViewById(R.id.pic3);
        MyBaseAdapter mAdapter = new MyBaseAdapter();
        mListView.setAdapter(mAdapter);
        initData();
    }

    protected void initData() {
        sqLiteHelper = new SQLiteHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pic3:
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                break;
        }
    }

    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return titles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.list_item, null);
                vh = new ViewHolder();
                vh.title = (TextView) convertView.findViewById(R.id.content_name);
                vh.price = (TextView) convertView.findViewById(R.id.content_price);
                vh.introduction = (TextView) convertView.findViewById(R.id.content_introduction);
                vh.iv = (ImageView) convertView.findViewById(R.id.iv);
                vh.btn = (Button) convertView.findViewById(R.id.content_btn);
                convertView.setTag(vh);
                final ViewHolder finalVh = vh;
                vh.btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String CartName = finalVh.title.getText().toString();
                        String CartPrice = finalVh.price.getText().toString();
                        String CartIntroduction = finalVh.introduction.getText().toString();
                        if (sqLiteHelper.insertData(CartName, CartPrice, CartIntroduction)) {
                            Toast.makeText(MainActivity.this, "已加入购物车", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "加入购物车失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.title.setText(titles[position]);
            vh.price.setText(prices[position]);
            vh.introduction.setText(introductions[position]);
            vh.iv.setBackgroundResource(pics[position]);
            return convertView;
        }
    }

    class ViewHolder {
        TextView title, price, introduction;
        Button btn;
        ImageView iv;
    }

    private class ShopCartActivity {
    }
}
