package rs.hydra.androidtv.quiz.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import rs.hydra.androidtv.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {

    private List<User> data;
    private Context context;

    public UserAdapter(Context context, List<User> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserViewHolder userViewHolder = (UserViewHolder) holder;
        User item = getItem(position);
        userViewHolder.name.setText(item.deviceName);
    }

    public User getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    /**
     * Promo view holder
     */
    private static class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        UserViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
