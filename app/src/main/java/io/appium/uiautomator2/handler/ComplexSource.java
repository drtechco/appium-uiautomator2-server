package io.appium.uiautomator2.handler;

import static io.appium.uiautomator2.utils.AXWindowHelpers.refreshAccessibilityCache;
import static io.appium.uiautomator2.utils.Attribute.xmlExposableAttributes;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;

import java.util.HashSet;
import java.util.Set;

import io.appium.uiautomator2.core.AccessibilityNodeInfoDumper;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.api.SourceModel;
import io.appium.uiautomator2.utils.Attribute;

public class ComplexSource extends SafeRequestHandler {
    public ComplexSource(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        SourceModel sourceModel = toModel(request, SourceModel.class);

        refreshAccessibilityCache();
        Set<Attribute> includeAttrs = new HashSet<>();
        Set<Attribute> allAttr = xmlExposableAttributes();
        for (Attribute attr : allAttr) {
            boolean enabled = false;
            for (String alias : attr.getAliases()) {
                for (String enableAttr : sourceModel.enableAttrs) {
                    if (alias.equalsIgnoreCase(enableAttr)) {
                        enabled = true;
                        break;
                    }
                }
            }
            if (enabled){
                includeAttrs.add(attr);
            }
        }
        String xmlSource = new AccessibilityNodeInfoDumper(null,includeAttrs).dumpToXml();
        return new AppiumResponse(getSessionId(request), xmlSource);
    }
}
