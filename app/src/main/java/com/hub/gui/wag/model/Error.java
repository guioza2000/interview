package com.hub.gui.wag.model;

import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("error_id")
    int errorId;

    @SerializedName("description")
    String description;

}
