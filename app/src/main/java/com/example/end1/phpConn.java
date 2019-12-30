package com.example.end1;
import android.net.Uri;
import android.util.Log;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpPost;
import com.koushikdutta.async.http.AsyncHttpResponse;

public class phpConn {
      // variable type String null (static)
    static String answer = null;
         // get variable
    static public String getAnswer(){return answer;}
       // link
    public void urlCon(String link)
    {
        Log.v("Abdalaziz", " trying: " + link);
        AsyncHttpClient.getDefaultInstance().executeString(
                new AsyncHttpPost(Uri.parse(link)), new AsyncHttpClient.StringCallback() {
                    @Override
                    public void onCompleted(Exception e, AsyncHttpResponse response, String result) {
                        if (e != null) {
                            e.printStackTrace();
                            Log.v("Abdalaziz:", "link failed...");
                            return;
                        }
                        //if static no wait needed
                        answer = result;
                    }
                });
    }
}

