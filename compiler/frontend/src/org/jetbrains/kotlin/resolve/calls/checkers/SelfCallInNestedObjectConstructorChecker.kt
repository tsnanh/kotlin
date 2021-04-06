/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.resolve.calls.checkers

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.config.LanguageFeature
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.ConstructorDescriptor
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.ValueArgument
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.DescriptorUtils
import org.jetbrains.kotlin.resolve.calls.callResolverUtil.isSuperOrDelegatingConstructorCall
import org.jetbrains.kotlin.resolve.calls.callUtil.getCall
import org.jetbrains.kotlin.resolve.calls.callUtil.getResolvedCall
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.calls.model.ResolvedValueArgument
import org.jetbrains.kotlin.resolve.scopes.receivers.ReceiverValue

object SelfCallInNestedObjectConstructorChecker : CallChecker {
    override fun check(resolvedCall: ResolvedCall<*>, reportOn: PsiElement, context: CallCheckerContext) {
        val candidateDescriptor = resolvedCall.candidateDescriptor
        val call = resolvedCall.call

        if (candidateDescriptor !is ConstructorDescriptor || !isSuperOrDelegatingConstructorCall(call)) return
        val constructedObject = context.resolutionContext.scope.ownerDescriptor.containingDeclaration as? ClassDescriptor ?: return
        if (constructedObject.kind != ClassKind.OBJECT) return
        val containingClass = constructedObject.containingDeclaration as? ClassDescriptor ?: return
        if (candidateDescriptor.constructedClass == containingClass) {
            val reportError = context.languageVersionSettings.supportsFeature(LanguageFeature.ProhibitSelfCallsInNestedObjects)
            for (resolvedArgument in resolvedCall.valueArguments.values) {
                for (argument in resolvedArgument.arguments) {
                    checkArgument(argument, containingClass, context, reportError)
                }
            }
        }
    }

    private fun checkArgument(
        argument: ValueArgument,
        containingClass: ClassDescriptor,
        context: CallCheckerContext,
        reportError: Boolean
    ) {
        val argumentExpression = argument.getArgumentExpression() ?: return
        val trace = context.trace
        val call = argumentExpression.getCall(trace.bindingContext) ?: return
        val resolvedCall = call.getResolvedCall(trace.bindingContext) ?: return
        checkReceiver(resolvedCall.dispatchReceiver, argumentExpression, containingClass, trace, reportError)
    }

    private fun checkReceiver(
        receiver: ReceiverValue?,
        argument: KtExpression,
        containingClass: ClassDescriptor,
        trace: BindingTrace,
        reportError: Boolean
    ) {
        val receiverType = receiver?.type ?: return
        val receiverClass = receiverType.constructor.declarationDescriptor as? ClassDescriptor ?: return
        if (DescriptorUtils.isSubclass(receiverClass, containingClass)) {
            val factory = if (reportError) {
                Errors.SELF_CALL_IN_NESTED_OBJECT_CONSTRUCTOR_ERROR
            } else {
                Errors.SELF_CALL_IN_NESTED_OBJECT_CONSTRUCTOR_WARNING
            }
            trace.report(factory.on(argument))
        }
    }


}
