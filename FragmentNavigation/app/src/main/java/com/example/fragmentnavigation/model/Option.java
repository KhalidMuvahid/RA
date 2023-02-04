package com.example.fragmentnavigation.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Option implements Parcelable {
    public int box;
    public boolean timeEnable;

    public Option(int box, boolean timeEnable){
        this.box = box;
        this.timeEnable = timeEnable;
    }

    protected Option(Parcel in) {
        box = in.readInt();
        timeEnable = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(box);
        dest.writeByte((byte) (timeEnable ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Option> CREATOR = new Creator<Option>() {
        @Override
        public Option createFromParcel(Parcel in) {
            return new Option(in);
        }

        @Override
        public Option[] newArray(int size) {
            return new Option[size];
        }
    };
}
