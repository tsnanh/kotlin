/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.declarations.impl

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.contracts.FirContractDescription
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase
import org.jetbrains.kotlin.fir.declarations.FirSimpleFunction
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter
import org.jetbrains.kotlin.fir.declarations.FirValueParameter
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.expressions.FirBlock
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource
import org.jetbrains.kotlin.fir.visitors.*

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

internal class FirSimpleFunctionImpl(
    override val source: FirSourceElement?,
    override val session: FirSession,
    override var resolvePhase: FirResolvePhase,
    override val origin: FirDeclarationOrigin,
    override val attributes: FirDeclarationAttributes,
    override var returnTypeRef: FirTypeRef,
    override var receiverTypeRef: FirTypeRef?,
    override val valueParameters: MutableList<FirValueParameter>,
    override var body: FirBlock?,
    override var status: FirDeclarationStatus,
    override val containerSource: DeserializedContainerSource?,
    override val dispatchReceiverType: ConeKotlinType?,
    override var contractDescription: FirContractDescription,
    override val name: Name,
    override val symbol: FirNamedFunctionSymbol,
    override val annotations: MutableList<FirAnnotationCall>,
    override val typeParameters: MutableList<FirTypeParameter>,
) : FirSimpleFunction() {
    override var controlFlowGraphReference: FirControlFlowGraphReference? = null

    init {
        symbol.bind(this)
    }

    override fun <R, D> accept(visitor: FirVisitor<R, D>, data: D): R {
        @Suppress("UNCHECKED_CAST")
        return if (visitor is FirTransformer<D>) visitor.transformSimpleFunction(this, data) as R
        else visitor.visitSimpleFunction(this, data)
    }

    override fun <E : FirElement, D> transform(visitor: FirTransformer<D>, data: D): CompositeTransformResult<E> {
        @Suppress("UNCHECKED_CAST")
        return visitor.transformSimpleFunction(this, data) as CompositeTransformResult<E>
    }

    override fun <R, D> acceptChildren(visitor: FirVisitor<R, D>, data: D) {
        returnTypeRef.accept(visitor, data)
        receiverTypeRef?.accept(visitor, data)
        controlFlowGraphReference?.accept(visitor, data)
        valueParameters.forEach { it.accept(visitor, data) }
        body?.accept(visitor, data)
        status.accept(visitor, data)
        contractDescription.accept(visitor, data)
        annotations.forEach { it.accept(visitor, data) }
        typeParameters.forEach { it.accept(visitor, data) }
    }

    override fun <D> transformChildren(transformer: FirTransformer<D>, data: D): FirSimpleFunctionImpl {
        transformReturnTypeRef(transformer, data)
        transformReceiverTypeRef(transformer, data)
        controlFlowGraphReference = controlFlowGraphReference?.accept(transformer, data)?.single as FirControlFlowGraphReference?
        transformValueParameters(transformer, data)
        transformBody(transformer, data)
        transformStatus(transformer, data)
        transformContractDescription(transformer, data)
        transformAnnotations(transformer, data)
        transformTypeParameters(transformer, data)
        return this
    }

    override fun <D> transformReturnTypeRef(transformer: FirTransformer<D>, data: D): FirSimpleFunctionImpl {
        returnTypeRef = returnTypeRef.accept(transformer, data).single as FirTypeRef
        return this
    }

    override fun <D> transformReceiverTypeRef(transformer: FirTransformer<D>, data: D): FirSimpleFunctionImpl {
        receiverTypeRef = receiverTypeRef?.accept(transformer, data)?.single as FirTypeRef?
        return this
    }

    override fun <D> transformValueParameters(transformer: FirTransformer<D>, data: D): FirSimpleFunctionImpl {
        valueParameters.transformInplace(transformer, data)
        return this
    }

    override fun <D> transformBody(transformer: FirTransformer<D>, data: D): FirSimpleFunctionImpl {
        body = body?.accept(transformer, data)?.single as FirBlock?
        return this
    }

    override fun <D> transformStatus(transformer: FirTransformer<D>, data: D): FirSimpleFunctionImpl {
        status = status.accept(transformer, data).single as FirDeclarationStatus
        return this
    }

    override fun <D> transformContractDescription(transformer: FirTransformer<D>, data: D): FirSimpleFunctionImpl {
        contractDescription = contractDescription.accept(transformer, data).single as FirContractDescription
        return this
    }

    override fun <D> transformAnnotations(transformer: FirTransformer<D>, data: D): FirSimpleFunctionImpl {
        annotations.transformInplace(transformer, data)
        return this
    }

    override fun <D> transformTypeParameters(transformer: FirTransformer<D>, data: D): FirSimpleFunctionImpl {
        typeParameters.transformInplace(transformer, data)
        return this
    }

    override fun replaceResolvePhase(newResolvePhase: FirResolvePhase) {
        resolvePhase = newResolvePhase
    }

    override fun replaceReturnTypeRef(newReturnTypeRef: FirTypeRef) {
        returnTypeRef = newReturnTypeRef
    }

    override fun replaceReceiverTypeRef(newReceiverTypeRef: FirTypeRef?) {
        receiverTypeRef = newReceiverTypeRef
    }

    override fun replaceControlFlowGraphReference(newControlFlowGraphReference: FirControlFlowGraphReference?) {
        controlFlowGraphReference = newControlFlowGraphReference
    }

    override fun replaceValueParameters(newValueParameters: List<FirValueParameter>) {
        valueParameters.clear()
        valueParameters.addAll(newValueParameters)
    }

    override fun replaceBody(newBody: FirBlock?) {
        body = newBody
    }

    override fun replaceContractDescription(newContractDescription: FirContractDescription) {
        contractDescription = newContractDescription
    }
}
