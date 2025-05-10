package com.shopping.study.infra.cache

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
class GeneralCache (
    _advice: GeneralCacheAdvice,
) {
    init {
        advice = _advice
    }

    companion object {
        private lateinit var advice: GeneralCacheAdvice
        private const val TOKEN = "::"

        // 가변인자를 사용하여, 여러 개의 Any 타입의 keys 인자를 받음
        fun <T> cache(vararg keys: Any, function: () -> T): T {
            return advice.cache(generateKey(keys), function)
        }

        fun <T> put(vararg keys: Any, function: () -> T): T {
            return advice.put(generateKey(keys), function)
        }

        fun <T> evict(vararg keys: Any, function: () -> T): T {
            return advice.evict(generateKey(keys), function)
        }

        // 일관된 룰로 키 생성
        private fun generateKey(keys: Array<out Any>) = keys.joinToString (TOKEN)
    }

    @Component
    class GeneralCacheAdvice {
        companion object {
            private const val CACHE_NAME = "GENERAL"
        }

        @Cacheable(value = [CACHE_NAME], key = "#key")
        fun <T> cache(key: String, function: () -> T): T {
            return function.invoke()
        }

        @CachePut(value = [CACHE_NAME], key = "#key")
        fun <T> put(key: String, function: () -> T): T {
            return function.invoke()
        }

        @CacheEvict(value = [CACHE_NAME], key = "#key")
        fun <T> evict(key: String, function: () -> T): T {
            return function.invoke()
        }
    }
}