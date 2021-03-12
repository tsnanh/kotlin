/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.expressions.impl

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirLabel
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.expressions.FirBlock
import org.jetbrains.kotlin.fir.expressions.FirExpression
import org.jetbrains.kotlin.fir.expressions.FirWhileLoop
import org.jetbrains.kotlin.fir.visitors.*

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

internal class FirWhileLoopImpl(
    override var source: FirSourceElement?,
    override val annotations: MutableList<FirAnnotationCall>,
    override var label: FirLabel?,
    override var condition: FirExpression,
    override var block: FirBlock,
) : FirWhileLoop() {
    override fun <R, D> accept(visitor: FirVisitor<R, D>, data: D): R {
        @Suppress("UNCHECKED_CAST")
        return if (visitor is FirTransformer<D>) visitor.transformWhileLoop(this, data) as R
        else visitor.visitWhileLoop(this, data)
    }

    override fun <E : FirElement, D> transform(visitor: FirTransformer<D>, data: D): CompositeTransformResult<E> {
        @Suppress("UNCHECKED_CAST")
        return visitor.transformWhileLoop(this, data) as CompositeTransformResult<E>
    }

    override fun <R, D> acceptChildren(visitor: FirVisitor<R, D>, data: D) {
        annotations.forEach { it.accept(visitor, data) }
        label?.accept(visitor, data)
        condition.accept(visitor, data)
        block.accept(visitor, data)
    }

    override fun <D> transformChildren(transformer: FirTransformer<D>, data: D): FirWhileLoopImpl {
        transformCondition(transformer, data)
        transformBlock(transformer, data)
        transformOtherChildren(transformer, data)
        return this
    }

    override fun <D> transformAnnotations(transformer: FirTransformer<D>, data: D): FirWhileLoopImpl {
        annotations.transformInplace(transformer, data)
        return this
    }

    override fun <D> transformCondition(transformer: FirTransformer<D>, data: D): FirWhileLoopImpl {
        condition = condition.accept(transformer, data).single as FirExpression
        return this
    }

    override fun <D> transformBlock(transformer: FirTransformer<D>, data: D): FirWhileLoopImpl {
        block = block.accept(transformer, data).single as FirBlock
        return this
    }

    override fun <D> transformOtherChildren(transformer: FirTransformer<D>, data: D): FirWhileLoopImpl {
        transformAnnotations(transformer, data)
        label = label?.accept(transformer, data)?.single as FirLabel?
        return this
    }

    override fun replaceSource(newSource: FirSourceElement?) {
        source = newSource
    }
}
