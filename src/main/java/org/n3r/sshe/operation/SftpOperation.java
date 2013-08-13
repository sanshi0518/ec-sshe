package org.n3r.sshe.operation;

import com.google.common.base.Throwables;
import org.apache.commons.lang3.StringUtils;
import org.n3r.sshe.SsheHost;
import org.n3r.sshe.ssh.Sftp;

public class SftpOperation extends HostOperation {
    private final String commandLine;
    private final String p1;
    private final String p2;
    private final String cmd;

    public SftpOperation(String commandLine) {
        this.commandLine = commandLine;
        String[] fields = StringUtils.split(commandLine);
        if (fields.length != 3) {
            throw new RuntimeException(commandLine + " is invalid.");
        }
        this.cmd = fields[0];
        this.p1 = fields[1];
        this.p2 = fields[2];
    }

    @Override
    public void execute(SsheHost ssheHost) {
        try {
            Sftp.sftp(ssheHost.getSession(), cmd, p1, p2);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }
}