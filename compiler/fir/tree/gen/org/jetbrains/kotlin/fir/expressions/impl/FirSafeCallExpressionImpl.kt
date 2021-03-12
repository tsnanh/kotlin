/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.expressions.impl

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirExpressionRef
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.expressions.FirCheckedSafeCallSubject
import org.jetbrains.kotlin.fir.expressions.FirExpression
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccess
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.visitors.*

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

internal class FirSafeCallExpressionImpl(
    override var source: FirSourceElement?,
    override var typeRef: FirTypeRef,
    override val annotations: MutableList<FirAnnotationCall>,
    override var receiver: FirExpression,
    override val checkedSubjectRef: FirExpressionRef<FirCheckedSafeCallSubject>,
    override var regularQualifiedAccess: FirQualifiedAccess,
) : FirSafeCallExpression() {
    override fun <R, D> accept(visitor: FirVisitor<R, D>, data: D): R {
        @Suppress("UNCHECKED_CAST")
        return if (visitor is FirTransformer<D>) visitor.transformSafeCallExpression(this, data) as R
        else visitor.visitSafeCallExpression(this, data)
    }

    override fun <E : FirElement, D> transform(visitor: FirTransformer<D>, data: D): CompositeTransformResult<E> {
        @Suppress("UNCHECKED_CAST")
        return visitor.transformSafeCallExpression(this, data) as CompositeTransformResult<E>
    }

    override fun <R, D> acceptChildren(visitor: FirVisitor<R, D>, data: D) {
        typeRef.accept(visitor, data)
        annotations.forEach { it.accept(visitor, data) }
        receiver.accept(visitor, data)
        regularQualifiedAccess.accept(visitor, data)
    }

    override fun <D> transformChildren(transformer: FirTransformer<D>, data: D): FirSafeCallExpressionImpl {
        typeRef = typeRef.accept(transformer, data).single as FirTypeRef
        transformAnnotations(transformer, data)
        transformReceiver(transformer, data)
        transformRegularQualifiedAccess(transformer, data)
        return this
    }

    override fun <D> transformAnnotations(transformer: FirTransformer<D>, data: D): FirSafeCallExpressionImpl {
        annotations.transformInplace(transformer, data)
        return this
    }

    override fun <D> transformReceiver(transformer: FirTransformer<D>, data: D): FirSafeCallExpressionImpl {
        receiver = receiver.accept(transformer, data).single as FirExpression
        return this
    }

    override fun <D> transformRegularQualifiedAccess(transformer: FirTransformer<D>, data: D): FirSafeCallExpressionImpl {
        regularQualifiedAccess = regularQualifiedAccess.accept(transformer, data).single as FirQualifiedAccess
        return this
    }

    override fun replaceSource(newSource: FirSourceElement?) {
        source = newSource
    }

    override fun replaceTypeRef(newTypeRef: FirTypeRef) {
        typeRef = newTypeRef
    }

    override fun replaceRegularQualifiedAccess(newRegularQualifiedAccess: FirQualifiedAccess) {
        regularQualifiedAccess = newRegularQualifiedAccess
    }
}
