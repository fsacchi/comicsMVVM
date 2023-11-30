package com.zig.presentation.core.injection

import com.zig.data_remote.core.injection.dataRemoteModule
import com.zig.data_remote.core.injection.servicesModule

val presentationModules = listOf(
    servicesModule,
    dataRemoteModule
) + useCaseModules + viewModelModules
