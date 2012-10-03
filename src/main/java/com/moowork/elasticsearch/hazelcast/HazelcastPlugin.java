package com.moowork.elasticsearch.hazelcast;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.AbstractPlugin;

public final class HazelcastPlugin
    extends AbstractPlugin
{
    private final Settings settings;

    public HazelcastPlugin( final Settings settings )
    {
        this.settings = settings;
    }

    @Override
    public String name()
    {
        return "hazelcast";
    }

    @Override
    public String description()
    {
        return "Hazelcast Plugin Version 1.0.0-SNAPSHOT";
    }
}
