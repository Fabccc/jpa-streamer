import com.speedment.jpastreamer.fieldgenerator.StandardFieldGeneratorProcessor;

/*
 * JPAstreamer - Express JPA queries with Java Streams
 * Copyright (c) 2020-2022, Speedment, Inc. All Rights Reserved.
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * See: https://github.com/speedment/jpa-streamer/blob/master/LICENSE
 */
module jpastreamer.fieldgenerator.test {

    requires jpastreamer.fieldgenerator.standard;
    requires jakarta.persistence;
    requires lombok;
    requires jakarta.validation;
    requires transitive java.sql;
    requires transitive jpastreamer.field;
    
    exports com.speedment.jpastreamer.fieldgenerator.test;
}
