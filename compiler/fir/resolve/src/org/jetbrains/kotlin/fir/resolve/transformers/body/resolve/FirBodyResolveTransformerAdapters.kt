/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve

import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.FirFile
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase
import org.jetbrains.kotlin.fir.resolve.ResolutionMode
import org.jetbrains.kotlin.fir.resolve.ScopeSession
import org.jetbrains.kotlin.fir.resolve.transformers.AdapterForResolveProcessor
import org.jetbrains.kotlin.fir.resolve.transformers.FirTransformerBasedResolveProcessor
import org.jetbrains.kotlin.fir.visitors.CompositeTransformResult
import org.jetbrains.kotlin.fir.visitors.FirTransformer
import org.jetbrains.kotlin.fir.visitors.compose

@OptIn(AdapterForResolveProcessor::class)
class FirBodyResolveProcessor(session: FirSession, scopeSession: ScopeSession) : FirTransformerBasedResolveProcessor(session, scopeSession) {
    override val transformer = FirBodyResolveTransformerAdapter(session, scopeSession)
}

@AdapterForResolveProcessor
class FirBodyResolveTransformerAdapter(session: FirSession, scopeSession: ScopeSession) : FirTransformer<Any?>() {
    private val transformer = FirBodyResolveTransformer(
        session,
        phase = FirResolvePhase.BODY_RESOLVE,
        implicitTypeOnly = false,
        scopeSession = scopeSession
    )

    override fun <E : FirElement> transformElement(element: E, data: Any?): CompositeTransformResult<E> {
        return element.compose()
    }

    override fun transformFile(file: FirFile, data: Any?): CompositeTransformResult<FirFile> {
        @Suppress("UNCHECKED_CAST")
        return file.accept(transformer, ResolutionMode.ContextIndependent) as CompositeTransformResult<FirFile>
    }
}
