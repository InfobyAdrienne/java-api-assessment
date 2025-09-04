package com.fundingproject.funding.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.fundingproject.funding.entity.FundingOpportunity;
import com.fundingproject.funding.enums.FundingStatus;
import com.fundingproject.funding.repository.FundingOpportunityRepository;

@Service
public class FundingOpportunityService {

  public final FundingOpportunityRepository fundingOpportunityRepository;

  public FundingOpportunityService(FundingOpportunityRepository fundingOpportunityRepository) {
    this.fundingOpportunityRepository = fundingOpportunityRepository;
  }

  public List<FundingOpportunity> getAllFundingOpportunities() {
    return fundingOpportunityRepository.findAll();
  }

  public FundingOpportunity getFundingOpportunityById(UUID id) throws NoSuchElementException {
    return fundingOpportunityRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Funding opportunity not found - check id"));
  }

  public FundingOpportunity createFundingOpportunity(FundingOpportunity fundingOpportunity)
      throws IllegalArgumentException, OptimisticLockingFailureException {
    if (fundingOpportunity == null) {
      throw new IllegalArgumentException("Funding opportunity cannot be null");
    }
    return fundingOpportunityRepository.save(fundingOpportunity);
  }

  public FundingOpportunity updateFundingOpportunity(UUID id, FundingOpportunity updatedFundingOpportunity)
      throws NoSuchElementException {
    if (updatedFundingOpportunity == null) {
      throw new IllegalArgumentException("Updated funding opportunity cannot be null");
    }

    // Check if the opportunity exists exists
    FundingOpportunity existingFundingOpportunity = getFundingOpportunityById(id);

    // Update the fields
    // We do not want to update the id or updatedAt fields
    existingFundingOpportunity.setProvider(updatedFundingOpportunity.getProvider());
    existingFundingOpportunity.setIndustryFocus(updatedFundingOpportunity.getIndustryFocus());
    existingFundingOpportunity.setMinimumAmount(updatedFundingOpportunity.getMinimumAmount());
    existingFundingOpportunity.setMaximumAmount(updatedFundingOpportunity.getMaximumAmount());
    existingFundingOpportunity.setFundingStatus(updatedFundingOpportunity.getFundingStatus());

    return fundingOpportunityRepository.save(existingFundingOpportunity);
  }

  public void deleteFundingOpportunity(UUID id) {
    if (!fundingOpportunityRepository.existsById(id)) {
        throw new NoSuchElementException("Funding opportunity with id " + id + " not found");
    }
    fundingOpportunityRepository.deleteById(id);
}

  public List<FundingOpportunity> getByFundingStatus(FundingStatus fundingStatus) {
    return fundingOpportunityRepository.findByFundingStatus(fundingStatus);
  }
}
