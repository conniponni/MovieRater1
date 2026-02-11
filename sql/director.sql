CREATE TABLE director
(
    director_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    birthYear BIGINT NOT NULL,
    nationality VARCHAR(400) NOT NULL,
    numberOfMoviesDirected BIGINT NOT NULL,
    active BOOLEAN NOT NULL
);