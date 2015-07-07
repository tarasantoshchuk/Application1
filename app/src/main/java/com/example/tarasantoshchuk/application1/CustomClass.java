package com.example.tarasantoshchuk.application1;

import android.os.Parcel;
import android.os.Parcelable;

public class CustomClass implements Parcelable {

    public static Parcelable.Creator<CustomClass> CREATOR;

    private String mStr1;
    private String mStr2;
    private String mStr3;

    public String getStr1() {
        return mStr1;
    }

    public void setStr1(String str1) {
        this.mStr1 = str1;
    }

    public String getStr2() {
        return mStr2;
    }

    public void setStr2(String str2) {
        this.mStr2 = str2;
    }

    public String getStr3() {
        return mStr3;
    }

    public void setStr3(String mStr3) {
        this.mStr3 = mStr3;
    }

    static {
        CREATOR = new Parcelable.Creator<CustomClass>() {

            @Override
            public CustomClass createFromParcel(Parcel source) {
                return new CustomClass(source);
            }

            @Override
            public CustomClass[] newArray(int size) {
                return new CustomClass[size];
            }
        };
    }

    public CustomClass(String str1, String str2, String str3) {
        this.mStr1 = str1;
        this.mStr2 = str2;
        this.mStr3 = str3;
    }

    public CustomClass(Parcel source) {
        mStr1 = source.readString();
        mStr2 = source.readString();
        mStr3 = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mStr1);
        dest.writeString(mStr2);
        dest.writeString(mStr3);

    }
}
