package org.abratuhi.quarkus;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import lombok.extern.jbosslog.JBossLog;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;

@JBossLog
@QuarkusTest
//@QuarkusTestResource(SftpTestResource.class)
@TestProfile(SftpTestProfile.class)
public class SftpResourceTest {
  @Inject
  SshClientFactory sshClientFactory;

  @Test
  void fileIsUploaded() {
    try (
        SSHClient sshClient = sshClientFactory.getClient();
        SFTPClient sftp = sshClient.newSFTPClient()
    ) {
      // precondition/ pre-assert
      assertThat(sftp.ls("upload")).hasSize(0);

      // action
      given()
          .when().post("/sftp")
          .then()
          .statusCode(HttpStatus.SC_CREATED);

      // postcondition/ assert
      assertThat(sftp.ls("upload")).hasSize(1);
      assertThat(sftp.ls("upload").get(0).getName()).contains(".zshrc");

      log.info(sftp.ls("upload"));

    } catch (IOException e) {
      fail("Something wrong happened", e);
    }
  }
}