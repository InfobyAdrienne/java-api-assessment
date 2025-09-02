package com.entrepreneurfunding.funding;

import org.springframework.data.repository.ListCrudRepository;
import java.util.UUID;

public interface FundingOpportunityRepository extends ListCrudRepository<FundingOpportunity, UUID> {
  
}
