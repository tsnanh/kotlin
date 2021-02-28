/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.contracts.impl

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall
import org.jetbrains.kotlin.fir.visitors.*

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

internal class FirLegacyRawContractDescriptionImpl(
    override val source: FirSourceElement?,
    override var contractCall: FirFunctionCall,
) : FirLegacyRawContractDescription() {
    override fun <R, D> accept(visitor: FirVisitor<R, D>, data: D): R {
        @Suppress("UNCHECKED_CAST")
        return if (visitor is FirTransformer<D>) visitor.transformLegacyRawContractDescription(this, data) as R
        else visitor.visitLegacyRawContractDescription(this, data)
    }

    override fun <E : FirElement, D> transform(visitor: FirTransformer<D>, data: D): CompositeTransformResult<E> {
        @Suppress("UNCHECKED_CAST")
        return visitor.transformLegacyRawContractDescription(this, data) as CompositeTransformResult<E>
    }

    override fun <R, D> acceptChildren(visitor: FirVisitor<R, D>, data: D) {
        contractCall.accept(visitor, data)
    }

    override fun <D> transformChildren(transformer: FirTransformer<D>, data: D): FirLegacyRawContractDescriptionImpl {
        contractCall = contractCall.accept(transformer, data).single as FirFunctionCall
        return this
    }
}
