package com.yg.scsk

import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.KTable
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.StreamListener

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@SpringBootApplication
@EnableBinding(T1T2Binding::class, T3Binding::class)
class Application {

    @StreamListener
    fun t1Handler(@Input("t1") t1: KTable<String, String>, @Input("t2") t2: KTable<String, String>) {

    }

    @StreamListener
    fun t2Handler(@Input("t3") t3: KStream<String, String>) {
        t3.map { key, value ->
            KeyValue(key, value)
        }
    }
}

interface T1T2Binding {
    @Input
    fun t1(): KTable<String, String>

    @Input
    fun t2(): KTable<String, String>
}

interface T3Binding {
    @Input
    fun t3(): KStream<String, String>
}