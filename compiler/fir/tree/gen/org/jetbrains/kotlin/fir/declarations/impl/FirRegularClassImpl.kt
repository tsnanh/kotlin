/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.declarations.impl

import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.declarations.FirDeclaration
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus
import org.jetbrains.kotlin.fir.declarations.FirRegularClass
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol
import org.jetbrains.kotlin.fir.types.FirTypeRef
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.fir.visitors.*

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

internal class FirRegularClassImpl(
    override val source: FirSourceElement?,
    override val session: FirSession,
    override var resolvePhase: FirResolvePhase,
    override val origin: FirDeclarationOrigin,
    override val attributes: FirDeclarationAttributes,
    override val annotations: MutableList<FirAnnotationCall>,
    override val typeParameters: MutableList<FirTypeParameterRef>,
    override var status: FirDeclarationStatus,
    override val classKind: ClassKind,
    override val declarations: MutableList<FirDeclaration>,
    override val scopeProvider: FirScopeProvider,
    override val name: Name,
    override val symbol: FirRegularClassSymbol,
    override var companionObject: FirRegularClass?,
    override val superTypeRefs: MutableList<FirTypeRef>,
) : FirRegularClass() {
    override var controlFlowGraphReference: FirControlFlowGraphReference? = null
    override val hasLazyNestedClassifiers: Boolean get() = false

    init {
        symbol.bind(this)
    }

    override fun <R, D> accept(visitor: FirVisitor<R, D>, data: D): R {
        @Suppress("UNCHECKED_CAST")
        return if (visitor is FirTransformer<D>) visitor.transformRegularClass(this, data) as R
        else visitor.visitRegularClass(this, data)
    }

    override fun <E : FirElement, D> transform(visitor: FirTransformer<D>, data: D): CompositeTransformResult<E> {
        @Suppress("UNCHECKED_CAST")
        return visitor.transformRegularClass(this, data) as CompositeTransformResult<E>
    }

    override fun <R, D> acceptChildren(visitor: FirVisitor<R, D>, data: D) {
        annotations.forEach { it.accept(visitor, data) }
        typeParameters.forEach { it.accept(visitor, data) }
        status.accept(visitor, data)
        controlFlowGraphReference?.accept(visitor, data)
        declarations.forEach { it.accept(visitor, data) }
        superTypeRefs.forEach { it.accept(visitor, data) }
    }

    override fun <D> transformChildren(transformer: FirTransformer<D>, data: D): FirRegularClassImpl {
        transformAnnotations(transformer, data)
        transformTypeParameters(transformer, data)
        transformStatus(transformer, data)
        controlFlowGraphReference = controlFlowGraphReference?.accept(transformer, data)?.single as FirControlFlowGraphReference?
        transformDeclarations(transformer, data)
        companionObject = declarations.asSequence().filterIsInstance<FirRegularClass>().firstOrNull { it.status.isCompanion }
        transformSuperTypeRefs(transformer, data)
        return this
    }

    override fun <D> transformAnnotations(transformer: FirTransformer<D>, data: D): FirRegularClassImpl {
        annotations.transformInplace(transformer, data)
        return this
    }

    override fun <D> transformTypeParameters(transformer: FirTransformer<D>, data: D): FirRegularClassImpl {
        typeParameters.transformInplace(transformer, data)
        return this
    }

    override fun <D> transformStatus(transformer: FirTransformer<D>, data: D): FirRegularClassImpl {
        status = status.accept(transformer, data).single as FirDeclarationStatus
        return this
    }

    override fun <D> transformDeclarations(transformer: FirTransformer<D>, data: D): FirRegularClassImpl {
        declarations.transformInplace(transformer, data)
        return this
    }

    override fun <D> transformCompanionObject(transformer: FirTransformer<D>, data: D): FirRegularClassImpl {
        companionObject = companionObject?.accept(transformer, data)?.single as FirRegularClass?
        return this
    }

    override fun <D> transformSuperTypeRefs(transformer: FirTransformer<D>, data: D): FirRegularClassImpl {
        superTypeRefs.transformInplace(transformer, data)
        return this
    }

    override fun replaceResolvePhase(newResolvePhase: FirResolvePhase) {
        resolvePhase = newResolvePhase
    }

    override fun replaceControlFlowGraphReference(newControlFlowGraphReference: FirControlFlowGraphReference?) {
        controlFlowGraphReference = newControlFlowGraphReference
    }

    override fun replaceSuperTypeRefs(newSuperTypeRefs: List<FirTypeRef>) {
        superTypeRefs.clear()
        superTypeRefs.addAll(newSuperTypeRefs)
    }
}
