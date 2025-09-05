CREATE TABLE funding_opportunity (
    id BINARY(16) PRIMARY KEY,
    provider VARCHAR(255) NOT NULL,
    industry_focus VARCHAR(100) NOT NULL,
    minimum_amount DECIMAL(11, 2) NOT NULL,
    maximum_amount DECIMAL(11, 2) NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    funding_status VARCHAR(50) NOT NULL
);