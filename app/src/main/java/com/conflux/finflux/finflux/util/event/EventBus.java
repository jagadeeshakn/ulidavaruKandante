package com.conflux.finflux.finflux.util.event;

import com.squareup.otto.Bus;

/**
 * Created by Praveen J U on 7/10/2016.
 */
public class EventBus extends Bus {

    private static final EventBus bus = new EventBus();

    public static EventBus getInstance(){return bus;}

    public EventBus(){}
}
