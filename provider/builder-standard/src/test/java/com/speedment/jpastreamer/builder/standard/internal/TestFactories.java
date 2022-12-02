/*
 * JPAstreamer - Express JPA queries with Java Streams
 * Copyright (c) 2020-2020, Speedment, Inc. All Rights Reserved.
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * See: https://github.com/speedment/jpa-streamer/blob/master/LICENSE
 */
package com.speedment.jpastreamer.builder.standard.internal;

import com.speedment.jpastreamer.pipeline.PipelineFactory;
import com.speedment.jpastreamer.pipeline.intermediate.DoubleIntermediateOperationFactory;
import com.speedment.jpastreamer.pipeline.intermediate.IntIntermediateOperationFactory;
import com.speedment.jpastreamer.pipeline.intermediate.IntermediateOperationFactory;
import com.speedment.jpastreamer.pipeline.intermediate.LongIntermediateOperationFactory;
import com.speedment.jpastreamer.pipeline.terminal.DoubleTerminalOperationFactory;
import com.speedment.jpastreamer.pipeline.terminal.IntTerminalOperationFactory;
import com.speedment.jpastreamer.pipeline.terminal.LongTerminalOperationFactory;
import com.speedment.jpastreamer.pipeline.terminal.TerminalOperationFactory;

import static java.util.Objects.requireNonNull;

final class TestFactories implements Factories {

    private final PipelineFactory pipeline;
    private final IntermediateOperationFactory intermediate;
    private final IntIntermediateOperationFactory intIntermediate;
    private final LongIntermediateOperationFactory longIntermediate;
    private final DoubleIntermediateOperationFactory doubleIntermediate;
    private final TerminalOperationFactory terminal;
    private final IntTerminalOperationFactory intTerminal;
    private final LongTerminalOperationFactory longTerminal;
    private final DoubleTerminalOperationFactory doubleTerminal;

    public TestFactories(final PipelineFactory pipeline,
                         final IntermediateOperationFactory intermediate,
                         final IntIntermediateOperationFactory intIntermediate,
                         final LongIntermediateOperationFactory longIntermediate,
                         final DoubleIntermediateOperationFactory doubleIntermediate,
                         final TerminalOperationFactory terminal,
                         final IntTerminalOperationFactory intTerminal,
                         final LongTerminalOperationFactory longTerminal,
                         final DoubleTerminalOperationFactory doubleTerminal) {
        this.pipeline = requireNonNull(pipeline);
        this.intermediate = requireNonNull(intermediate);
        this.intIntermediate = requireNonNull(intIntermediate);
        this.longIntermediate = requireNonNull(longIntermediate);
        this.doubleIntermediate = requireNonNull(doubleIntermediate);
        this.terminal = requireNonNull(terminal);
        this.intTerminal = requireNonNull(intTerminal);
        this.longTerminal = requireNonNull(longTerminal);
        this.doubleTerminal = requireNonNull(doubleTerminal);
    }

    @Override
    public PipelineFactory pipeline() {
        return pipeline;
    }

    @Override
    public IntermediateOperationFactory intermediate() {
        return intermediate;
    }

    public IntIntermediateOperationFactory intIntermediate() {
        return intIntermediate;
    }

    public LongIntermediateOperationFactory longIntermediate() {
        return longIntermediate;
    }

    public DoubleIntermediateOperationFactory doubleIntermediate() {
        return doubleIntermediate;
    }

    @Override
    public TerminalOperationFactory terminal() {
        return terminal;
    }

    @Override
    public IntTerminalOperationFactory intTerminal() {
        return intTerminal;
    }

    @Override
    public LongTerminalOperationFactory longTerminal() {
        return longTerminal;
    }

    @Override
    public DoubleTerminalOperationFactory doubleTerminal() {
        return doubleTerminal;
    }

}
