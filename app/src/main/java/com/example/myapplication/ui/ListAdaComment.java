package com.example.myapplication.ui;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DatabaseHelperUser;
import com.example.myapplication.R;
import com.example.myapplication.ui.BaseApplication;
import com.example.myapplication.ui.SearchBookdata;
import com.example.myapplication.ui.SessionHandler;
import com.example.myapplication.ui.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class ListAdaComment extends RecyclerView.Adapter {
    String bookname;
    DatabaseHelperUser db=new DatabaseHelperUser(BaseApplication.getContext());
    SessionHandler session = null;
    String[] author;
    float[] rate;
    String[] content;
    String[] date;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item3, viewGroup, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        Cursor as=db.get_comment(bookname);
        as.moveToNext();
        return as.getCount();
    }

    public String getbookname(String name)
    {
        this.bookname=name;
        return this.bookname;
    }
    private class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView mItemText;
        private TextView mItemAuthor;
        private ImageView mItemImage;
        private TextView mItemDes;
        private TextView mItemPrice;
        private TextView tp;

        public ListViewHolder(View itemView) {

            super(itemView);
            mItemText = itemView.findViewById(R.id.Book);
            mItemAuthor = itemView.findViewById(R.id.Author);
            mItemImage = itemView.findViewById(R.id.bookimg);
            mItemDes=itemView.findViewById(R.id.des);
            mItemPrice=itemView.findViewById(R.id.Price);
            tp=itemView.findViewById(R.id.Type);
        }

        public void bindView(final int position) {
            session=new SessionHandler(BaseApplication.getContext());
            final User user = session.getUserDetails();
            Cursor temp=db.get_comment(bookname);
            temp.moveToNext();
            int tempcount=temp.getCount();
            author = new String[tempcount];
            content = new String[tempcount];
            date = new String[tempcount];
            rate = new float[tempcount];
            int a=0;
            if (temp!= null) {
                temp.moveToFirst();
                author[a] = temp.getString(temp.getColumnIndex("AUTHOR"));
                content[a] = temp.getString(temp.getColumnIndex("CONTENT"));
                date[a] = temp.getString(temp.getColumnIndex("DATE"));
                rate[a] = temp.getFloat(temp.getColumnIndex("SCORE"));
                a++;
                while (temp.moveToNext()) {
                    author[a] = temp.getString(temp.getColumnIndex("AUTHOR"));
                    content[a] = temp.getString(temp.getColumnIndex("CONTENT"));
                    date[a] = temp.getString(temp.getColumnIndex("DATE"));
                    rate[a] = temp.getFloat(temp.getColumnIndex("SCORE"));
                    a++;
                }
            }
            TextView mItemText=itemView.findViewById(R.id.author_com);
            TextView mItemContent=itemView.findViewById(R.id.content_com);
            TextView ItemDate=itemView.findViewById(R.id.com_date);
            MaterialRatingBar ItemRate=itemView.findViewById(R.id.RatingBar33);
            mItemText.setText(author[position]);
            mItemContent.setText(content[position]);
            ItemDate.setText(date[position]);
            ItemRate.setRating(rate[position]);
            //Toast.makeText(BaseApplication.getContext(), (int) rate[position],Toast.LENGTH_SHORT).show();
        }

    }
}
