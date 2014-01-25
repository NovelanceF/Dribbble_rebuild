package com.lance.dribbb.views;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;

import com.lance.dribbb.R;

public class ConnectDialog {
  
  public static void initDialog(final Activity a) {
    View connectDialog = a.getLayoutInflater().inflate(R.layout.dialog_connect, null);
    final EditText editText = (EditText)connectDialog.findViewById(R.id.user_account);
    
    final SharedPreferences userInfo = a.getSharedPreferences("user_info", Context.MODE_PRIVATE); 
    if(userInfo.getString("username", "") != ""){
      editText.setText(userInfo.getString("username", ""));
    }
    
    new AlertDialog.Builder(a)
    .setView(connectDialog)
    .setPositiveButton("Ok",
        new DialogInterface.OnClickListener() {

          @Override
          public void onClick(DialogInterface dialog, int which) {
            if(editText.getText().toString() != "") {
              userInfo.edit().putString("username", editText.getText().toString()).commit();
            }
          }
        }).setNegativeButton("Cancel", null).show();
  }
}
