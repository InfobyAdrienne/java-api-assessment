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
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "funding_opportunities")

public class FundingOpportunity {
  @Id
  @GeneratedValue
  @Column(name = "id", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
  private UUID id;

  @Column(name = "provider", nullable = false, length = 255)
  private String provider;

  @Enumerated(EnumType.STRING)
  @Column(name = "industryFocus", nullable = false, length = 50)
  private IndustryFocus industryFocus;

  @Column(name = "minimumAmount", nullable = false)
  private BigDecimal minimumAmount;

  @Column(name = "maximumAmount", nullable = false)
  private BigDecimal maximumAmount;

  @Column(name = "updatedAt", nullable = false)
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
  @Column(name = "fundingStatus", nullable = false, length = 50)
  private FundingStatus fundingStatus;

  // Define a constructor and default values
  public FundingOpportunity(String provider, IndustryFocus industryFocus, BigDecimal zero,
      BigDecimal zero2, Instant updatedAt, FundingStatus fundingStatus) {
    this.provider = provider;
    this.industryFocus = industryFocus;
    this.minimumAmount = zero;
    this.maximumAmount = zero2;
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

  // Create getter and setter methods for each field, except id and updatedAt
  // which only have getters
  public UUID getId() {
    return id;
  }

  // Created the setter for testing purposes. Is there a better way to do this?
  // Had to make it public
  public void setId(UUID id) {
    this.id = id;
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
