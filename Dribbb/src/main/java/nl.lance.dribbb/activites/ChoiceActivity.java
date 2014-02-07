package nl.lance.dribbb.activites;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import nl.lance.dribbb.R;

public class ChoiceActivity extends SwipeBackActivity {

  Typeface typeface;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choice);

    typeface = Typeface.createFromAsset(getAssets(), "font/Roboto-Thin.ttf");

    Button shotsButton = (Button)findViewById(R.id.choice_shots);
    Button playerButton = (Button)findViewById(R.id.choice_player);
    TextView textView = (TextView)findViewById(R.id.collection_label);

    shotsButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ChoiceActivity.this, DActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("is", "Shots");
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
      }
    });

    shotsButton.setTypeface(typeface);

    playerButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ChoiceActivity.this, DActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("is", "Player");
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
      }
    });

    playerButton.setTypeface(typeface);

    typeface = Typeface.createFromAsset(getAssets(), "font/Roboto-Light.ttf");
    textView.setTypeface(typeface);
   }
}
