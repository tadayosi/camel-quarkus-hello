package io.github.tadayosi.sample.camel.quarkus.hello;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.util.InetAddressUtil;

public class HelloRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:hello?period={{timer.period}}")
            .setBody().constant("Hello from " + InetAddressUtil.getLocalHostName())
            .log("${body}");
    }
}
