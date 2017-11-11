package bpp.kelvian.com;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kelvian on 5/5/17.
 */

public class item1 implements Parcelable {

    public String title;
    public String description;
    public boolean isExpanded;

    public item1(){}

    public item1(Parcel in){
        title = in.readString();
        description = in.readString();
        isExpanded = in.readInt() == 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(isExpanded ? 1 : 0);
    }
    public static final Parcelable.Creator<item1> CREATOR = new Parcelable.Creator<item1>(){
        @Override
        public item1 createFromParcel(Parcel source) {
            return new item1(source);
        }

        @Override
        public item1[] newArray(int size) {
            return new item1[size];
        }
    };

}
