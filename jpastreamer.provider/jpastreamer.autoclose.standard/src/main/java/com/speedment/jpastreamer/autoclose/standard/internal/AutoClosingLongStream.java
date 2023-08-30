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
package com.speedment.jpastreamer.autoclose.standard.internal;

import java.util.*;
import java.util.function.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * A LongStream that will call its {@link #close()} method automatically after
 * a terminating operation has been called.
 * <p>
 * N.B. The {@link #iterator()} {@link #spliterator()} methods will throw
 * an {@link UnsupportedOperationException} because otherwise the AutoClose
 * property cannot be guaranteed. This can be unlocked by setting the
 * allowStreamIteratorAndSpliterator flag
 *
 * @author     Per Minborg
 */
final class AutoClosingLongStream
    extends AbstractAutoClosingBaseStream<Long, LongStream>
    implements LongStream {

    AutoClosingLongStream(final LongStream stream) {
        this(stream, Boolean.getBoolean("jpastreamer.allowiteratorandspliterator"));
    }

    AutoClosingLongStream(
        final LongStream stream,
        final boolean allowStreamIteratorAndSpliterator
    ) {
        super(stream, allowStreamIteratorAndSpliterator);
    }

    @Override
    public LongStream filter(LongPredicate predicate) {
        return wrap(stream().filter(predicate));
    }

    @Override
    public LongStream map(LongUnaryOperator mapper) {
        return wrap(stream().map(mapper));
    }

    @Override
    public <U> Stream<U> mapToObj(LongFunction<? extends U> mapper) {
        return wrap(stream().mapToObj(mapper));
    }

    @Override
    public IntStream mapToInt(LongToIntFunction mapper) {
        return wrap(stream().mapToInt(mapper));
    }

    @Override
    public DoubleStream mapToDouble(LongToDoubleFunction mapper) {
        return wrap(stream().mapToDouble(mapper));
    }

    @Override
    public LongStream flatMap(LongFunction<? extends LongStream> mapper) {
        return wrap(stream().flatMap(mapper));
    }

    @Override
    public LongStream distinct() {
        return wrap(stream().distinct());
    }

    @Override
    public LongStream sorted() {
        return wrap(stream().sorted());
    }

    @Override
    public LongStream peek(LongConsumer action) {
        return wrap(stream().peek(action));
    }

    @Override
    public LongStream limit(long maxSize) {
        return wrap(stream().limit(maxSize));
    }

    @Override
    public LongStream skip(long n) {
        return wrap(stream().skip(n));
    }

    @Override
    public LongStream takeWhile(LongPredicate predicate) {
        return wrap(stream().takeWhile(predicate));
    }

    @Override
    public LongStream dropWhile(LongPredicate predicate) {
        return wrap(stream().dropWhile(predicate));
    }

    @Override
    public void forEach(LongConsumer action) {
        finallyClose(() -> stream().forEach(action));
    }

    @Override
    public void forEachOrdered(LongConsumer action) {
        finallyClose(() -> stream().forEachOrdered(action));
    }

    @Override
    public long[] toArray() {
        try {
            return stream().toArray();
        } finally {
            close();
        }
    }

    @Override
    public long reduce(long identity, LongBinaryOperator op) {
        return finallyClose(() -> stream().reduce(identity, op));
    }

    @Override
    public OptionalLong reduce(LongBinaryOperator op) {
        return finallyClose(() -> stream().reduce(op));
    }

    @Override
    public <R> R collect(Supplier<R> supplier, ObjLongConsumer<R> accumulator, BiConsumer<R, R> combiner) {
        return finallyClose(() -> stream().collect(supplier, accumulator, combiner));
    }

    @Override
    public long sum() {
        return finallyClose(stream()::sum);
    }

    @Override
    public OptionalLong min() {
        return finallyClose(stream()::min);
    }

    @Override
    public OptionalLong max() {
        return finallyClose(stream()::max);
    }

    @Override
    public long count() {
        return finallyClose(stream()::count);
    }

    @Override
    public OptionalDouble average() {
        return finallyClose(stream()::average);
    }

    @Override
    public LongSummaryStatistics summaryStatistics() {
        return finallyClose(stream()::summaryStatistics);
    }

    @Override
    public boolean anyMatch(LongPredicate predicate) {
        return finallyClose(() -> stream().anyMatch(predicate));
    }

    @Override
    public boolean allMatch(LongPredicate predicate) {
        return finallyClose(() -> stream().allMatch(predicate));
    }

    @Override
    public boolean noneMatch(LongPredicate predicate) {
        return finallyClose(() -> stream().noneMatch(predicate));
    }

    @Override
    public OptionalLong findFirst() {
        return finallyClose(stream()::findFirst);
    }

    @Override
    public OptionalLong findAny() {
        return finallyClose(stream()::findAny);
    }

    @Override
    public DoubleStream asDoubleStream() {
        return wrap(stream().asDoubleStream());
    }

    @Override
    public Stream<Long> boxed() {
        return wrap(stream().boxed());
    }

    @Override
    public LongStream sequential() {
        return wrap(stream().sequential());
    }

    @Override
    public LongStream parallel() {
        return wrap(stream().parallel());
    }

    @Override
    public PrimitiveIterator.OfLong iterator() {
        if (isAllowStreamIteratorAndSpliterator()) {
            return stream().iterator();
        }
        throw newUnsupportedException("iterator");
    }

    @Override
    public Spliterator.OfLong spliterator() {
        if (isAllowStreamIteratorAndSpliterator()) {
            return stream().spliterator();
        }
        throw newUnsupportedException("spliterator");
    }

    @Override
    public boolean isParallel() {
        return stream().isParallel();
    }

    @Override
    public LongStream unordered() {
        return wrap(stream().unordered());
    }

    @Override
    public LongStream onClose(Runnable closeHandler) {
        return wrap(stream().onClose(closeHandler));
    }

}
