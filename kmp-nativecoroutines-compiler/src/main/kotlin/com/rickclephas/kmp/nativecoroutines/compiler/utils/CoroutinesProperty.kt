package com.rickclephas.kmp.nativecoroutines.compiler.utils

import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.descriptors.effectiveVisibility
import org.jetbrains.kotlin.ir.declarations.IrProperty

internal val PropertyDescriptor.isCoroutinesProperty: Boolean
    get() = !name.isSpecial && hasFlowType

internal val PropertyDescriptor.needsNativeProperty: Boolean
    get() = effectiveVisibility().publicApi &&
            !hasIgnoreAnnotation &&
            overriddenDescriptors.size != 1 &&
            isCoroutinesProperty

internal val IrProperty.isCoroutinesProperty: Boolean
    get() = !name.isSpecial && (getter?.returnType?.isFlowType == true)

internal val IrProperty.needsNativeProperty: Boolean
    get() = visibility.isPublicAPI &&
            !hasIgnoreAnnotation &&
            overriddenSymbols.size != 1 &&
            isCoroutinesProperty