CREATE TABLE movie_list
(
    id            int unsigned AUTO_INCREMENT,
    name          VARCHAR(100) NOT NULL,
    release_date  DATE         NOT NULL,
    director_name VARCHAR(30)  NOT NULL,
    box_office    BIGINT       NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO movie_list (id, name, release_date, director_name, box_office)
VALUES (1, 'Episode IV – A New Hope', '1978-06-30', 'George Walton Lucas Jr.', 775398007);
INSERT INTO movie_list (id, name, release_date, director_name, box_office)
VALUES (2, 'Episode V – The Empire Strikes Back', '1980-06-28', 'Irvin Kershner', 538375067);
INSERT INTO movie_list (id, name, release_date, director_name, box_office)
VALUES (3, 'Episode VI – Return of the Jedi', '1983-07-02', 'Richard Marquand', 475106177);
INSERT INTO movie_list (id, name, release_date, director_name, box_office)
VALUES (4, 'Episode I – The Phantom Menace', '1999-07-10', 'George Walton Lucas Jr.', 1027082707);
INSERT INTO movie_list (id, name, release_date, director_name, box_office)
VALUES (5, 'Episode II – Attack of the Clones', '2002-05-16', 'George Walton Lucas Jr.', 653779970);
INSERT INTO movie_list (id, name, release_date, director_name, box_office)
VALUES (6, 'Episode III – Revenge of the Sith', '2005-07-09', 'George Walton Lucas Jr.', 868390560);
INSERT INTO movie_list (id, name, release_date, director_name, box_office)
VALUES (7, 'Episode VII – The Force Awakens', '2015-12-18', 'Jeffrey Jacob Abrams', 2071310218);
INSERT INTO movie_list (id, name, release_date, director_name, box_office)
VALUES (8, 'Episode VIII – The Last Jedi', '2017-12-15', 'Rian Craig Johnson', 1334407706);
INSERT INTO movie_list (id, name, release_date, director_name, box_office)
VALUES (9, 'Episode IX – The Rise of Skywalker', '2019-12-20', 'Jeffrey Jacob Abrams', 1077022372);
