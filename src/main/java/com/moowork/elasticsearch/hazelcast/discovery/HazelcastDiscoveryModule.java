package com.moowork.elasticsearch.hazelcast.discovery;

import org.elasticsearch.common.inject.AbstractModule;
import org.elasticsearch.discovery.Discovery;

public final class HazelcastDiscoveryModule
    extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind( Discovery.class ).to( HazelcastDiscovery.class ).asEagerSingleton();
    }
}
