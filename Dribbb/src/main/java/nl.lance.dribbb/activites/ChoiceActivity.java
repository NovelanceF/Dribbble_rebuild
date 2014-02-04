package nl.lance.dribbb.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import nl.lance.dribbb.R;

public class ChoiceActivity extends SwipeBackActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choice);

    Button shotsButton = (Button)findViewById(R.id.choice_shots);
    Button playerButton = (Button)findViewById(R.id.choice_player);

    shotsButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ChoiceActivity.this, DActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("is", "Shots");
        intent.putExtras(bundle);
        startActivity(intent);
      }
    });

    playerButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ChoiceActivity.this, DActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("is", "Player");
        intent.putExtras(bundle);
        startActivity(intent);
      }
    });
   }
}
