package rs.hydra.androidtv.quiz.score;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import rs.hydra.androidtv.R;
import rs.hydra.androidtv.quiz.user.User;

import java.util.List;

public class ScoreActivity extends FragmentActivity {

    public static final String USERS = "users";

    private ImageView cup1, cup2;
    private TextView name1, name2;
    private TextView point1, point2;
    private TextView title1, title2;

    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        users = getIntent().getParcelableArrayListExtra(USERS);

        initView();
        showWinner(users);
    }

    private void showWinner(List<User> user) {
        if (user.get(0).points > user.get(1).points) {
            cup1.setVisibility(View.VISIBLE);
            cup2.setVisibility(View.INVISIBLE);
            title1.setText("WINNER");
            title1.setTextColor(getResources().getColor(R.color.green));
            title2.setText("LOOSER");
            title2.setTextColor(getResources().getColor(R.color.red));
        } else {
            cup1.setVisibility(View.INVISIBLE);
            cup2.setVisibility(View.VISIBLE);
            title2.setText("WINNER");
            title2.setTextColor(getResources().getColor(R.color.green));
            title1.setText("LOOSER");
            title1.setTextColor(getResources().getColor(R.color.red));
        }
        point1.setText(user.get(0).points);
        point2.setText(user.get(1).points);
        name1.setText(user.get(0).name);
        name2.setText(user.get(1).name);
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
