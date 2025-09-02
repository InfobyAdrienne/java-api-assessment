package com.entrepreneurfunding.funding;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.entrepreneurfunding.funding.enums.FundingStatus;
import com.entrepreneurfunding.funding.enums.IndustryFocus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "funding_opportunities")


public class FundingOpportunity {
  @Id
  @GeneratedValue
  
  private UUID id;
  private String provider;
  private IndustryFocus industryFocus;
  private BigDecimal minimumAmount;
  private BigDecimal maximumAmount;
  private Instant updatedAt;
  private FundingStatus fundingStatus;

  // Define a constructor and default values
  public FundingOpportunity(String provider, IndustryFocus industryFocus, BigDecimal minimumAmount, BigDecimal maximumAmount, Instant updatedAt, FundingStatus fundingStatus) {
    this.provider = provider;
    this.industryFocus = industryFocus;
    this.minimumAmount = minimumAmount;
    this.maximumAmount = maximumAmount;
    this.updatedAt = updatedAt;
    this.fundingStatus = fundingStatus;
  }

  public FundingOpportunity() {
    this(
        "Unknown", // provider
        IndustryFocus.OTHER, // industry focus
        BigDecimal.ZERO, // minimumAmount
        BigDecimal.ZERO, // maximumAmount
        Instant.now(), // updatedAt
        FundingStatus.PENDING // default status
    );
  }
  
  // Create getter and setter methods for each field, except id, which should only have a getter
  public UUID getId() {
    return id;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public IndustryFocus getIndustryFocus() {
    return industryFocus;
  }

  public void setIndustryFocus(IndustryFocus industryFocus) {
    this.industryFocus = industryFocus;
  }

  // get maximum amount 
  public BigDecimal getMaximumAmount() {
    return maximumAmount;
  }

  // set maximum amount 
  public void setMaximumAmount(BigDecimal maximumAmount) {
    this.maximumAmount = maximumAmount;
  }
  
  // get minimumim amount 
  public BigDecimal getMinimumAmount() {
    return minimumAmount;
  }

  // set minimumim amount 
  public void setMinimumAmount(BigDecimal minimumAmount) {
    this.minimumAmount = minimumAmount;
  }

  // Updated at should be updated whever there is a change to the table, dont know how to do this? 


  // get status 
  public FundingStatus getFundingStatus() {
    return fundingStatus;
  }
  // if status is not declared, return the default PENDING

  public void setFundingStatus(FundingStatus fundingStatus) {
    this.fundingStatus = fundingStatus;
  }
}
