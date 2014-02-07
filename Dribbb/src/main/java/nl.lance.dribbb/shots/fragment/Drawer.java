package nl.lance.dribbb.shots.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import nl.lance.dribbb.R;
import nl.lance.dribbb.activites.ChoiceActivity;
import nl.lance.dribbb.activites.UActivity;
import nl.lance.dribbb.adapter.DrawerAdapter;
import nl.lance.dribbb.network.PlayerData;

public class Drawer extends Fragment {

  private DrawerAdapter adapter;
  private PlayerData data;
  private SharedPreferences userInfo;
  
  public Drawer() {
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);
    ListView listView = (ListView)rootView.findViewById(R.id.drawer_list);

    userInfo = getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
    data = new PlayerData(getActivity());
    
    adapter = new DrawerAdapter(getActivity(), data.getDrawerList());
    listView.setAdapter(adapter);
    
    listView.setOnItemClickListener(new OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
          long arg3) {
        Intent intent = new Intent(getActivity(), UActivity.class);
        Bundle bundle = new Bundle();
        if(arg2 == 0) {
          initDialog(getActivity());
        }
        if(!userInfo.getString("avatar_url", "").equals("")) {
          if (arg2 == 1) {
            bundle.putString("url", nl.lance.dribbb.network.DribbbleAPI.getUserFollowingUrl(userInfo.getString("username", "")));
            intent.putExtras(bundle);
            getActivity().startActivity(intent);
          } else if (arg2 == 2) {
            bundle.putString("url", nl.lance.dribbb.network.DribbbleAPI.getuserLikesUel(userInfo.getString("username", "")));
            intent.putExtras(bundle);
            getActivity().startActivity(intent);
          }
        }
        if (arg2 == 3) {
          Intent intent2 = new Intent(getActivity(), ChoiceActivity.class);
          getActivity().startActivity(intent2);
        }
        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
      }
    });
    return rootView;
  }
  
  public void initDialog(final Activity a) {
    View connectDialog = a.getLayoutInflater().inflate(R.layout.dialog_connect, null);
    final EditText editText = (EditText)connectDialog.findViewById(R.id.user_account);
    
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
              data.getPlayerInfo(nl.lance.dribbb.network.DribbbleAPI.getUserUrl(userInfo.getString("username", "")), adapter);
            }
          }
        }).setNegativeButton("Cancel", null).show();
  }

}
