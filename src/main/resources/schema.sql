DROP TABLE IF EXISTS funding_opportunities;

CREATE TABLE funding_opportunities (
    id BINARY(16) PRIMARY KEY,
    provider VARCHAR(255) NOT NULL,
    industry_focus ENUM('TECH','HEALTHCARE','EDUCATION','FINANCE','RETAIL','AGRICULTURE','ENVIRONMENT','OTHER') NOT NULL,
    minimum_amount DECIMAL(38,2) NOT NULL,
    maximum_amount DECIMAL(38,2) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    funding_status ENUM('ACTIVE','INACTIVE','PENDING') NOT NULL
);