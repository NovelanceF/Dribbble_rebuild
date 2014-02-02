package nl.lance.dribbb.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

/**
 * Created by Novelance on 2/2/14.
 */
public class DetailAdapter extends ContentShotsAdapter {
  public DetailAdapter(Activity c, List<Map<String, Object>> list, int p) {
    super(c, list, p);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    return super.getView(position, convertView, parent);
  }
}
