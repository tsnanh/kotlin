/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.kotlin.fir.FirElement
import org.jetbrains.kotlin.fir.FirSymbolOwner
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor
import org.jetbrains.kotlin.utils.keysToMap

class LocalClassesNavigationInfo(
    val parentForClass: Map<FirClass<*>, FirClass<*>?>,
    private val parentClassForFunction: Map<FirCallableMemberDeclaration<*>, FirClass<*>>,
    val allMembers: List<FirSymbolOwner<*>>
) {
    val designationMap: Map<FirCallableMemberDeclaration<*>, List<FirClass<*>>> by lazy {
        parentClassForFunction.keys.keysToMap {
            pathForCallable(it)
        }
    }

    private fun pathForCallable(callableMemberDeclaration: FirCallableMemberDeclaration<*>): List<FirClass<*>> {
        val result = mutableListOf<FirClass<*>>()
        var current = parentClassForFunction[callableMemberDeclaration]

        while (current != null) {
            result += current
            current = parentForClass[current]
        }

        return result.asReversed()
    }
}

fun FirClass<*>.collectLocalClassesNavigationInfo(): LocalClassesNavigationInfo =
    NavigationInfoVisitor().run {
        this@collectLocalClassesNavigationInfo.accept(this@run, null)

        LocalClassesNavigationInfo(parentForClass, resultingMap, allMembers)
    }

private class NavigationInfoVisitor : FirDefaultVisitor<Unit, Any?>() {
    val resultingMap: MutableMap<FirCallableMemberDeclaration<*>, FirClass<*>> = mutableMapOf()
    val parentForClass: MutableMap<FirClass<*>, FirClass<*>?> = mutableMapOf()
    val allMembers: MutableList<FirSymbolOwner<*>> = mutableListOf()
    private var currentPath: PersistentList<FirClass<*>> = persistentListOf()

    override fun visitElement(element: FirElement, data: Any?) {}

    override fun visitRegularClass(regularClass: FirRegularClass, data: Any?) {
        visitClass(regularClass, null)
    }

    override fun visitAnonymousObject(anonymousObject: FirAnonymousObject, data: Any?) {
        visitClass(anonymousObject, null)
    }

    override fun <F : FirClass<F>> visitClass(klass: FirClass<F>, data: Any?) {
        parentForClass[klass] = currentPath.lastOrNull()
        val prev = currentPath
        currentPath = currentPath.add(klass)

        klass.acceptChildren(this, null)

        currentPath = prev
    }

    override fun visitSimpleFunction(simpleFunction: FirSimpleFunction, data: Any?) {
        visitCallableMemberDeclaration(simpleFunction, null)
    }

    override fun visitProperty(property: FirProperty, data: Any?) {
        visitCallableMemberDeclaration(property, null)
    }

    override fun visitConstructor(constructor: FirConstructor, data: Any?) {
        visitCallableMemberDeclaration(constructor, null)
    }

    override fun <F : FirCallableMemberDeclaration<F>> visitCallableMemberDeclaration(
        callableMemberDeclaration: FirCallableMemberDeclaration<F>,
        data: Any?
    ) {
        allMembers += callableMemberDeclaration
        if (callableMemberDeclaration.returnTypeRef !is FirImplicitTypeRef) return
        resultingMap[callableMemberDeclaration] = currentPath.last()
    }

    override fun visitAnonymousInitializer(anonymousInitializer: FirAnonymousInitializer, data: Any?) {
        allMembers += anonymousInitializer
    }
}
