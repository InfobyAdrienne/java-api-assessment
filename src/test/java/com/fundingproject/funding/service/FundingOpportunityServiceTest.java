package com.fundingproject.funding.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fundingproject.funding.entity.FundingOpportunity;
import com.fundingproject.funding.repository.FundingOpportunityRepository;

import com.fundingproject.funding.enums.FundingStatus;
import com.fundingproject.funding.enums.IndustryFocus;

public class FundingOpportunityServiceTest {

  private FundingOpportunityService fundingOpportunityService;
  private FundingOpportunityRepository mockFundingOpportunityRepository;
  private FundingOpportunity opportunity1, opportunity2;

  @BeforeEach
  void setUp() {
    mockFundingOpportunityRepository = Mockito.mock(FundingOpportunityRepository.class);
    fundingOpportunityService = new FundingOpportunityService(mockFundingOpportunityRepository);
    opportunity1 = new FundingOpportunity("Tech Fund Provider", IndustryFocus.TECH, BigDecimal.valueOf(1000.00),
        BigDecimal.valueOf(5000.00), Instant.now(), FundingStatus.ACTIVE);
    opportunity2 = new FundingOpportunity("Health Fund Provider", IndustryFocus.HEALTHCARE,
        BigDecimal.valueOf(20000.00), BigDecimal.valueOf(6000.00),
        Instant.now(), FundingStatus.PENDING);
  }

  @Test
  void testGetAllFundingOpportunitiesInitiallyEmpty() {
    Mockito.when(mockFundingOpportunityRepository.findAll()).thenReturn(List.of());
    List<FundingOpportunity> opportunities = fundingOpportunityService.getAllFundingOpportunities();
    assertTrue(opportunities.isEmpty());
  }

  @Test
  void testGetAllFundingOpportunitiesInitiallyEmptyetAllFundingOpportunitiesReturnsList() {
    Mockito.when(mockFundingOpportunityRepository.findAll()).thenReturn(List.of(opportunity1, opportunity2));
    List<FundingOpportunity> opportunities = fundingOpportunityService.getAllFundingOpportunities();

    assertTrue(opportunities.size() == 2);
    assertTrue(opportunities.contains(opportunity1));
    assertTrue(opportunities.contains(opportunity2));
  }

  @Test
  void testCreateFundingOpportunitySuccessfully() {
    Mockito.when(mockFundingOpportunityRepository.save(Mockito.any(FundingOpportunity.class)))
        .thenReturn(opportunity1);
    FundingOpportunity createdOpportunity = fundingOpportunityService.createFundingOpportunity(opportunity1);
    assertTrue(createdOpportunity.equals(opportunity1));
  }

  @Test
  void testGetExistingFundingOpportunityById() {
    UUID id = UUID.randomUUID();
    opportunity1.setId(id); // Set the ID for testing purposes

    Mockito.when(mockFundingOpportunityRepository.findById(id)).thenReturn(Optional.of(opportunity1));
    FundingOpportunity foundOpportunity = fundingOpportunityService.getFundingOpportunityById(id);

    assertTrue(foundOpportunity.equals(opportunity1));
    assertTrue(foundOpportunity.getId().equals(id));
  }

  @Test
  void testUpdateExistingFundingOpportunityWithAllFields() {
    // Arrange
    UUID id = UUID.randomUUID();
    opportunity1.setId(id); // Existing entity in repository

    Instant fixedInstant = Instant.parse("2025-09-04T21:39:48.811435Z");

    // Create a "new" FundingOpportunity with updated values
    FundingOpportunity updatedData = new FundingOpportunity(
        "Updated Provider",
        IndustryFocus.FINANCE,
        BigDecimal.valueOf(1000),
        BigDecimal.valueOf(500),
        fixedInstant,
        FundingStatus.INACTIVE);
    updatedData.setId(id); // ID must match the existing entity

    // Mock repository behavior
    Mockito.when(mockFundingOpportunityRepository.findById(id))
        .thenReturn(Optional.of(opportunity1));
    Mockito.when(mockFundingOpportunityRepository.save(Mockito.any(FundingOpportunity.class)))
        .thenAnswer(invocation -> invocation.getArgument(0)); // return the saved entity

    // Act
    FundingOpportunity result = fundingOpportunityService.updateFundingOpportunity(id, updatedData);

    // Assert
    assertEquals(id, result.getId());
    assertEquals("Updated Provider", result.getProvider());
    assertEquals(IndustryFocus.FINANCE, result.getIndustryFocus());
    assertEquals(BigDecimal.valueOf(1000), result.getMinimumAmount());
    assertEquals(BigDecimal.valueOf(500), result.getMaximumAmount());
    assertEquals(FundingStatus.INACTIVE, result.getFundingStatus());
    assertEquals(fixedInstant, result.getUpdatedAt());
  }

  @Test
  void testDeleteExistingFundingOpportunity() {
    UUID id = UUID.randomUUID();

    // Mock that the entity exists before deletion
    Mockito.when(mockFundingOpportunityRepository.existsById(id)).thenReturn(true);

    // Call the service
    fundingOpportunityService.deleteFundingOpportunity(id);

    // Verify that deleteById was called
    Mockito.verify(mockFundingOpportunityRepository).deleteById(id);

    // Optional: verify that the service would now behave as if entity is gone
    Mockito.when(mockFundingOpportunityRepository.existsById(id)).thenReturn(false);
    assertFalse(mockFundingOpportunityRepository.existsById(id));
  }
}