package com.akvone.mobiletechnologies

import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.mongo.config.Net
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.runtime.Network


fun main() {
    val mongodConfig = MongodConfigBuilder()
            .version(Version.Main.PRODUCTION)
            .net(Net("localhost", 27017, Network.localhostIsIPv6()))
            .build()
    MongodStarter.getDefaultInstance().prepare(mongodConfig).start()

    Thread.sleep(1000000000L)
}