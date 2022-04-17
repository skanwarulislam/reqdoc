package com.example.app.country.model;

public class Document {

  private final int mId;
  private final String mCode;
  private final String mDescription;
  private final boolean mRequired;

  public Document(int id, String code, String description, boolean required) {
    this.mId = id;
    this.mCode = code;
    this.mDescription = description;
    this.mRequired = required;
  }

  public int getId() {
    return mId;
  }

  public String getCode() {
    return mCode;
  }

  public String getDescription() {
    return mDescription;
  }

  public boolean isRequired() {
    return mRequired;
  }
}
