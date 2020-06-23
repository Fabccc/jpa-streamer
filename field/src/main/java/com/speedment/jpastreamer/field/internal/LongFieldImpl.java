/*
 *
 * Copyright (c) 2006-2020, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.jpastreamer.field.internal;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.jpastreamer.field.internal.predicate.longs.*;
import com.speedment.jpastreamer.field.method.LongGetter;
import com.speedment.jpastreamer.field.LongField;
import com.speedment.jpastreamer.field.comparator.LongFieldComparator;
import com.speedment.jpastreamer.field.comparator.NullOrder;
import com.speedment.jpastreamer.field.internal.comparator.LongFieldComparatorImpl;
import com.speedment.jpastreamer.field.internal.method.GetLongImpl;
import com.speedment.jpastreamer.field.method.GetLong;
import com.speedment.jpastreamer.field.predicate.FieldPredicate;
import com.speedment.jpastreamer.field.predicate.Inclusion;
import com.speedment.jpastreamer.field.predicate.SpeedmentPredicate;

import javax.persistence.AttributeConverter;
import java.util.Collection;

import static com.speedment.jpastreamer.field.internal.util.CollectionUtil.collectionToSet;
import static java.util.Objects.requireNonNull;

/**
 * Default implementation of the {@link LongField}-interface.
 * 
 * Generated by com.speedment.sources.pattern.FieldImplPattern
 * 
 * @param <ENTITY> entity type
 * @param <D>      database type
 * 
 * @author Emil Forslund
 * @since  3.0.0
 */
@GeneratedCode(value = "Speedment")
public final class LongFieldImpl<ENTITY, D> implements LongField<ENTITY, D> {
    
    private final Class<ENTITY> table;
    private final GetLong<ENTITY, D> getter;
    private final Class<? extends AttributeConverter<Long, ? super D>> attributeConverterClass;
    private final boolean unique;

    public LongFieldImpl(
            Class<ENTITY> table,
            LongGetter<ENTITY> getter,
            Class<? extends AttributeConverter<Long, ? super D>> attributeConverterClass,
            boolean unique) {
        this.table = requireNonNull(table);
        this.getter     = new GetLongImpl<>(this, getter);
        this.attributeConverterClass = attributeConverterClass;
        this.unique     = unique;
    }

    @Override
    public Class<ENTITY> table() {
        return table;
    }

    @Override
    public GetLong<ENTITY, D> getter() {
        return getter;
    }
    
    @Override
    public Class<? extends AttributeConverter<Long, ? super D>> attributeConverterClass() {
        return attributeConverterClass;
    }
    
    @Override
    public boolean isUnique() {
        return unique;
    }
    
    @Override
    public LongFieldComparator<ENTITY, D> comparator() {
        return new LongFieldComparatorImpl<>(this);
    }
    
    @Override
    public LongFieldComparator<ENTITY, D> reversed() {
        return comparator().reversed();
    }
    
    @Override
    public LongFieldComparator<ENTITY, D> comparatorNullFieldsFirst() {
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
    public FieldPredicate<ENTITY> equal(Long value) {
        return new LongEqualPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> greaterThan(Long value) {
        return new LongGreaterThanPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> greaterOrEqual(Long value) {
        return new LongGreaterOrEqualPredicate<>(this, value);
    }
    
    @Override
    public FieldPredicate<ENTITY> between(
            Long start,
            Long end,
            Inclusion inclusion) {
        return new LongBetweenPredicate<>(this, start, end, inclusion);
    }
    
    @Override
    public FieldPredicate<ENTITY> in(Collection<Long> values) {
        return new LongInPredicate<>(this, collectionToSet(values));
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> notEqual(Long value) {
        return new LongNotEqualPredicate<>(this, value);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> lessOrEqual(Long value) {
        return new LongLessOrEqualPredicate<>(this, value);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> lessThan(Long value) {
        return new LongLessThanPredicate<>(this, value);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> notBetween(
            Long start,
            Long end,
            Inclusion inclusion) {
        return new LongNotBetweenPredicate<>(this, start, end, inclusion);
    }
    
    @Override
    public SpeedmentPredicate<ENTITY> notIn(Collection<Long> values) {
        return new LongNotInPredicate<>(this, collectionToSet(values));
    }
}