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
package com.speedment.jpastreamer.integration.test.standard;

import com.speedment.jpastreamer.application.StreamSupplier;
import com.speedment.jpastreamer.integration.test.standard.model.Film;
import com.speedment.jpastreamer.integration.test.standard.model.Film$;
import com.speedment.jpastreamer.streamconfiguration.StreamConfiguration;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnionTest extends JPAStreamerTest {
    
    @Test
    void unionTest() {

        StreamConfiguration<Film> configuration = StreamConfiguration.of(Film.class)
                .joining(Film$.actors)
                .joining(Film$.language);
         
        try (final StreamSupplier<Film> supplier = jpaStreamer.createStreamSupplier(configuration)) {
            
            List<Film> actual = Stream.of(
                            supplier.stream().filter(Film$.length.greaterThan(120)),
                            supplier.stream().filter(Film$.rating.equal("PG-13"))
                    )
                    .flatMap(Function.identity())
                    .distinct()
                    .sorted(Comparator.comparing(Film::getFilmId))
                    .collect(Collectors.toList());

            List<Film> expected = jpaStreamer.stream(configuration)
                    .filter(f -> f.getLength() > 120 || f.getRating().equals("PG-13"))
                    .sorted(Comparator.comparing(Film::getFilmId))
                    .collect(Collectors.toList());
            
            assertEquals(expected, actual); 
            
        }
    }
}
