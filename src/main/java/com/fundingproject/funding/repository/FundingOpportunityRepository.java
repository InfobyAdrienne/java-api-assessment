package com.fundingproject.funding.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.fundingproject.funding.entity.FundingOpportunity;
import com.fundingproject.funding.enums.FundingStatus;

import java.util.List;
import java.util.UUID;

public interface FundingOpportunityRepository extends ListCrudRepository<FundingOpportunity, UUID> {

  List<FundingOpportunity> findByFundingStatus(FundingStatus fundingStatus);
  
  
}
