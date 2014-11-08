package nl.urandom.topthreads;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.net.URI;

import freenet.clients.http.*;
import freenet.pluginmanager.FredPluginL10n;
import freenet.support.HTMLNode;
import freenet.support.api.HTTPRequest;
import freenet.support.plugins.helpers1.PluginContext;
import freenet.support.plugins.helpers1.WebInterfaceToadlet;

/**
 * FIXME Javadoc
 *
 * @author bertm
 */
public class TopThreadsToadlet extends WebInterfaceToadlet {
    private final FredPluginL10n l10n;

    protected TopThreadsToadlet(PluginContext pluginContext, FredPluginL10n l10n) {
        super(pluginContext, TopThreads.PLUGIN_ROOT, "");
        this.l10n = l10n;
    }

    @Override
    public void handleMethodGET(URI uri, HTTPRequest httpRequest, ToadletContext toadletContext)
            throws ToadletContextClosedException, IOException, RedirectException {
        PageNode page =
                pluginContext.pageMaker.getPageNode(_("Page.TopThreads.Title"), toadletContext);
        HTMLNode outer = page.outer;
        HTMLNode contentNode = page.content;
        InfoboxNode box = pluginContext.pageMaker.getInfobox(_("Infobox.TopThreads.Threads.Title"));
        HTMLNode content = box.content;

        ThreadData[] threads = Threads.getTop(15);

        for (ThreadData t : threads) {
            HTMLNode p = new HTMLNode("p"); //NON-NLS
            p.addChild("#", t.toString());
            content.addChild(p);
        }

        contentNode.addChild(box.outer);
        writeHTMLReply(toadletContext, 200, "OK", outer.generate()); //NON-NLS
    }

    private String _(String key) {
        return l10n.getString(key);
    }
}
