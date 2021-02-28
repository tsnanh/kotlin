/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.visitors

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirPureAbstractElement

fun <T : FirElement, D> T.transformSingle(transformer: FirTransformer<D>, data: D): T {
    @Suppress("UNCHECKED_CAST")
    return ((this as FirPureAbstractElement).accept(transformer, data) as CompositeTransformResult<T>).single
}

fun <T : FirElement, D> MutableList<T>.transformInplace(transformer: FirTransformer<D>, data: D) {
    val iterator = this.listIterator()
    while (iterator.hasNext()) {
        val next = iterator.next() as FirPureAbstractElement

        @Suppress("UNCHECKED_CAST")
        val result = next.accept(transformer, data) as CompositeTransformResult<T>

        if (result.isSingle) {
            iterator.set(result.single)
        } else {
            val resultIterator = result.list.listIterator()
            if (!resultIterator.hasNext()) {
                iterator.remove()
            } else {
                iterator.set(resultIterator.next())
            }
            while (resultIterator.hasNext()) {
                iterator.add(resultIterator.next())
            }
        }

    }
}

sealed class TransformData<out D> {
    class Data<D>(val value: D) : TransformData<D>()
    object Nothing : TransformData<kotlin.Nothing>()
}

inline fun <T : FirElement, D> MutableList<T>.transformInplace(transformer: FirTransformer<D>, dataProducer: (Int) -> TransformData<D>) {
    val iterator = this.listIterator()
    var index = 0
    while (iterator.hasNext()) {
        val next = iterator.next() as FirPureAbstractElement
        val data = when (val data = dataProducer(index++)) {
            is TransformData.Data<D> -> data.value
            TransformData.Nothing -> continue
        }

        @Suppress("UNCHECKED_CAST")
        val result = (next.accept(transformer, data) as CompositeTransformResult<T>).single

        iterator.set(result)
    }
}
