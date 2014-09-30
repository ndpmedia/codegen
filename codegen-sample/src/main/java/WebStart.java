

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebStart {

    private Server server;
    
    public void startServer() throws Exception {
        server = new Server(6666);
        server.setStopAtShutdown(true);
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        webAppContext.setResourceBase("src/main/webapp");
        webAppContext.setClassLoader(getClass().getClassLoader());
        server.setHandler(webAppContext);
        server.start();

        waitForAnyKey();

    }

    protected void waitForAnyKey() throws IOException {
        String timestamp = new SimpleDateFormat("MM-dd HH:mm:ss.SSS")
                .format(new Date());

        System.out.println(String.format(
                "[%s] [INFO] Press any key to stop server ... ", timestamp));
        System.in.read();
    }


    public void shutdownServer() throws Exception {
        server.stop();
    }

    public static void main(String[] args) throws Exception {
        WebStart server = new WebStart();
        server.startServer();
        server.shutdownServer();
    }


}
