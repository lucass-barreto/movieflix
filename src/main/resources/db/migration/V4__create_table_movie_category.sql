CREATE TABLE movie_category
(
    movie_id    BIGINT,
    category_id BIGINT,
    CONSTRAINT fk_movie_category_movie FOREIGN KEY (movie_id) REFERENCES movie_tb (id),
    CONSTRAINT fk_movie_category_category FOREIGN KEY (category_id) REFERENCES category_tb (id)
);