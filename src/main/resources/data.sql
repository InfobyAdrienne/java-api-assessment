INSERT INTO funding_opportunities (
    id, provider, industry_focus, minimum_amount, maximum_amount, updated_at, funding_status
) VALUES
    (CAST('1111111111111111' AS BINARY(16)), 'Comp Corp', 'TECH', 50000.00, 200000.00, NOW(), 'PENDING'),
    (CAST('2222222222222222' AS BINARY(16)), 'Global Health Fund', 'HEALTHCARE', 100000.00, 500000.00, NOW(), 'ACTIVE'),
    (CAST('3333333333333333' AS BINARY(16)), 'Eco Ventures', 'ENVIRONMENT', 25000.00, 150000.00, NOW(), 'INACTIVE'),
    (CAST('4444444444444444' AS BINARY(16)), 'EduTech Innovations', 'EDUCATION', 30000.00, 120000.00, NOW(), 'ACTIVE'),
    (CAST('5555555555555555' AS BINARY(16)), 'FinTech Solutions', 'FINANCE', 50000.00, 250000.00, NOW(), 'PENDING'),
    (CAST('6666666666666666' AS BINARY(16)), 'Agri Pioneers', 'AGRICULTURE', 20000.00, 100000.00, NOW(), 'ACTIVE');


