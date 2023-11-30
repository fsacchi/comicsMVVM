package com.zig.data.exception

class UnexpectedException(message: String? = null) : Throwable(message)

class NetworkException(message: String = "Houve um problema com a sua conexão. Verifique sua rede de dados.") :
    BusinessException(message)

class TimeoutException(message: String = "Tempo de processamento excedido. Tente novamente.") : BusinessException(message)
