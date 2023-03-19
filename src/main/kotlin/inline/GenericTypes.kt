package com.isyscore.kotlin.swing.inline

inline fun<reified T> newClassInstance(vararg params: Any): T {
    val pparam = params.map {
        when(it::class.java.name) {
            "java.lang.Integer" -> Int::class.java
            "java.lang.Double" -> Double::class.java
            "java.lang.Boolean" -> Boolean::class.java
            "java.lang.Byte" -> Byte::class.java
            "java.lang.Short" -> Short::class.java
            "java.lang.Long" -> Long::class.java
            "java.lang.Float" -> Float::class.java
            "java.lang.Character" -> Char::class.java
            else -> it::class.java
        }
    }.toTypedArray()
    return T::class.java.getDeclaredConstructor(*pparam).apply { isAccessible = true }.newInstance(*params)
}