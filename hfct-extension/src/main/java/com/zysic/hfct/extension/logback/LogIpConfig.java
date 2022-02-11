package com.zysic.hfct.extension.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.zysic.hfct.core.helper.IPHelper;

public class LogIpConfig extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return IPHelper.getLocalIpAddress();
    }

}
