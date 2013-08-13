package org.n3r.sshe.operation;

import com.google.common.base.Throwables;
import org.apache.commons.lang3.StringUtils;
import org.n3r.sshe.SsheHost;
import org.n3r.sshe.ssh.Scp;

public class ScpOperation extends HostOperation {
    private final String commandLine;
    private final String p1;
    private final String p2;

    public ScpOperation(String commandLine) {
        this.commandLine = commandLine;
        String[] fields = StringUtils.split(commandLine);
        if (fields.length != 2) {
            throw new RuntimeException(commandLine + " is invalid.");
        }

        this.p1 = fields[0];
        this.p2 = fields[1];
    }

    @Override
    public void execute(SsheHost ssheHost) {
        try {
            Scp.scp(ssheHost.getSession(), p1, p2);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }
}
