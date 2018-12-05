package rs.hydra.androidtv.quiz.score;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;
import rs.hydra.androidtv.R;
import rs.hydra.androidtv.quiz.user.User;

import java.util.List;

public class ScoreActivity extends FragmentActivity {

    private ImageView cup1, cup2;
    private TextView name1, name2;
    private TextView point1, point2;
    private TextView title1, title2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        initView();
        showWinner(null);
    }

    private void showWinner(List<User> user) {
    }

    private void initView() {
        cup1 = findViewById(R.id.cupPlayer1);
        cup2 = findViewById(R.id.cupPlayer2);
        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        point1 = findViewById(R.id.points1);
        point2 = findViewById(R.id.points2);
        title1 = findViewById(R.id.title1);
        title2 = findViewById(R.id.title2);
    }
}
