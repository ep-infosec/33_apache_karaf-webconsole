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
package org.apache.karaf.webconsole.servicemix.internal.navigation;

import java.util.ArrayList;
import java.util.List;

import org.apache.karaf.webconsole.core.navigation.ConsoleTabProvider;
import org.apache.karaf.webconsole.servicemix.internal.EndpointsPage;
import org.apache.karaf.webconsole.servicemix.internal.TrackNmrPage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

/**
 * ServiceMix tab in console.
 */
public class ServiceMixConsoleTabProvider implements ConsoleTabProvider {

    public List<Link<Page>> getItems(String componentId, String labelId) {
        List<Link<Page>> links = new ArrayList<Link<Page>>();

        Link<Page> link = new BookmarkablePageLink<Page>(componentId, TrackNmrPage.class);
        link.add(new Label(labelId, "Track"));

        return links;
    }

    public Link<Page> getModuleLink(String componentId, String labelId) {
        Link<Page> link = new BookmarkablePageLink<Page>(componentId, EndpointsPage.class);
        link.add(new Label(labelId, "ServiceMix"));
        return link;
    }

}
