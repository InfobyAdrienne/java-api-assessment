package com.fundingproject.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.fundingproject.entity.FundingOpportunity;

import java.util.UUID;

public interface FundingOpportunityRepository extends ListCrudRepository<FundingOpportunity, UUID> {
  
}
