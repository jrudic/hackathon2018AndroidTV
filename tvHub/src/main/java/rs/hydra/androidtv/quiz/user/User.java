package rs.hydra.androidtv.quiz.user;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class User implements Parcelable {

    public String deviceName = null;
    public String name = null;
    public int points = 0;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.deviceName);
        dest.writeString(this.name);
        dest.writeInt(this.points);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;

        return TextUtils.equals(deviceName, user.deviceName);
    }

    @Override
    public int hashCode() {
        return deviceName.hashCode();
    }

    public User() {
    }

    public User( String deviceName) {
        this.deviceName = deviceName;
    }

    public User(String deviceName, String name, int points) {
        this.deviceName = deviceName;
        this.name = name;
        this.points = points;
    }

    protected User(Parcel in) {
        this.deviceName = in.readString();
        this.name = in.readString();
        this.points = in.readInt();
    }

    public void increaseScore(){
        points++;
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
