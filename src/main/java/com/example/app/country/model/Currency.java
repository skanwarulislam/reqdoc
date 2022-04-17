package com.example.app.country.model;

import com.example.app.country.deserializer.CurrencyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@JsonDeserialize(using = CurrencyDeserializer.class)
public class Currency implements Comparable<Currency> {

  private final String mCurrency;
  private final String mCountryName;

  public Currency(@NotNull final String mCurrency, @NotNull final String mCountry) {
    this.mCurrency = mCurrency;
    this.mCountryName = mCountry;
  }

  public String getCurrency() {
    return mCurrency;
  }

  public String getCountryName() {
    return mCountryName;
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
    Currency otherCountry = (Currency) otherObj;
    return new EqualsBuilder()
        .append(this.mCurrency, otherCountry.mCurrency)
        .append(this.mCountryName, otherCountry.mCountryName)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(11, 31)
        .append(this.mCurrency)
        .append(this.mCountryName)
        .toHashCode();
  }

  @Override
  public int compareTo(@NotNull Currency otherCurrency) {
    return this.mCurrency.compareTo(otherCurrency.mCurrency);
  }
}
