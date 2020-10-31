package com.example.myapplication.ui;

import android.content.Intent;
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
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static androidx.core.content.ContextCompat.startActivity;

public class ListAdaSearch extends RecyclerView.Adapter implements Filterable {
    SearchBookdata a = new SearchBookdata();
    DatabaseHelperUser db=new DatabaseHelperUser(BaseApplication.getContext());
    SessionHandler session = null;
    String[] h = a.getTitle();
    int count=a.getTitle().length;
    String[] d = a.getAuthor();
    int[] e = a.picturePath();
    String[] q=a.getDes();
    int[] pri=a.getPrice();
    String[] type=a.getType();
    float[] rate = new float[count];
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        return count;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            h = a.getTitle();
            count=a.getTitle().length;
            d = a.getAuthor();
            e = a.picturePath();
            q=a.getDes();
            pri=a.getPrice();
            type=a.getType();
            List<String>title =new ArrayList<>();
            List<String>Author =new ArrayList<>();
            List<Integer>img =new ArrayList<>();
            List<String>des =new ArrayList<>();
            List<Integer>price =new ArrayList<>();
            List<String>ty=new ArrayList<>();
            if (constraint.toString().isEmpty())
            {
                ty.addAll(Arrays.asList(type));
                title.addAll(Arrays.asList(h));
                Author.addAll(Arrays.asList(d));
                for (int i : e)
                {
                    img.add(i);
                }
                des.addAll(Arrays.asList(q));
                for (int i : pri)
                {
                    price.add(i);
                }
            }
            else
            {
                for (int i=0;i<count;i++)
                {
                    if (h[i].toLowerCase().contains(constraint.toString().toLowerCase()) || type[i].toLowerCase().contains(constraint.toString().toLowerCase()))
                    {
                        title.add(h[i]);
                        Author.add(d[i]);
                        img.add(e[i]);
                        des.add(q[i]);
                        price.add(pri[i]);
                        ty.add(type[i]);
                    }

                }
            }
            String[] temptitle=new String[title.size()];
            String[] tempAuthor=new String[title.size()];
            int[] tempimg=new int[title.size()];
            String[] tempdes=new String[title.size()];
            int[] tempprice=new int[title.size()];
            String[] temptype=new String[title.size()];
            for (int i=0;i<title.size();i++)
            {
                temptitle[i]=title.get(i);
                tempAuthor[i]=Author.get(i);
                tempimg[i]=img.get(i);
                tempdes[i]=des.get(i);
                tempprice[i]=price.get(i);
                temptype[i]=ty.get(i);
            }
            count=title.size();
            h=temptitle;
                    d=tempAuthor;
                    e=tempimg;
                            q=tempdes;pri=tempprice;
                            type=temptype;
            /*
            List<List<String>> bigArrayList = new ArrayList<List<String>>();
            bigArrayList.add(title);
            bigArrayList.add(Author);
            bigArrayList.add(img);
            bigArrayList.add(des);
            bigArrayList.add(price);
            FilterResults filterResults=new FilterResults();
            filterResults.values=bigArrayList;
             */
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            //List<List<String>> bArrayList = new ArrayList<List<String>>();
            //bArrayList.addAll((Collection<? extends List<String>>) results.values);
            notifyDataSetChanged();
            /*
            count=
            for(String i:bArrayList.get(0))
            {
                h= bArrayList.get(0).toString();
            }
            int count=a.getTitle().length;
            String[] d = a.getAuthor();
            int[] e = a.picturePath();
            String[] q=a.getDes();
            final int[] pri=a.getPrice();*/
        }
    };

    private class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView mItemText;
        private TextView mItemAuthor;
        private ImageView mItemImage;
        private TextView mItemDes;
        private TextView mItemPrice;
        private TextView tp;
        private MaterialRatingBar bar;

        public ListViewHolder(View itemView) {

            super(itemView);
            mItemText = itemView.findViewById(R.id.Book);
            mItemAuthor = itemView.findViewById(R.id.Author);
            mItemImage = itemView.findViewById(R.id.bookimg);
            mItemDes=itemView.findViewById(R.id.des);
            mItemPrice=itemView.findViewById(R.id.Price);
            tp=itemView.findViewById(R.id.Type);
            bar=itemView.findViewById(R.id.rate_show);
        }

        public void bindView(final int position) {
            session=new SessionHandler(BaseApplication.getContext());
            final User user = session.getUserDetails();
            Cursor temp= db.get_comment(h[position]);
            temp.moveToNext();
            int tempc=temp.getCount();
            if (temp!=null&&tempc>0)
            {
                float sum=0;
                temp.moveToFirst();
                sum = sum+temp.getFloat(temp.getColumnIndex("SCORE"));
                while (temp.moveToNext()) {
                    sum = sum+temp.getFloat(temp.getColumnIndex("SCORE"));
                }
                rate[position]=sum/(float)tempc;
            }
            else
                rate[position]= (float) 0.0;
            bar.setRating(rate[position]);
            mItemText.setText(h[position]);
            mItemAuthor.setText(d[position]);
            mItemImage.setImageResource(e[position]);
            mItemDes.setText(q[position]);
            mItemPrice.setText("Price "+String.valueOf(pri[position])+" $");
            tp.setText(type[position]);
            final TextView aq;
            Button detail;
            Button buy;
            TextView tp;
            aq = itemView.findViewById(R.id.des);
            buy= itemView.findViewById(R.id.buynow);
            detail = itemView.findViewById(R.id.Detail);
            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (aq.getVisibility() == View.GONE) {
                        aq.setVisibility(View.VISIBLE);
                    } else
                        aq.setVisibility(View.GONE);
                }
            });
            buy.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    if (db.check_book(user.getUsername(),h[position]).getCount()>0)
                    {
                        Toast.makeText(BaseApplication.getContext(),"You have had this book!Purchase failure!",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Date date = new Date();
                        String res=db.changeBalance(user.getUsername(),pri[position]);
                        Toast.makeText(BaseApplication.getContext(), res, Toast.LENGTH_SHORT).show();
                        if (res=="Success!") {
                            db.insertData(user.getUsername(), h[position], pri[position], date.toString());
                            Toast.makeText(BaseApplication.getContext(), "success!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            Button com=itemView.findViewById(R.id.COMMENT_Detail);
            com.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(BaseApplication.getContext(), Comment.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("bookname",h[position]);
                    startActivity(BaseApplication.getContext(),intent,null);
                }
            });
        }

    }
}
