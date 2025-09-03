package com.fundingproject.funding.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fundingproject.funding.entity.FundingOpportunity;
import com.fundingproject.funding.enums.FundingStatus;
import com.fundingproject.funding.service.FundingOpportunityService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")

public class FundingOpportunityController {

  private final FundingOpportunityService fundingOpportunityService;

  FundingOpportunityController(FundingOpportunityService fundingOpportunityService) {
    this.fundingOpportunityService = fundingOpportunityService;
  }

  // Get all opportunities
  @GetMapping("/funding")
  public List<FundingOpportunity> getAllFundingOpportunities() {
    return fundingOpportunityService.getAllFundingOpportunities();
  }

  // Get an opportunity by an ID
  @GetMapping("/funding/{id}")
  public ResponseEntity<FundingOpportunity> getFundingOpportunityById(@PathVariable UUID id) {
    try {
      FundingOpportunity fundingOpportunity = fundingOpportunityService.getFundingOpportunityById(id);
      return ResponseEntity.ok(fundingOpportunity);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/funding")
  public ResponseEntity<FundingOpportunity> createFundingOpportunity(
      @RequestBody FundingOpportunity fundingOpportunity) {
    try {
      FundingOpportunity createdOpportunity = fundingOpportunityService.createFundingOpportunity(fundingOpportunity);
      return ResponseEntity.status(201).body(createdOpportunity);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/funding/{id}")
  public ResponseEntity<FundingOpportunity> updateFundingOpportunity(
      @PathVariable UUID id, @RequestBody FundingOpportunity fundingOpportunity) {
    try {
      FundingOpportunity updatedOpportunity = fundingOpportunityService.updateFundingOpportunity(id,
          fundingOpportunity);
      return ResponseEntity.ok(updatedOpportunity);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/funding/{id}")
  public ResponseEntity<Void> deleteFundingOpportunity(@PathVariable UUID id) {
    fundingOpportunityService.deleteFundingOpportunity(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/funding/status/{status}")
    public List<FundingOpportunity> getByFundingStatus(@PathVariable String status) {
          FundingStatus fundingStatus = FundingStatus.valueOf(status.toUpperCase());
      return fundingOpportunityService.getByFundingStatus(fundingStatus);
    }
}
