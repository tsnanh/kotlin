/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.expressions.impl

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.expressions.FirArgumentList
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall
import org.jetbrains.kotlin.fir.expressions.FirExpression
import org.jetbrains.kotlin.fir.references.FirReference
import org.jetbrains.kotlin.fir.references.impl.FirExplicitSuperReference
import org.jetbrains.kotlin.fir.references.impl.FirExplicitThisReference
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.fir.visitors.*

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

internal class FirDelegatedConstructorCallImpl(
    override val source: FirSourceElement?,
    override val annotations: MutableList<FirAnnotationCall>,
    override var argumentList: FirArgumentList,
    override var constructedTypeRef: FirTypeRef,
    override var dispatchReceiver: FirExpression,
    override val isThis: Boolean,
) : FirDelegatedConstructorCall() {
    override var calleeReference: FirReference = if (isThis) FirExplicitThisReference(source, null) else FirExplicitSuperReference(source, null, constructedTypeRef)
    override val isSuper: Boolean get() = !isThis

    override fun <R, D> accept(visitor: FirVisitor<R, D>, data: D): R {
        @Suppress("UNCHECKED_CAST")
        return if (visitor is FirTransformer<D>) visitor.transformDelegatedConstructorCall(this, data) as R
        else visitor.visitDelegatedConstructorCall(this, data)
    }

    override fun <E : FirElement, D> transform(visitor: FirTransformer<D>, data: D): CompositeTransformResult<E> {
        @Suppress("UNCHECKED_CAST")
        return visitor.transformDelegatedConstructorCall(this, data) as CompositeTransformResult<E>
    }

    override fun <R, D> acceptChildren(visitor: FirVisitor<R, D>, data: D) {
        annotations.forEach { it.accept(visitor, data) }
        argumentList.accept(visitor, data)
        constructedTypeRef.accept(visitor, data)
        calleeReference.accept(visitor, data)
    }

    override fun <D> transformChildren(transformer: FirTransformer<D>, data: D): FirDelegatedConstructorCallImpl {
        transformAnnotations(transformer, data)
        argumentList = argumentList.accept(transformer, data).single as FirArgumentList
        constructedTypeRef = constructedTypeRef.accept(transformer, data).single as FirTypeRef
        transformCalleeReference(transformer, data)
        return this
    }

    override fun <D> transformAnnotations(transformer: FirTransformer<D>, data: D): FirDelegatedConstructorCallImpl {
        annotations.transformInplace(transformer, data)
        return this
    }

    override fun <D> transformDispatchReceiver(transformer: FirTransformer<D>, data: D): FirDelegatedConstructorCallImpl {
        dispatchReceiver = dispatchReceiver.accept(transformer, data).single as FirExpression
        return this
    }

    override fun <D> transformCalleeReference(transformer: FirTransformer<D>, data: D): FirDelegatedConstructorCallImpl {
        calleeReference = calleeReference.accept(transformer, data).single as FirReference
        return this
    }

    override fun replaceArgumentList(newArgumentList: FirArgumentList) {
        argumentList = newArgumentList
    }

    override fun replaceConstructedTypeRef(newConstructedTypeRef: FirTypeRef) {
        constructedTypeRef = newConstructedTypeRef
    }

    override fun replaceCalleeReference(newCalleeReference: FirReference) {
        calleeReference = newCalleeReference
    }
}
