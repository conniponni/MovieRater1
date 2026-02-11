CREATE TABLE IF NOT EXISTS review (
                                      id SERIAL PRIMARY KEY,
                                      movie_title VARCHAR(255) NOT NULL,
    reviewer_name VARCHAR(100) NOT NULL,
    rating INT NOT NULL CHECK (rating >= 1 AND rating <= 10),
    comment VARCHAR(1000) NOT NULL,
    created_at DATE NOT NULL
    );