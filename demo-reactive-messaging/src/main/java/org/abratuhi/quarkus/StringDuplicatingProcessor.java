package org.abratuhi.quarkus;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.kafka.Record;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
@ApplicationScoped
public class StringDuplicatingProcessor {
    @Incoming("string-in")
    @Outgoing("string-out")
    public Record<String, String> x2(byte[] bytes) {
        String str = new String(bytes);
        log.infov("Received {0}", str);
        return Record.of(str, str + str);
    }
}
