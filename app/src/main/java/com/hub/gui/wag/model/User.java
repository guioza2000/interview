package com.hub.gui.wag.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * This type describes a user on a Stack Exchange site.
 * @see <a href="https://api.stackexchange.com/docs/types/user">Type user</a>
 */
@SuppressWarnings("unused")
public class User implements Parcelable{

    @SerializedName("badge_counts")
    private BadgeCount badgeCount;

    @SerializedName("account_id")
    private int accountId;

    @SerializedName("is_employee")
    private boolean isEmployee;

    @SerializedName("last_modified_date")
    private Date lastModifiedDate;

    @SerializedName("last_access_date")
    private Date lastAccessDate;

    @SerializedName("reputation_change_year")
    private int reputationChangeYear;

    @SerializedName("reputation_change_quarter")
    private int reputationChangeQuarter;

    @SerializedName("reputation_change_month")
    private int reputationChangeMonth;

    @SerializedName("reputation_change_week")
    private int reputationChangeWeek;

    @SerializedName("reputation_change_day")
    private int reputationChangeDay;

    @SerializedName("reputation")
    private int reputation;

    @SerializedName("creation_date")
    private Date creationDate;

    @SerializedName("user_type")
    private String userType;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("accept_rate")
    private int acceptRate;

    @SerializedName("location")
    private String location;

    @SerializedName("website_url")
    private String websiteUrl;

    @SerializedName("link")
    private String link;

    @SerializedName("profile_image")
    private String profileImage;

    @SerializedName("display_name")
    private String displayName;

    public User(){};

    public BadgeCount getBadgeCounts() {
        return badgeCount;
    }

    public void setBadgeCounts(BadgeCount badgeCounts) {
        this.badgeCount = badgeCounts;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean isIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(Date lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public int getReputationChangeYear() {
        return reputationChangeYear;
    }

    public void setReputationChangeYear(int reputationChangeYear) {
        this.reputationChangeYear = reputationChangeYear;
    }

    public int getReputationChangeQuarter() {
        return reputationChangeQuarter;
    }

    public void setReputationChangeQuarter(int reputationChangeQuarter) {
        this.reputationChangeQuarter = reputationChangeQuarter;
    }

    public int getReputationChangeMonth() {
        return reputationChangeMonth;
    }

    public void setReputationChangeMonth(int reputationChangeMonth) {
        this.reputationChangeMonth = reputationChangeMonth;
    }

    public int getReputationChangeWeek() {
        return reputationChangeWeek;
    }

    public void setReputationChangeWeek(int reputationChangeWeek) {
        this.reputationChangeWeek = reputationChangeWeek;
    }

    public int getReputationChangeDay() {
        return reputationChangeDay;
    }

    public void setReputationChangeDay(int reputationChangeDay) {
        this.reputationChangeDay = reputationChangeDay;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAcceptRate() {
        return acceptRate;
    }

    public void setAcceptRate(int acceptRate) {
        this.acceptRate = acceptRate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    protected User(Parcel in) {
        badgeCount = (BadgeCount) in.readValue(BadgeCount.class.getClassLoader());
        accountId = in.readInt();
        isEmployee = in.readByte() != 0x00;
        long tmpLastModifiedDate = in.readLong();
        lastModifiedDate = tmpLastModifiedDate != -1 ? new Date(tmpLastModifiedDate) : null;
        long tmpLastAccessDate = in.readLong();
        lastAccessDate = tmpLastAccessDate != -1 ? new Date(tmpLastAccessDate) : null;
        reputationChangeYear = in.readInt();
        reputationChangeQuarter = in.readInt();
        reputationChangeMonth = in.readInt();
        reputationChangeWeek = in.readInt();
        reputationChangeDay = in.readInt();
        reputation = in.readInt();
        long tmpCreationDate = in.readLong();
        creationDate = tmpCreationDate != -1 ? new Date(tmpCreationDate) : null;
        userType = in.readString();
        userId = in.readInt();
        acceptRate = in.readInt();
        location = in.readString();
        websiteUrl = in.readString();
        link = in.readString();
        profileImage = in.readString();
        displayName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(badgeCount);
        dest.writeInt(accountId);
        dest.writeByte((byte) (isEmployee ? 0x01 : 0x00));
        dest.writeLong(lastModifiedDate != null ? lastModifiedDate.getTime() : -1L);
        dest.writeLong(lastAccessDate != null ? lastAccessDate.getTime() : -1L);
        dest.writeInt(reputationChangeYear);
        dest.writeInt(reputationChangeQuarter);
        dest.writeInt(reputationChangeMonth);
        dest.writeInt(reputationChangeWeek);
        dest.writeInt(reputationChangeDay);
        dest.writeInt(reputation);
        dest.writeLong(creationDate != null ? creationDate.getTime() : -1L);
        dest.writeString(userType);
        dest.writeInt(userId);
        dest.writeInt(acceptRate);
        dest.writeString(location);
        dest.writeString(websiteUrl);
        dest.writeString(link);
        dest.writeString(profileImage);
        dest.writeString(displayName);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
