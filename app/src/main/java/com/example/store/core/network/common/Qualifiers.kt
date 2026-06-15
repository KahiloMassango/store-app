package com.example.store.core.network.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthenticatedClient


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class PublicClient