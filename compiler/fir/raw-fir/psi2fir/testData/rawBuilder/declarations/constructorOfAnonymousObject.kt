private fun resolveAccessorCall(
    suspendPropertyDescriptor: PropertyDescriptor,
    context: TranslationContext
): ResolvedCall<PropertyDescriptor> {
    return object : ResolvedCall<PropertyDescriptor> {
        override fun getStatus() = ResolutionStatus.SUCCESS

        override fun getCandidateDescriptor() = suspendPropertyDescriptor
        override fun getResultingDescriptor() = suspendPropertyDescriptor
    }
}

// FIR_FRAGMENT_EXPECTED LINE: 4 TEXT OFFSET: 152 LINE TEXT: {
// FIR_FRAGMENT_EXPECTED LINE: 5 TEXT OFFSET: 158 LINE TEXT: return object : ResolvedCall<PropertyDescriptor> {
// FIR_FRAGMENT_EXPECTED LINE: 1 TEXT OFFSET: 12 LINE TEXT: private fun resolveAccessorCall(
// FIR_FRAGMENT_EXPECTED LINE: 2 TEXT OFFSET: 37 LINE TEXT: suspendPropertyDescriptor: PropertyDescriptor
// FIR_FRAGMENT_EXPECTED LINE: 3 TEXT OFFSET: 88 LINE TEXT: context: TranslationContext
// FIR_FRAGMENT_EXPECTED LINE: 6 TEXT OFFSET: 230 LINE TEXT: override fun getStatus() = ResolutionStatus.SUCCESS
// FIR_FRAGMENT_EXPECTED LINE: 8 TEXT OFFSET: 291 LINE TEXT: override fun getCandidateDescriptor() = suspendPropertyDescriptor
// FIR_FRAGMENT_EXPECTED LINE: 9 TEXT OFFSET: 365 LINE TEXT: override fun getResultingDescriptor() = suspendPropertyDescriptor