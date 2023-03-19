import com.isyscore.kotlin.swing.component.Browser;
import me.friwi.jcefmaven.CefAppBuilder;
import me.friwi.jcefmaven.CefInitializationException;
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter;
import me.friwi.jcefmaven.UnsupportedPlatformException;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.browser.CefMessageRouter;
import org.cef.handler.CefLifeSpanHandlerAdapter;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TestCEFJava {

    class MainForm extends JFrame {
        public MainForm(String title, String url) {
            super(title);
            try {
                CefAppBuilder builder = new CefAppBuilder();
                builder.getCefSettings().windowless_rendering_enabled = false;
                builder.setAppHandler(new MavenCefAppHandlerAdapter() {
                    @Override
                    public void stateHasChanged(CefApp.CefAppState state) {
                        if (state == CefApp.CefAppState.TERMINATED) {
                            System.exit(0);
                        }
                    }
                });
                CefApp app = builder.build();
                CefClient client = app.createClient();
                client.addLifeSpanHandler(new CefLifeSpanHandlerAdapter() {
                    @Override
                    public boolean onBeforePopup(CefBrowser browser, CefFrame frame, String target_url, String target_frame_name) {
                        browser.loadURL(target_url);
                        return true;
                    }
                });

                CefMessageRouter msgRouter = CefMessageRouter.create();
                client.addMessageRouter(msgRouter);
                CefBrowser browser = client.createBrowser(url, false, false);
                Component ui = browser.getUIComponent();

                JPanel pnl = new JPanel(new BorderLayout());
                pnl.add(ui, BorderLayout.CENTER);
                setContentPane(pnl);
            } catch (IOException | UnsupportedPlatformException | InterruptedException | CefInitializationException e) {

            }

            setSize(1024, 768);
            setLocationRelativeTo(null);
            setVisible(true);
        }

    }

    @Test
    public void test() {
        MainForm form = new MainForm("Sample", "https://www.isyscore.com");
        while (form.isVisible()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

}
