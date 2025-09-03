package com.fundingproject.funding.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.fundingproject.funding.enums.FundingStatus;
import com.fundingproject.funding.enums.IndustryFocus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "funding_opportunities")

public class FundingOpportunity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(columnDefinition = "CHAR(36)")
  private UUID id;

  private String provider;

  @Enumerated(EnumType.STRING)
  private IndustryFocus industryFocus;

  private BigDecimal minimumAmount;
  private BigDecimal maximumAmount;
  private Instant updatedAt;

  @PrePersist
  protected void onCreate() {
    this.updatedAt = Instant.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = Instant.now();
  }

  @Enumerated(EnumType.STRING)
  private FundingStatus fundingStatus;

  // Define a constructor and default values
  public FundingOpportunity(String provider, IndustryFocus industryFocus, BigDecimal minimumAmount,
      BigDecimal maximumAmount, Instant updatedAt, FundingStatus fundingStatus) {
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

  // Create getter and setter methods for each field, except id and updatedAt which only have getters
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

  public BigDecimal getMaximumAmount() {
    return maximumAmount;
  }

  public void setMaximumAmount(BigDecimal maximumAmount) {
    this.maximumAmount = maximumAmount;
  }

  public BigDecimal getMinimumAmount() {
    return minimumAmount;
  }

  public void setMinimumAmount(BigDecimal minimumAmount) {
    this.minimumAmount = minimumAmount;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public FundingStatus getFundingStatus() {
    return fundingStatus;
  }

  public void setFundingStatus(FundingStatus fundingStatus) {
    this.fundingStatus = fundingStatus;
  }
}
