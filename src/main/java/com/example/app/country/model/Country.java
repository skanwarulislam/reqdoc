package com.example.app.country.model;

import com.example.app.country.deserializer.CountryDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;

/***
 * This is model pojo to be used in serialize and deserialize json response from coin direct api
 * with strip down properties. Only keys now takes from the response are name, defaultCurrency, documents,
 * options.maximumWithdrawal
 */

@JsonDeserialize(using = CountryDeserializer.class)
public class Country implements Comparable<Country> {

  private final String mName;
  private final String mCurrency;
  private final ArrayList<Document> mRequiredDocuments;
  private final int mMaxWithdrawal;

  public Country(final String pName, final String pCurrency,
      final ArrayList<Document> pRequiredDocuments, final int pMaxWithdrawal) {
    this.mName = pName;
    this.mCurrency = pCurrency;
    this.mRequiredDocuments = pRequiredDocuments;
    this.mMaxWithdrawal = pMaxWithdrawal;
  }

  public String getName() {
    return mName;
  }

  public String getCurrency() {
    return mCurrency;
  }

  public ArrayList<Document> getRequiredDocuments() {
    return mRequiredDocuments;
  }

  public int getMaxWithdrawal() {
    return mMaxWithdrawal;
  }

  @Override
  public int compareTo(@NotNull Country otherCountry) {
    return this.mName.compareTo(otherCountry.getName());
  }

  @Override
  public boolean equals(Object otherObj) {
    if (otherObj == null) {
      return false;
    }
    if (otherObj == this) {
      return true;
    }
    if (otherObj.getClass() != getClass()) {
      return false;
    }
    Country otherCountry = (Country) otherObj;
    return new EqualsBuilder()
        .append(this.mName, otherCountry.mName)
        .append(this.mCurrency, otherCountry.mCurrency)
        .append(this.mRequiredDocuments.size(), otherCountry.mRequiredDocuments.size())
        .append(this.mMaxWithdrawal, otherCountry.mMaxWithdrawal)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(this.mName)
        .append(this.mCurrency)
        .append(this.mRequiredDocuments)
        .append(this.mMaxWithdrawal)
        .toHashCode();
  }
}
