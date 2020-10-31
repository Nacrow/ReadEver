package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

import androidx.annotation.Nullable;

public class DatabaseHelperUser extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="mybookstore.db";
    public static final String TABLE_NAME="user";
    public static final String COLUMN_1="ID";
    public static final String COLUMN_2="Username";
    public static final String COLUMN_3="Fullname";
    public static final String COLUMN_4="Password";
    public static final String COLUMN_5="Balance";
    public static final String TABLE_NAME_order="order_";
    public static final String BOOK_TABLE="book";
    public static final String COLUMN_1_order="ID";
    public static final String COLUMN_2_order="Username";
    public static final String COLUMN_3_order="Book";
    public static final String COLUMN_4_order="Date";
    public static final String COLUMN_5_order="Balance";
    public static final String COLUMN_1_book="ID";
    public static final String COLUMN_2_book="Bookname";
    public static final String COLUMN_3_book="Booktype";
    public static final String COLUMN_4_book="author";
    public static final String COLUMN_5_book="price";
    public static final String COLUMN_6_book="des";
    public static final String COLUMN_7_book="img";
    public static final String COLUMN_8_book="txt";
    public static final String TABLE_NAME_COMMENT="Comment";
    public static final String COMMENT_1="ID";
    public static final String COMMENT_2="Bookname";
    public static final String COMMENT_3="Content";
    public static final String COMMENT_4="AUTHOR";
    public static final String COMMENT_5="SCORE";
    public static final String COMMENT_6="DATE";
    public DatabaseHelperUser(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

        //SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,FULLNAME TEXT,PASSWORD TEXT,BALANCE INTEGER DEFAULT 0)");
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME_order+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,BOOK TEXT,DATE TEXT,BALANCE INTEGER)");
        sqLiteDatabase.execSQL("create table "+ BOOK_TABLE+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,BOOKNAME TEXT,BOOKTYPE TEXT,AUTHOR TEXT,PRICE INTEGER,DES TEXT,IMG INTEGER,TXT TEXT)");
        sqLiteDatabase.execSQL("Create table "+TABLE_NAME_COMMENT+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,BOOKNAME TEXT,CONTENT TEXT,AUTHOR TEXT,SCORE REAL,DATE TEXT)");
        ContentValues contentValues1= new ContentValues();
        contentValues1.put(COLUMN_2_book,"Romeo and Juliet");
        contentValues1.put(COLUMN_3_book,"Tragedy");
        contentValues1.put(COLUMN_4_book,"William Shakespeare");
        contentValues1.put(COLUMN_5_book,3);
        contentValues1.put(COLUMN_6_book,"Romeo and Juliet is a tragedy written by William Shakespeare early in his career about two young star-crossed lovers whose deaths ultimately reconcile their feuding families. It was among Shakespeare's most popular plays during his lifetime and along with Hamlet, is one of his most frequently performed plays. Today, the title characters are regarded as archetypal young lovers.");
        contentValues1.put(COLUMN_7_book, R.drawable.romeo);
        contentValues1.put(COLUMN_8_book,"Romeo and Juliet.txt");
        sqLiteDatabase.insert(BOOK_TABLE,null,contentValues1);
        ContentValues contentValues2= new ContentValues();
        contentValues2.put(COLUMN_2_book,"Othello");
        contentValues2.put(COLUMN_3_book,"Tragedy");
        contentValues2.put(COLUMN_4_book,"William Shakespeare");
        contentValues2.put(COLUMN_5_book,3);
        contentValues2.put(COLUMN_6_book,"Othello (The Tragedy of Othello, the Moor of Venice) is a tragedy by William Shakespeare, believed to have been written in 1603. It is based on the story Un Capitano Moro (\"A Moorish Captain\") by Cinthio (a disciple of Boccaccio's), first published in 1565.[1] The story revolves around its two central characters: Othello, a Moorish general in the Venetian army, and his treacherous ensign, Iago. Given its varied and enduring themes of racism, love, jealousy, betrayal, revenge, and repentance, Othello is still often performed in professional and community theatre alike, and has been the source for numerous operatic, film, and literary adaptations.");
        contentValues2.put(COLUMN_7_book, R.drawable.othello);
        contentValues2.put(COLUMN_8_book,"othello.txt");
        sqLiteDatabase.insert(BOOK_TABLE,null,contentValues2);
        ContentValues contentValues3= new ContentValues();
        contentValues3.put(COLUMN_2_book,"Alice Adventures in Wonderland");
        contentValues3.put(COLUMN_3_book,"Fantasy");
        contentValues3.put(COLUMN_4_book,"Lewis Carroll");
        contentValues3.put(COLUMN_5_book,4);
        contentValues3.put(COLUMN_6_book,"Alice Adventures in Wonderland (commonly shortened to Alice in Wonderland) is an 1865 novel by English author Lewis Carroll (the pseudonym of Charles Dodgson). It tells of a young girl named Alice, who falls through a rabbit hole into a subterranean fantasy world populated by peculiar, anthropomorphic creatures. It is considered to be one of the best examples of the literary nonsense genre. The tale plays with logic, giving the story lasting popularity with adults as well as with children.");
        contentValues3.put(COLUMN_7_book, R.drawable.alice);
        contentValues3.put(COLUMN_8_book,"Alice.txt");
        sqLiteDatabase.insert(BOOK_TABLE,null,contentValues3);
        ContentValues contentValues4= new ContentValues();
        contentValues4.put(COLUMN_2_book,"Pride and Prejudice");
        contentValues4.put(COLUMN_3_book,"Romance");
        contentValues4.put(COLUMN_4_book,"Jane Austen");
        contentValues4.put(COLUMN_5_book,3);
        contentValues4.put(COLUMN_6_book,"Pride and Prejudice is a romantic novel of manners written by Jane Austen in 1813. The novel follows the character development of Elizabeth Bennet, the dynamic protagonist of the book who learns about the repercussions of hasty judgments and comes to appreciate the difference between superficial goodness and actual goodness. Its humour lies in its honest depiction of manners, education, marriage, and money during the Regency era in Great Britain.");
        contentValues4.put(COLUMN_7_book, R.drawable.pride);
        contentValues4.put(COLUMN_8_book,"Pride and Prejudice.txt");
        sqLiteDatabase.insert(BOOK_TABLE,null,contentValues4);
        ContentValues contentValues5= new ContentValues();
        contentValues5.put(COLUMN_2_book,"The Adventures of Sherlock Holmes");
        contentValues5.put(COLUMN_3_book,"Detective");
        contentValues5.put(COLUMN_4_book,"Arthur Conan Doyle");
        contentValues5.put(COLUMN_5_book,5);
        contentValues5.put(COLUMN_6_book,"The Adventures of Sherlock Holmes is a collection of twelve short stories by Arthur Conan Doyle, first published on 14 October 1892. It contains the earliest short stories featuring the consulting detective Sherlock Holmes, which had been published in twelve monthly issues of The Strand Magazine from July 1891 to June 1892. The stories are collected in the same sequence, which is not supported by any fictional chronology. The only characters common to all twelve are Holmes and Dr. Watson and all are related in first-person narrative from Watson's point of view.");
        contentValues5.put(COLUMN_7_book, R.drawable.sherlock);
        contentValues5.put(COLUMN_8_book,"The Adventures of Sherlock Holmes.txt");
        sqLiteDatabase.insert(BOOK_TABLE,null,contentValues5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_order);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+BOOK_TABLE);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String username,String fullname,String password)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_2,username);
        contentValues.put(COLUMN_3,fullname);
        contentValues.put(COLUMN_4,password);
        long result=db.insert(TABLE_NAME,null,contentValues);
        db.close();
        if (result ==-1)
            return false;
        else
            return true;
    }
    public Cursor loginquery(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * From "+ TABLE_NAME +" WHERE USERNAME='"+username+"' AND PASSWORD='"+password+"'",null);
        return res;
    }
    public Cursor inquery(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * From "+ TABLE_NAME+" WHERE USERNAME='"+username+"'",null);
        return res;
    }
    public Boolean Updatepwd(String username,String newpassword)
    {
        ContentValues values = new ContentValues();

        values.put(COLUMN_4, newpassword);

        SQLiteDatabase db = this.getWritableDatabase();

        long result= db.update(TABLE_NAME, values,  COLUMN_2+ "='" + username+"'", null);

        db.close();
        if (result==-1)
            return false;
        else
            return true;
    }
    public String changeBalance(String username,int price)
    {
        int balance;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=inquery(username);
        res.moveToFirst();
        balance=res.getInt(res.getColumnIndex("BALANCE"))-price;
        if (balance<0)
        {
            return "Insufficient Balance. Failure.";
        }
        else
        {
            ContentValues values = new ContentValues();

            values.put(COLUMN_5, balance);

            long result= db.update(TABLE_NAME, values,  COLUMN_2+ "='" + username+"'", null);
            if (result==-1)
                return "Failure";
            else
                return "Success!";
        }
    }
    public boolean insertData(String username,String bookname,int price,String date)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_2_order,username);
        contentValues.put(COLUMN_3_order,bookname);
        contentValues.put(COLUMN_4_order,date);
        contentValues.put(COLUMN_5_order,price);
        long result=db.insert(TABLE_NAME_order,null,contentValues);
        if (result ==-1)
            return false;
        else
            return true;
    }
    public Cursor orderquery(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * From "+ TABLE_NAME_order +" WHERE USERNAME='"+username+"'",null);
        return res;
    }
    public void deleteorder(String username,String bookname)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLE_NAME_order+" WHERE USERNAME = '"+username+"' AND" +
                " BOOK= '"+bookname+"'");
    }
    public Cursor check_book(String username,String bookname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * From "+ TABLE_NAME_order +" WHERE USERNAME='"+username+"' AND BOOK='"+bookname+"'",null);
        return res;
    }
    public Cursor inquery_order(String username)
    {
        int a=0;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * From "+ TABLE_NAME_order +","+BOOK_TABLE+" WHERE "+TABLE_NAME_order+".USERNAME='"+username+"' AND "+TABLE_NAME_order+".BOOK="+BOOK_TABLE+".BOOKNAME",null);
        return res;
    }
    public Cursor allbook()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * From "+ BOOK_TABLE ,null);
        return res;
    }
    public boolean insert_comment(String bookname,String author,String content,double score)
    {
        Date date=new Date();
        String a=date.toString();
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COMMENT_2,bookname);
        contentValues.put(COMMENT_3,content);
        contentValues.put(COMMENT_4,author);
        contentValues.put(COMMENT_5,score);
        contentValues.put(COMMENT_6,a);
        long result=db.insert(TABLE_NAME_COMMENT,null,contentValues);
        if (result ==-1)
            return false;
        else
            return true;
    }
    public Cursor get_comment(String bookname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * From "+ TABLE_NAME_COMMENT+" WHERE "+COMMENT_2+"='"+bookname+"'",null);
        return res;
    }
    public Cursor get_comment_user(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * From "+ TABLE_NAME_COMMENT+" WHERE "+COMMENT_4+"='"+username+"'",null);
        return res;
    }
}

