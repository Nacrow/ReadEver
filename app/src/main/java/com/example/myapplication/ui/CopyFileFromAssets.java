package com.example.myapplication.ui;
import java.io.File;
 import java.io.FileOutputStream;
import java.io.InputStream;
import android.content.Context;
public class CopyFileFromAssets {
/**
  9 　　*
  10 　　* @param myContext
  11 　　* @param ASSETS_NAME 要复制的文件名
  12 　　* @param savePath 要保存的路径
  13 　　* @param saveName 复制后的文件名
  14 　　* testCopy(Context context)是一个测试例子。
  15 　　*/
public static void copy2(Context myContext, String ASSETS_NAME, String savePath, String saveName) {
    String filename = savePath + "/" + saveName;
    File dir = new File(savePath);

    if (!dir.exists())
        dir.mkdir();
    try {
        InputStream is = myContext.getAssets().open(ASSETS_NAME);
        FileOutputStream fos = new FileOutputStream(filename);
        byte[] buffer = new byte[7168];
        int count = 0;
        while ((count = is.read(buffer)) > 0) {
            fos.write(buffer, 0, count);
        }
        fos.close();
        is.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void testCopy(Context context) {
String path=context.getFilesDir().getAbsolutePath();
String name="test.txt";
//CopyFileFromAssets.copy(context, name, path, name);
}
}