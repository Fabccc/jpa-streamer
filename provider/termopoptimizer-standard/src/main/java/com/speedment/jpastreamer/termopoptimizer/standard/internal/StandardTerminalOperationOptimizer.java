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
package com.speedment.jpastreamer.termopoptimizer.standard.internal;

import com.speedment.jpastreamer.pipeline.Pipeline;
import com.speedment.jpastreamer.termopoptimizer.TerminalOperationOptimizer;

import static java.util.Objects.requireNonNull;

final class StandardTerminalOperationOptimizer implements TerminalOperationOptimizer {
    
    @Override
    public <T> Pipeline<T> optimize(Pipeline<T> pipeline) {
        requireNonNull(pipeline);
        
        // For now, just return whatever we get. 
        
        return pipeline;
    }
    
}
