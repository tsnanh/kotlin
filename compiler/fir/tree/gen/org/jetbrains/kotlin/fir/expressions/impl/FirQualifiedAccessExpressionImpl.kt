/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.expressions.impl

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.expressions.FirExpression
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
import org.jetbrains.kotlin.fir.references.FirReference
import org.jetbrains.kotlin.fir.types.FirTypeProjection
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.visitors.*
import org.jetbrains.kotlin.fir.FirImplementationDetail

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

internal class FirQualifiedAccessExpressionImpl(
    override var source: FirSourceElement?,
    override var typeRef: FirTypeRef,
    override val annotations: MutableList<FirAnnotationCall>,
    override var calleeReference: FirReference,
    override val typeArguments: MutableList<FirTypeProjection>,
    override var explicitReceiver: FirExpression?,
    override var dispatchReceiver: FirExpression,
    override var extensionReceiver: FirExpression,
) : FirQualifiedAccessExpression() {
    override fun <R, D> accept(visitor: FirVisitor<R, D>, data: D): R {
        @Suppress("UNCHECKED_CAST")
        return if (visitor is FirTransformer<D>) visitor.transformQualifiedAccessExpression(this, data) as R
        else visitor.visitQualifiedAccessExpression(this, data)
    }

    override fun <E : FirElement, D> transform(visitor: FirTransformer<D>, data: D): CompositeTransformResult<E> {
        @Suppress("UNCHECKED_CAST")
        return visitor.transformQualifiedAccessExpression(this, data) as CompositeTransformResult<E>
    }

    override fun <R, D> acceptChildren(visitor: FirVisitor<R, D>, data: D) {
        typeRef.accept(visitor, data)
        annotations.forEach { it.accept(visitor, data) }
        calleeReference.accept(visitor, data)
        typeArguments.forEach { it.accept(visitor, data) }
        explicitReceiver?.accept(visitor, data)
        if (dispatchReceiver !== explicitReceiver) {
            dispatchReceiver.accept(visitor, data)
        }
        if (extensionReceiver !== explicitReceiver && extensionReceiver !== dispatchReceiver) {
            extensionReceiver.accept(visitor, data)
        }
    }

    override fun <D> transformChildren(transformer: FirTransformer<D>, data: D): FirQualifiedAccessExpressionImpl {
        typeRef = typeRef.accept(transformer, data).single as FirTypeRef
        transformAnnotations(transformer, data)
        transformCalleeReference(transformer, data)
        transformTypeArguments(transformer, data)
        explicitReceiver = explicitReceiver?.transformSingle(transformer, data)
        if (dispatchReceiver !== explicitReceiver) {
            dispatchReceiver = dispatchReceiver.transformSingle(transformer, data)
        }
        if (extensionReceiver !== explicitReceiver && extensionReceiver !== dispatchReceiver) {
            extensionReceiver = extensionReceiver.transformSingle(transformer, data)
        }
        return this
    }

    override fun <D> transformAnnotations(transformer: FirTransformer<D>, data: D): FirQualifiedAccessExpressionImpl {
        annotations.transformInplace(transformer, data)
        return this
    }

    override fun <D> transformCalleeReference(transformer: FirTransformer<D>, data: D): FirQualifiedAccessExpressionImpl {
        calleeReference = calleeReference.accept(transformer, data).single as FirReference
        return this
    }

    override fun <D> transformTypeArguments(transformer: FirTransformer<D>, data: D): FirQualifiedAccessExpressionImpl {
        typeArguments.transformInplace(transformer, data)
        return this
    }

    override fun <D> transformExplicitReceiver(transformer: FirTransformer<D>, data: D): FirQualifiedAccessExpressionImpl {
        explicitReceiver = explicitReceiver?.accept(transformer, data)?.single as FirExpression?
        return this
    }

    override fun <D> transformDispatchReceiver(transformer: FirTransformer<D>, data: D): FirQualifiedAccessExpressionImpl {
        dispatchReceiver = dispatchReceiver.accept(transformer, data).single as FirExpression
        return this
    }

    override fun <D> transformExtensionReceiver(transformer: FirTransformer<D>, data: D): FirQualifiedAccessExpressionImpl {
        extensionReceiver = extensionReceiver.accept(transformer, data).single as FirExpression
        return this
    }

    @FirImplementationDetail
    override fun replaceSource(newSource: FirSourceElement?) {
        source = newSource
    }

    override fun replaceTypeRef(newTypeRef: FirTypeRef) {
        typeRef = newTypeRef
    }

    override fun replaceCalleeReference(newCalleeReference: FirReference) {
        calleeReference = newCalleeReference
    }

    override fun replaceTypeArguments(newTypeArguments: List<FirTypeProjection>) {
        typeArguments.clear()
        typeArguments.addAll(newTypeArguments)
    }

    override fun replaceExplicitReceiver(newExplicitReceiver: FirExpression?) {
        explicitReceiver = newExplicitReceiver
    }
}
