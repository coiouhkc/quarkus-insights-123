package org.abratuhi.quarkus;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import lombok.extern.jbosslog.JBossLog;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;

@JBossLog
@Path("/sftp")
public class SftpResource {

  @Inject
  SshClientFactory sshClientFactory;

  @POST
  public Response create() {
    try (
        SSHClient sshClient = sshClientFactory.getClient();
        SFTPClient sftp = sshClient.newSFTPClient()
    ) {

      sftp.put("src/main/resources/.zshrc", "upload/.zshrc");

      return Response
          .created(URI.create("/"))
          .build();
    } catch (Exception e) {
      log.error(e);
      return Response.serverError().build();
    }
  }
}