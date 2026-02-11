CREATE TABLE movies
(

    id               BIGSERIAL PRIMARY KEY,

    title            VARCHAR(100)     NOT NULL,

    genre            VARCHAR(50)      NOT NULL,

    release_year     INT              NOT NULL CHECK (release_year >= 1888 AND release_year <= 2100),

    duration_minutes INT              NOT NULL CHECK (duration_minutes > 0 AND duration_minutes <= 500),

    rating           DOUBLE PRECISION NOT NULL CHECK (rating >= 0 AND rating <= 10)

);