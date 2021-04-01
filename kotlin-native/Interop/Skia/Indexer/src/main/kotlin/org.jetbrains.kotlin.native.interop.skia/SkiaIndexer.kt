/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.jetbrains.kotlin.native.interop.skia

import clang.*
import kotlinx.cinterop.CValue
import org.jetbrains.kotlin.native.interop.indexer.*

fun buildSkiaNativeIndexImpl(library: NativeLibrary, verbose: Boolean): IndexerResult {
    val result = SkiaNativeIndexImpl(library, verbose)
    return buildNativeIndexImpl(result)
}

class SkiaNativeIndexImpl(library: NativeLibrary, verbose: Boolean) : NativeIndexImpl(library, verbose) {
    override fun convertType(type: CValue<CXType>, typeAttributes: CValue<CXTypeAttributes>?): Type {
        if (type.kind == CXTypeKind.CXType_Record) {
            val decl: StructDecl = getStructDeclAt(clang_getTypeDeclaration(type))
            if (decl.isSkiaSharedPointer) {
                return ManagedType(decl)
            }
        }
        return super.convertType(type, typeAttributes)
    }


    // Skip functions which parameter or return type is TemplateRef
    override fun isFuncDeclEligible(cursor: CValue<CXCursor>): Boolean {
        var ret = true
        visitChildren(cursor) { childCursor, _ ->
            when (childCursor.kind) {
                CXCursorKind.CXCursor_TemplateRef ->
                    if (childCursor.spelling.startsWith("sk_sp")) {
                        CXChildVisitResult.CXChildVisit_Recurse
                    } else {
                        ret = false
                        CXChildVisitResult.CXChildVisit_Break
                    }
                else -> CXChildVisitResult.CXChildVisit_Recurse
            }
        }
        return ret
    }


    // TODO: This should be managed by Skia plugin.
    private val wellKnownSkiaStructs: List<String>
            = listOf("SkImage", "SkSurface", "SkData", "GrDirectContext", "SkColorSpace", "SkPicture")

    // TODO: This should be managed by Skia plugin.
    override fun String.isUnknownTemplate() =
            this.startsWith("sk_sp") &&
                    wellKnownSkiaStructs.none { this == "sk_sp<$it>" }


}

val StructDecl.isSkiaSharedPointer: Boolean
    get() = spelling.startsWith("sk_sp<") && spelling.endsWith(">")

val StructDecl.stripSkiaSharedPointer: String
    get() = this.spelling.drop(6).dropLast(1).let {
        if (it.startsWith("const ")) it.drop(6) else it
    }
