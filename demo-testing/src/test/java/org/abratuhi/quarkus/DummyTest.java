package org.abratuhi.quarkus;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;


@QuarkusTest
public class DummyTest {
  @Test
  void doNothing() {
    assertThat(List.of()).isEmpty();
  }
}