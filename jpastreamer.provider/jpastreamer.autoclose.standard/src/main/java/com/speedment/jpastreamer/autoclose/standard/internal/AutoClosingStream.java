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

import com.speedment.jpastreamer.javasixteen.Java16StreamAdditions;
import com.speedment.jpastreamer.javasixteen.Java16StreamUtil;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * A Stream that will call its {@link #close()} method automatically after
 * a terminating operation has been called.
 * <p>
 * N.B. The {@link #iterator()} {@link #spliterator()} methods will throw
 * an {@link UnsupportedOperationException} because otherwise the AutoClose
 * property cannot be guaranteed. This can be unlocked by setting the
 * allowStreamIteratorAndSpliterator flag
 *
 * @param <T>  Stream type
 * @param <R>  The new Stream type
 * @author     Per Minborg
 */
final class AutoClosingStream<T, R>
    extends AbstractAutoClosingBaseStream<T, Stream<T>>
    implements Stream<T>, Java16StreamAdditions<T, R> {

    AutoClosingStream(final Stream<T> stream) {
        this(stream, Boolean.getBoolean("jpastreamer.allowiteratorandspliterator"));
    }

    AutoClosingStream(
        final Stream<T> stream,
        final boolean allowStreamIteratorAndSpliterator
    ) {
        super(stream, allowStreamIteratorAndSpliterator);
    }

    @Override
    public Stream<T> filter(Predicate<? super T> predicate) {
        return wrap(stream().filter(predicate));
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
        return wrap(stream().map(mapper));
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
        return wrap(stream().mapToInt(mapper));
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
        return wrap(stream().mapToLong(mapper));
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
        return wrap(stream().mapToDouble(mapper));
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return wrap(stream().flatMap(mapper));
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
        return wrap(stream().flatMapToInt(mapper));
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
        return wrap(stream().flatMapToLong(mapper));
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
        return wrap(stream().flatMapToDouble(mapper));
    }

    @Override
    public Stream<T> distinct() {
        return wrap(stream().distinct());
    }

    @Override
    public Stream<T> sorted() {
        return wrap(stream().sorted());
    }

    @Override
    public Stream<T> sorted(Comparator<? super T> comparator) {
        return wrap(stream().sorted(comparator));
    }

    @Override
    public Stream<T> peek(Consumer<? super T> action) {
        return wrap(stream().peek(action));
    }

    @Override
    public Stream<T> limit(long maxSize) {
        return wrap(stream().limit(maxSize));
    }

    @Override
    public Stream<T> skip(long n) {
        return wrap(stream().skip(n));
    }

    @Override
    public Stream<T> takeWhile(Predicate<? super T> predicate) {
        return wrap(stream().takeWhile(predicate));
    }

    @Override
    public Stream<T> dropWhile(Predicate<? super T> predicate) {
        return wrap(stream().dropWhile(predicate));
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        finallyClose(() -> stream().forEach(action));
    }

    @Override
    public void forEachOrdered(Consumer<? super T> action) {
        finallyClose(() -> stream().forEachOrdered(action));
    }

    @Override
    public Object[] toArray() {
        return finallyClose((Supplier<Object[]>) stream()::toArray);
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
        return finallyClose(() -> stream().toArray(generator));
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        return finallyClose(() -> stream().reduce(identity, accumulator));
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        return finallyClose(() -> stream().reduce(accumulator));
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        return finallyClose(() -> stream().reduce(identity, accumulator, combiner));
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        return finallyClose(() -> stream().collect(supplier, accumulator, combiner));
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return finallyClose(() -> stream().collect(collector));
    }

    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
        return finallyClose(() -> stream().min(comparator));
    }

    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
        return finallyClose(() -> stream().max(comparator));
    }

    @Override
    public long count() {
        return finallyClose(stream()::count);
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        return finallyClose(() -> stream().anyMatch(predicate));
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        return finallyClose(() -> stream().allMatch(predicate));
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
        return finallyClose(() -> stream().noneMatch(predicate));
    }

    @Override
    public Optional<T> findFirst() {
        return finallyClose(stream()::findFirst);
    }

    @Override
    public Optional<T> findAny() {
        return finallyClose(stream()::findAny);
    }

    @Override
    public Iterator<T> iterator() {
        if (isAllowStreamIteratorAndSpliterator()) {
            return stream().iterator();
        }
        throw newUnsupportedException("iterator");
    }

    @Override
    public Spliterator<T> spliterator() {
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
    public Stream<T> sequential() {
        return wrap(stream().sequential());
    }

    @Override
    public Stream<T> parallel() {
        return wrap(stream().parallel());
    }

    @Override
    public Stream<T> unordered() {
        return wrap(stream().unordered());
    }

    @Override
    public Stream<T> onClose(Runnable closeHandler) {
        return wrap(stream().onClose(closeHandler));
    }

    @Override
    public List<T> toList(Stream<T> stream) {
        return finallyClose(() -> Java16StreamUtil.toList(stream));
    }

    @Override
    public Stream<R> mapMulti(Stream<T> stream, BiConsumer<? super T, ? super Consumer<R>> mapper) {
        return wrap(Java16StreamUtil.mapMulti(stream, mapper));
    }

    @Override
    public IntStream mapMultiToInt(Stream<T> stream, BiConsumer<? super T, ? super IntConsumer> mapper) {
        return wrap(Java16StreamUtil.mapMultiToInt(stream, mapper));
    }

    @Override
    public DoubleStream mapMultiToDouble(Stream<T> stream, BiConsumer<? super T, ? super DoubleConsumer> mapper) {
        return wrap(Java16StreamUtil.mapMultiToDouble(stream, mapper));
    }

    @Override
    public LongStream mapMultiToLong(Stream<T> stream, BiConsumer<? super T, ? super LongConsumer> mapper) {
        return wrap(Java16StreamUtil.mapMultiToLong(stream, mapper));
    }
}
