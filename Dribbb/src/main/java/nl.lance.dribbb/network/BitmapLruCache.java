package nl.lance.dribbb.network;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

  public BitmapLruCache() {
    super(15*1024*1024);
  }
  
  @Override
  protected int sizeOf(String key, Bitmap value) {
    return super.sizeOf(key, value);
  }
  
  @Override
  public Bitmap getBitmap(String url) {
      return get(url);
  }

  @Override
  public void putBitmap(String url, Bitmap bitmap) {
      put(url, bitmap);
  }

}
