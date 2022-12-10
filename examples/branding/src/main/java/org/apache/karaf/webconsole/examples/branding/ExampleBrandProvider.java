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
package org.apache.karaf.webconsole.examples.branding;

import java.util.LinkedList;
import java.util.List;

import org.apache.karaf.webconsole.core.behavior.CssBehavior;
import org.apache.karaf.webconsole.core.brand.BrandProvider;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.resource.PackageResourceReference;

/**
 * Example brand provider which modify console look and feel.
 */
public class ExampleBrandProvider implements BrandProvider {

    public Image getHeaderImage(String imageId) {
        return new Image(imageId, new PackageResourceReference(ExampleBrandProvider.class, "logo.png"));
    }

    public List<Behavior> getBehaviors() {
        List<Behavior> behaviors = new LinkedList<Behavior>();
        behaviors.add(new CssBehavior(ExampleBrandProvider.class, "override.css"));
        return behaviors;
    }

    public void modify(Page page) {
        page.addOrReplace(new Label("footer", "Branded WebConsole"));
    }
}