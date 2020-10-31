package com.example.myapplication.ui;

import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DatabaseHelperUser;
import com.example.myapplication.R;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static android.view.View.GONE;
import static androidx.core.content.ContextCompat.startActivity;


public class ListAdaBook extends RecyclerView.Adapter {
    Bookdata a = new Bookdata();
    DatabaseHelperUser db=new DatabaseHelperUser(BaseApplication.getContext());

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item2, viewGroup, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        SessionHandler session = new SessionHandler(BaseApplication.getContext());
        final User user = session.getUserDetails();
        return a.getData(user.getUsername()).getCount();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView mItemText;
        private TextView mItemAuthor;
        private ImageView mItemImage;

        public ListViewHolder(View itemView) {

            super(itemView);
            mItemText = itemView.findViewById(R.id.Book2);
            mItemAuthor = itemView.findViewById(R.id.Author2);
            mItemImage = itemView.findViewById(R.id.bookimg2);
        }

        public void bindView(final int position) {
            SessionHandler session = new SessionHandler(BaseApplication.getContext());
            final User user = session.getUserDetails();
            Cursor res=a.getData(user.getUsername());
            int count=res.getCount();
            int index=0;
            String[] result;
            res.moveToNext();
            final String[] h = new String[count];
            final String[] d = new String[count];
            final String[] txt=new String[count];
            int[] e = new int[count];
            if (res!= null) {
                res.moveToFirst();
                h[index] = res.getString(res.getColumnIndex("BOOKNAME"));
                d[index] = res.getString(res.getColumnIndex("AUTHOR"));
                e[index] = res.getInt(res.getColumnIndex("IMG"));
                txt[index]=res.getString(res.getColumnIndex("TXT"));
                index++;
                while (res.moveToNext()) {
                    h[index] = res.getString(res.getColumnIndex("BOOKNAME"));
                    d[index] = res.getString(res.getColumnIndex("AUTHOR"));
                    e[index] = res.getInt(res.getColumnIndex("IMG"));
                    txt[index]=res.getString(res.getColumnIndex("TXT"));
                    index++;
                }
            }
            mItemText.setText(h[position]);
            mItemAuthor.setText(d[position]);
            mItemImage.setImageResource(e[position]);
            Button del;
            Button read;
            Button com;
            del= itemView.findViewById(R.id.delete);
            read=itemView.findViewById(R.id.Readnow);
            com=itemView.findViewById(R.id.Comment_btn);
            final ConstraintLayout comment_layout;
            comment_layout=itemView.findViewById(R.id.comment_layout);
            del.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.deleteorder(user.getUsername(),h[position]);
                    Toast.makeText(BaseApplication.getContext(),"success!",Toast.LENGTH_SHORT).show();
                }
            });
            read.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(BaseApplication.getContext(), read_fragment.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("url",txt[position]);
                    startActivity(BaseApplication.getContext(),intent,null);
                }
            });
            com.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comment_layout.getVisibility()==GONE)
                        comment_layout.setVisibility(View.VISIBLE);
                    else
                        comment_layout.setVisibility(GONE);
                }
            });
            Button submit= itemView.findViewById(R.id.Comment_submit);
            submit.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean result;
                    TextView tx=itemView.findViewById(R.id.edit_comment);
                    String content=tx.getText().toString();
                    MaterialRatingBar rate= itemView.findViewById(R.id.materialRatingBar);
                    result=db.insert_comment(h[position],user.getFullName(),content,rate.getRating());
                    if (result)
                        Toast.makeText(BaseApplication.getContext(),"Success!",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(BaseApplication.getContext(),"Failure",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
