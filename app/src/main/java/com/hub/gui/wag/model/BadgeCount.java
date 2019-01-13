package com.hub.gui.wag.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * This type represents the total Badges, segregated by rank, a user has earned.
 * @see <a href="https://api.stackexchange.com/docs/types/badge-count">Type badge-count</a>
 */
public class BadgeCount implements Parcelable {

    @SerializedName("bronze")
    private int bronze;

    @SerializedName("silver")
    private int silver;

    @SerializedName("gold")
    private int gold;

    public BadgeCount(){};

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    protected BadgeCount(Parcel in) {
        bronze = in.readInt();
        silver = in.readInt();
        gold = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bronze);
        dest.writeInt(silver);
        dest.writeInt(gold);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BadgeCount> CREATOR = new Parcelable.Creator<BadgeCount>() {
        @Override
        public BadgeCount createFromParcel(Parcel in) {
            return new BadgeCount(in);
        }

        @Override
        public BadgeCount[] newArray(int size) {
            return new BadgeCount[size];
        }
    };
}
