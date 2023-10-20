DROP TABLE IF EXISTS movie_list;

CREATE TABLE movie_list
(
    movie_id     int unsigned AUTO_INCREMENT,
    movie_name   VARCHAR(100) NOT NULL,
    release_date VARCHAR(100) NOT NULL,
    PRIMARY KEY (movie_id)
);

INSERT INTO movie_list (movie_id, movie_name, release_date)
VALUES (1, 'Episode IV – A New Hope', '1978年6月30日');
INSERT INTO movie_list (movie_id, movie_name, release_date)
VALUES (2, 'Episode V – The Empire Strikes Back', '1980年6月28日');
INSERT INTO movie_list (movie_id, movie_name, release_date)
VALUES (3, 'Episode VI – Return of the Jedi', '1983年7月2日');
INSERT INTO movie_list (movie_id, movie_name, release_date)
VALUES (4, 'Episode I – The Phantom Menace', '1999年7月10日');
INSERT INTO movie_list (movie_id, movie_name, release_date)
VALUES (5, 'Episode II – Attack of the Clones', '2002年5月16日');
INSERT INTO movie_list (movie_id, movie_name, release_date)
VALUES (6, 'Episode III – Revenge of the Sith', '2005年7月9日');
INSERT INTO movie_list (movie_id, movie_name, release_date)
VALUES (7, 'Episode VII – The Force Awakens', '2015年12月18日');
INSERT INTO movie_list (movie_id, movie_name, release_date)
VALUES (8, 'Episode VIII – The Last Jedi', '2017年12月15日');
INSERT INTO movie_list (movie_id, movie_name, release_date)
VALUES (9, 'Episode IX – The Rise of Skywalker', '2019年12月20日');


DROP TABLE IF EXISTS director_list;

CREATE TABLE director_list
(
    director_id int unsigned AUTO_INCREMENT,
    director    VARCHAR(100) NOT NULL,
    PRIMARY KEY (director_id)

);

INSERT INTO director_list (director_id, director)
VALUES (1, 'George Walton Lucas Jr.');
INSERT INTO director_list (director_id, director)
VALUES (2, 'Irvin Kershner');
INSERT INTO director_list (director_id, director)
VALUES (3, 'Richard Marquand');
INSERT INTO director_list (director_id, director)
VALUES (4, 'George Walton Lucas Jr.');
INSERT INTO director_list (director_id, director)
VALUES (5, 'George Walton Lucas Jr.');
INSERT INTO director_list (director_id, director)
VALUES (6, 'George Walton Lucas Jr.');
INSERT INTO director_list (director_id, director)
VALUES (7, 'Jeffrey Jacob Abrams');
INSERT INTO director_list (director_id, director)
VALUES (8, 'Rian Craig Johnson');
INSERT INTO director_list (director_id, director)
VALUES (9, 'Jeffrey Jacob Abrams');



