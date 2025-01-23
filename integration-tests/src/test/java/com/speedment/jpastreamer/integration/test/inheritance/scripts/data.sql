--
-- JPAstreamer - Express JPA queries with Java Streams
-- Copyright (c) 2020-2022, Speedment, Inc. All Rights Reserved.
--
-- License: GNU Lesser General Public License (LGPL), version 2.1 or later.
--
-- This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
-- without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
-- See the GNU Lesser General Public License for more details.
--
-- See: https://github.com/speedment/jpa-streamer/blob/master/LICENSE
--

INSERT INTO books (id, publishing_date, title, version, pages)
VALUES  (1, '2008-7-04', 'Book 1', 2, 213),
        (2, '2009-7-04', 'Book 2', 2, 234),
        (3, '2010-7-04', 'Book 3', 2, 643),
        (4, '2011-7-04', 'Book 4', 2, 211),
        (5, '2012-7-04', 'Book 5', 2, 887),
        (6, '2013-7-04', 'Book 6', 2, 123),
        (7, '2014-7-04', 'Book 7', 2, 312),
        (8, '2015-7-04', 'Book 8', 2, 11),
        (9, '2016-7-04', 'Book 9', 2, 523),
        (10, '2017-7-04', 'Book 10', 2, 432),
        (11, '2018-7-04', 'Book 11', 2, 322),
        (12, '2019-7-04', 'Book 12', 2, 121),
        (13, '2020-7-04', 'Book 13', 3, 148);

INSERT INTO blogposts (id, publishing_date, title, version, url)
VALUES  (1, '2008-7-04', 'Blog Post 1', 2, 'http://speedment.com'),
        (2, '2009-7-04', 'Blog Post 2', 2, 'http://speedment.com'),
        (3, '2010-7-04', 'Blog Post 3', 2, 'http://speedment.com'),
        (4, '2011-7-04', 'Blog Post 4', 2, 'http://speedment.com'),
        (5, '2012-7-04', 'Blog Post 5', 2, 'http://speedment.com'),
        (6, '2013-7-04', 'Blog Post 6', 2, 'http://speedment.com'),
        (7, '2014-7-04', 'Blog Post 7', 2, 'http://speedment.com'),
        (8, '2015-7-04', 'Blog Post 8', 2, 'http://speedment.com'),
        (9, '2016-7-04', 'Blog Post 9', 2, 'http://speedment.com'),
        (10, '2017-7-04', 'Blog Post 10', 2, 'http://speedment.com'),
        (11, '2018-7-04', 'Blog Post 11', 2, 'http://speedment.com'),
        (12, '2019-7-04', 'Blog Post 12', 2, 'http://speedment.com'),
        (13, '2020-7-04', 'Blog Post 13', 3, 'http://speedment.com');
        

INSERT INTO author (id, firstname, lastname, version)
        (1, 'Author 1', 'Lastname', 2),
        (2, 'Author 2', 'Blog Post 2', 2),
        (3, 'Author 3', 'Blog Post 3', 2),
        (4, 'Author 5', 'Blog Post 4', 2),
        (5, 'Author 6', 'Blog Post 5', 2),
        (6, 'Author 7', 'Blog Post 6', 3);

