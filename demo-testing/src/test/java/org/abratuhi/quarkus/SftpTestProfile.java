package org.abratuhi.quarkus;

import java.util.List;

import io.quarkus.test.junit.QuarkusTestProfile;

public class SftpTestProfile implements QuarkusTestProfile {
    @Override
    public List<TestResourceEntry> testResources() {
        return List.of(new TestResourceEntry(SftpTestResource.class));
    }

    @Override
    public boolean disableGlobalTestResources() {
        return true;
    }
}