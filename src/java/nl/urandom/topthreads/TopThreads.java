package nl.urandom.topthreads;

import freenet.l10n.BaseL10n.LANGUAGE;
import freenet.l10n.PluginL10n;
import freenet.pluginmanager.*;
import freenet.support.api.HTTPRequest;
import freenet.support.plugins.helpers1.PluginContext;
import freenet.support.plugins.helpers1.WebInterface;


public class TopThreads implements FredPlugin, FredPluginThreadless, FredPluginVersioned,
        FredPluginHTTP, FredPluginL10n, FredPluginBaseL10n {

    public static final String PLUGIN_ROOT = "/TopThreads";
    private static final String PLUGIN_CATEGORY = "Navigation.Menu.TopThreads.Name";

    private PluginRespirator pluginRespirator;
    private PluginContext pluginContext;
    private WebInterface webInterface;
    private PluginL10n l10n;

    @Override
	public void terminate() {
		webInterface.kill();
	}

    @Override
	public void runPlugin(PluginRespirator pr) {
		pluginRespirator = pr;
        pluginContext = new PluginContext(pluginRespirator);
        webInterface = new WebInterface(pluginContext);
        webInterface.addNavigationCategory(PLUGIN_ROOT + "/", PLUGIN_CATEGORY,
                "Navigation.Menu.TopThreads.Tooltip", this);
        webInterface.registerVisible(new TopThreadsToadlet(pluginContext, this), PLUGIN_CATEGORY,
                "Navigation.Menu.TopThreads.Item.TopThreads.Name",
                "Navigation.Menu.TopThreads.Item.TopThreads.Tooltip");
	}

    @Override
    public String handleHTTPGet(HTTPRequest request) throws PluginHTTPException {
        throw new RedirectPluginHTTPException("Redirecting to TopThreads plugin…", PLUGIN_ROOT);
    }

    @Override
    public String handleHTTPPost(HTTPRequest request) throws PluginHTTPException {
        throw new PluginHTTPException("POST requests not implemented", request.getPath());
    }

    @Override
    public String getVersion() {
        return Version.BUILD;
    }

    @Override
    public String getString(String key) {
        String s = l10n.getBase().getString(key);
        return s != null ? s : key;
    }

    @Override
    public void setLanguage(LANGUAGE selectedLanguage) {
        l10n = new PluginL10n(this, selectedLanguage);
    }

    @Override
    public String getL10nFilesBasePath() {
        return "nl/urandom/topthreads/l10n"; //NON-NLS
    }

    @Override
    public String getL10nFilesMask() {
        return "${lang}.properties"; //NON-NLS
    }

    @Override
    public String getL10nOverrideFilesMask() {
        return "${lang}.override.properties"; //NON-NLS
    }

    @Override
    public ClassLoader getPluginClassLoader() {
        return this.getClass().getClassLoader();
    }
}
