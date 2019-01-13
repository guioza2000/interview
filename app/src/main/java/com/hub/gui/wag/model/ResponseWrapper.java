package com.hub.gui.wag.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/***
 * StackExchange Response Wrapper as define in:
 * @see <a href="https://api.stackexchange.com/docs/wrapper">wrapper</a>
 * @param <T>, the type of item requested such as {@link User}
 */
public class ResponseWrapper<T> {

    @SerializedName("backoff")
    private int backoff;

    @SerializedName("error_id")
    private int errorId;

    @SerializedName("error_message")
    private int errorMessage;

    @SerializedName("error_name")
    private int errorName;

    @SerializedName("has_more")
    private boolean hasMore;

    @SerializedName("items")
    private List<T> items = null;

    @SerializedName("quota_max")
    private int quotaMax;

    @SerializedName("quota_remaining")
    private int quotaRemaining;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(int quotaMax) {
        this.quotaMax = quotaMax;

    }

    public int getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(int quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }
}
