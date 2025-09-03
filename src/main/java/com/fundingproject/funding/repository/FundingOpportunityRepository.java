package com.fundingproject.funding.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.fundingproject.funding.entity.FundingOpportunity;

import java.util.UUID;

public interface FundingOpportunityRepository extends ListCrudRepository<FundingOpportunity, UUID> {
  
}
