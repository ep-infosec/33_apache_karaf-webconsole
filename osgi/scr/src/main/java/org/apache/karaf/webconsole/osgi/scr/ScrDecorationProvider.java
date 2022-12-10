/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.karaf.webconsole.osgi.scr;

import org.apache.felix.scr.ScrService;
import org.apache.karaf.webconsole.core.behavior.CssBehavior;
import org.apache.karaf.webconsole.core.panel.CssImagePanel;
import org.apache.karaf.webconsole.osgi.core.spi.IDecorationProvider;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.osgi.framework.Bundle;

/**
 * A decoration provider which add scr icon in first column of bundles.
 */
public class ScrDecorationProvider implements IDecorationProvider {

    public Panel getDecoration(String componentId, IModel<Bundle> model) {
        Bundle bundle = model.getObject();
        ScrService scr = ScrComponent.getScrService();

        if (scr == null || scr.getComponents(bundle) == null) {
            return null;
        }

        // if scr is present and components are not null - then we have a scr components
        CssImagePanel imagePanel = new CssImagePanel(componentId, "scr");
        imagePanel.add(new CssBehavior(getClass(), "decorator.css"));
        return imagePanel;
    }

}
