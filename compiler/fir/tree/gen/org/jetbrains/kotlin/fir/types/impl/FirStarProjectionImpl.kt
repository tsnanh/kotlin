/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.types.impl

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.types.FirStarProjection
import org.jetbrains.kotlin.fir.visitors.*

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

internal class FirStarProjectionImpl(
    override val source: FirSourceElement?,
) : FirStarProjection() {
    override fun <R, D> accept(visitor: FirVisitor<R, D>, data: D): R {
        @Suppress("UNCHECKED_CAST")
        return if (visitor is FirTransformer<D>) visitor.transformStarProjection(this, data) as R
        else visitor.visitStarProjection(this, data)
    }

    override fun <E : FirElement, D> transform(visitor: FirTransformer<D>, data: D): CompositeTransformResult<E> {
        @Suppress("UNCHECKED_CAST")
        return visitor.transformStarProjection(this, data) as CompositeTransformResult<E>
    }

    override fun <R, D> acceptChildren(visitor: FirVisitor<R, D>, data: D) {}

    override fun <D> transformChildren(transformer: FirTransformer<D>, data: D): FirStarProjectionImpl {
        return this
    }
}
