package rs.hydra.androidtv.quiz.user;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class User implements Parcelable {

    public String uuid = null;
    public String name = null;
    public int points = 0;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uuid);
        dest.writeString(this.name);
        dest.writeInt(this.points);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;

        return TextUtils.equals(uuid, user.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    public User() {
    }

    public User(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public User(String uuid, String name, int points) {
        this.uuid = uuid;
        this.name = name;
        this.points = points;
    }

    protected User(Parcel in) {
        this.uuid = in.readString();
        this.name = in.readString();
        this.points = in.readInt();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
