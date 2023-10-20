DROP TABLE IF EXISTS movie_list;

CREATE TABLE movie_list
(
    movie_id      int unsigned AUTO_INCREMENT,
    movie_name    VARCHAR(100) NOT NULL,
    release_date  VARCHAR(100) NOT NULL,
    director_name VARCHAR(100) NOT NULL,
    box_office    VARCHAR(100) NOT NULL,
    PRIMARY KEY (movie_id)
);

INSERT INTO movie_list (movie_id, movie_name, release_date, director_name, box_office)
VALUES (1, 'Episode IV – A New Hope', '1978年6月30日', 'George Walton Lucas Jr.', '$775,398,007');
INSERT INTO movie_list (movie_id, movie_name, release_date, director_name, box_office)
VALUES (2, 'Episode V – The Empire Strikes Back', '1980年6月28日', 'Irvin Kershner', '$538,375,067');
INSERT INTO movie_list (movie_id, movie_name, release_date, director_name, box_office)
VALUES (3, 'Episode VI – Return of the Jedi', '1983年7月2日', 'Richard Marquand', '$475,106,177');
INSERT INTO movie_list (movie_id, movie_name, release_date, director_name, box_office)
VALUES (4, 'Episode I – The Phantom Menace', '1999年7月10日', 'George Walton Lucas Jr.', '$1,027,082,707');
INSERT INTO movie_list (movie_id, movie_name, release_date, director_name, box_office)
VALUES (5, 'Episode II – Attack of the Clones', '2002年5月16日', 'George Walton Lucas Jr.', '$653,779,970');
INSERT INTO movie_list (movie_id, movie_name, release_date, director_name, box_office)
VALUES (6, 'Episode III – Revenge of the Sith', '2005年7月9日', 'George Walton Lucas Jr.', '$868,390,560');
INSERT INTO movie_list (movie_id, movie_name, release_date, director_name, box_office)
VALUES (7, 'Episode VII – The Force Awakens', '2015年12月18日', 'Jeffrey Jacob Abrams', '$2,071,310,218');
INSERT INTO movie_list (movie_id, movie_name, release_date, director_name, box_office)
VALUES (8, 'Episode VIII – The Last Jedi', '2017年12月15日', 'Rian Craig Johnson', '$1,334,407,706');
INSERT INTO movie_list (movie_id, movie_name, release_date, director_name, box_office)
VALUES (9, 'Episode IX – The Rise of Skywalker', '2019年12月20日', 'Jeffrey Jacob Abrams', '$1,077,022,372');