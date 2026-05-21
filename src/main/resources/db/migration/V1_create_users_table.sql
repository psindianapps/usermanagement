CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY_KEY,
    name       VARCHAR(255) NOT NULL,
    username   VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    gender     VARCHAR(10) NOT NULL,
    dob        DATE,
    isActice   INT  DEFAULT 0,
    isVerified INT DEFAULT 0,
    isDeleted  VARCHAR(10) DEFAULT 'N',
    createdAt  DATETIME,
    updatedAt  DATETIME,
)