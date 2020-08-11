package com.moon.demo.golf23.o_listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class WeatherRunListener {

//    @Autowired
    private WeatherEventMulticaster eventMulticaster;

    public void snow() {
        eventMulticaster.multicastEvent(new SnowEvent());
    }

    public void rain() {
        eventMulticaster.multicastEvent(new RainEvent());
    }

}
