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
        "Unknown",      // provider
        IndustryFocus.OTHER,     // industry focus
        BigDecimal.ZERO,         // minimumAmount
        BigDecimal.ZERO,         // maximumAmount
        Instant.now(),           // updatedAt
        FundingStatus.PENDING    // default status
    );
  }

}
