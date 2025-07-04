/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.handler;

import io.appium.uiautomator2.core.AccessibilityNodeInfoDumper;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.api.SessionModel;
import io.appium.uiautomator2.model.api.SourceModel;
import io.appium.uiautomator2.utils.Attribute;

import static io.appium.uiautomator2.utils.AXWindowHelpers.refreshAccessibilityCache;
import static io.appium.uiautomator2.utils.Attribute.xmlExposableAttributes;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;

import java.util.HashSet;
import java.util.Set;

/**
 * Get page source. Return as string of XML doc
 */
public class Source extends SafeRequestHandler {
    public Source(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {

        String xmlSource = new AccessibilityNodeInfoDumper(null,xmlExposableAttributes()).dumpToXml();
        return new AppiumResponse(getSessionId(request), xmlSource);
    }
}
