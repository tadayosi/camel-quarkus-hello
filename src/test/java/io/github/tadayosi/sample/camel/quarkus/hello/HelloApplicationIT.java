package io.github.tadayosi.sample.camel.quarkus.hello;

import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloApplicationIT {

    private KubernetesClient client;

    @BeforeEach
    void setUp() {
        client = new DefaultKubernetesClient();
    }

    @Test
    void testPodsReady() {
        PodList podList = client.pods()
            .withLabel("app", "camel")
            .withLabel("app.kubernetes.io/name", "camel-quarkus-hello")
            .list();
        assertEquals(2, podList.getItems().size());
        podList.getItems().forEach(pod -> {
            pod.getStatus().getConditions().stream()
                .filter(c -> "Ready".equals(c.getType()))
                .forEach(c -> {
                    assertEquals("True", c.getStatus());
                });
        });
    }
}
