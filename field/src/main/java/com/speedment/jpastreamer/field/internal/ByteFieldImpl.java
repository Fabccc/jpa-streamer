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
package com.speedment.jpastreamer.field.internal;

import com.speedment.jpastreamer.field.ByteField;
import com.speedment.jpastreamer.field.internal.comparator.ByteFieldComparatorImpl;
import com.speedment.jpastreamer.field.internal.predicate.bytes.*;
import com.speedment.jpastreamer.field.comparator.ByteFieldComparator;
import com.speedment.jpastreamer.field.comparator.NullOrder;
import com.speedment.jpastreamer.field.internal.method.GetByteImpl;
import com.speedment.jpastreamer.field.method.ByteGetter;
import com.speedment.jpastreamer.field.method.GetByte;
import com.speedment.jpastreamer.field.predicate.FieldPredicate;
import com.speedment.jpastreamer.field.predicate.Inclusion;
import com.speedment.jpastreamer.field.predicate.SpeedmentPredicate;

import java.util.Collection;

import static com.speedment.jpastreamer.field.internal.util.CollectionUtil.collectionToSet;
import static java.util.Objects.requireNonNull;

/**
 * Default implementation of the {@link ByteField}-interface.
 * 
 * Generated by com.speedment.sources.pattern.FieldImplPattern
 * 
 * @param <ENTITY> entity type
 *
 * @author Emil Forslund
 * @since  3.0.0
 */
public final class ByteFieldImpl<ENTITY> implements ByteField<ENTITY> {
    
    private final Class<ENTITY> table;
    private final String columnName;
    private final GetByte<ENTITY> getter;
    private final boolean unique;

    public ByteFieldImpl(
            Class<ENTITY> table,
            String columnName,
            ByteGetter<ENTITY> getter,
            boolean unique) {
        this.table = requireNonNull(table);
        this.columnName = requireNonNull(columnName);
        this.getter     = new GetByteImpl<>(this, getter);
        this.unique     = unique;
    }
    
    @Override
    public Class<ENTITY> table() {
        return table;
    }

    @Override
    public GetByte<ENTITY> getter() {
        return getter;
    }
    
    @Override
    public boolean isUnique() {
        return unique;
    }
    
    @Override
    public ByteFieldComparator<ENTITY> comparator() {
        return new ByteFieldComparatorImpl<>(this);
    }
    
    @Override
    public ByteFieldComparator<ENTITY> reversed() {
        return comparator().reversed();
    }
    
    @Override
    public ByteFieldComparator<ENTITY> comparatorNullFieldsFirst() {
        return comparator();
    }
    
    @Override
    public NullOrder getNullOrder() {
        return NullOrder.LAST;
    }
    
    @Override
    public boolean isReversed() {
        return false;
    }
    
    @Override
    public FieldPredicate<ENTITY> equal(Byte value) {
        return new ByteEqualPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> greaterThan(Byte value) {
        return new ByteGreaterThanPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> greaterOrEqual(Byte value) {
        return new ByteGreaterOrEqualPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> between(
            Byte start,
            Byte end,
            Inclusion inclusion) {
        return new ByteBetweenPredicate<>(this, start, end, inclusion);
    }
    
    @Override
    public FieldPredicate<ENTITY> in(Collection<Byte> values) {
        return new ByteInPredicate<>(this, collectionToSet(values));
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> notEqual(Byte value) {
        return new ByteNotEqualPredicate<>(this, value);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> lessOrEqual(Byte value) {
        return new ByteLessOrEqualPredicate<>(this, value);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> lessThan(Byte value) {
        return new ByteLessThanPredicate<>(this, value);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> notBetween(
            Byte start,
            Byte end,
            Inclusion inclusion) {
        return new ByteNotBetweenPredicate<>(this, start, end, inclusion);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> notIn(Collection<Byte> values) {
        return new ByteNotInPredicate<>(this, collectionToSet(values));
    }

    @Override
    public String columnName() {
        return columnName;
    }
}
