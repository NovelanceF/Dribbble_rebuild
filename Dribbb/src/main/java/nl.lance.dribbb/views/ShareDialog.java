package nl.lance.dribbb.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.twitter.Twitter;
import nl.lance.dribbb.R;

public class ShareDialog {

  public static void initDialog(Boolean slient, final String platForm, final Activity a) {
    View shareDialog = a.getLayoutInflater().inflate(R.layout.share_dialog, null);
    final EditText text = (EditText)shareDialog.findViewById(R.id.share);
    TextView label = (TextView)shareDialog.findViewById(R.id.label);
    label.setTypeface(Typeface.createFromAsset(a.getAssets(), "font/Roboto-Light.ttf"));
    ImageView imageView = (ImageView)shareDialog.findViewById(R.id.labelicon);
    ImageView preview = (ImageView)shareDialog.findViewById(R.id.share_preview);
    preview.setImageBitmap(getBitmap());
    final TextView textRemain = (TextView)shareDialog.findViewById(R.id.textremains);
    label.setText(platForm);

    if(platForm == Twitter.NAME) {
      label.setTextColor(0xff36bff8);
      imageView.setBackgroundResource(R.drawable.twitter2);
    } else if (platForm == SinaWeibo.NAME) {
      label.setText(platForm);
      label.setTextColor(0xffe23176);
      imageView.setBackgroundResource(R.drawable.sinaweibo);
    }

    text.addTextChangedListener(new TextWatcher() {

      @Override
      public void afterTextChanged(Editable s) {
        int count = 140 - s.length();
        textRemain.setText("Remains " + count + " Letters~");
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count,
                                    int after) {}

      @Override
      public void onTextChanged(CharSequence s, int start, int before,
                                int count) {}
    });

    new AlertDialog.Builder(a)
            .setView(shareDialog)
            .setPositiveButton("Ok",
                    new android.content.DialogInterface.OnClickListener() {

                      @Override
                      public void onClick(DialogInterface dialog,
                                          int which) {
                        if(platForm == Twitter.NAME){
                          onSharing(true, Twitter.NAME, text.getText().toString(), a);
                        } else if (platForm == SinaWeibo.NAME) {
                          onSharing(true, SinaWeibo.NAME, text.getText().toString(), a);
                        }
                      }
                    }).setNegativeButton("Cancel", null).show();
  }

  private static void onSharing(boolean silent, String platform, String things, Activity a) {
    if (things.length() <= 140) {

      final OnekeyShare oks = new OnekeyShare();
      oks.setNotification(R.drawable.ic_launcher, "Dude");
      // oks.setAddress("12345678901");
      oks.setTitleUrl("http://sharesdk.cn");
      oks.setText(things);
      oks.setImagePath("/sdcard/temp.jpg");
      oks.setUrl("http://www.sharesdk.cn");
      oks.setSiteUrl("http://sharesdk.cn");
      //oks.setVenueName("Southeast in China");
      //oks.setVenueDescription("This is a beautiful place!");
      // oks.setLatitude(23.122619f);
      // oks.setLongitude(113.372338f);
      oks.setSilent(silent);
      if (platform != null) {
        oks.setPlatform(platform);
      }
      oks.show(a);
    } else
      Toast.makeText(a, "Please edit your sharing to 140 letters or less", 2000).show();
  }

  public static void storeBitmap(final String Url, final Activity a) {
    RequestQueue requestQueue;
    requestQueue = Volley.newRequestQueue(a);
    ImageRequest imgrequest = new ImageRequest(Url,
            new Response.Listener<Bitmap>() {

              @Override
              public void onResponse(Bitmap arg0) {
                File file = new File(
                        Environment.getExternalStorageDirectory(),
                        "temp" + ".jpg");
                FileOutputStream outputStream = null;
                try {
                  outputStream = new FileOutputStream(file);
                  arg0.compress(CompressFormat.JPEG, 100,
                          outputStream);
                  outputStream.close();
                  Log.i("image","saved");
                } catch (FileNotFoundException e) {
                  e.printStackTrace();
                } catch (IOException e) {
                  e.printStackTrace();
                }
              }

            }, 800, 600, Config.ARGB_8888, new ErrorListener() {

      @Override
      public void onErrorResponse(VolleyError arg0) {
        Toast.makeText(a, "Failed", 2000).show();
        Log.i("image",Url);
      }
    });
    requestQueue.add(imgrequest);
  }

  public static Bitmap getBitmap() {
    return BitmapFactory.decodeFile("/sdcard/temp" + ".jpg");
  }

}
