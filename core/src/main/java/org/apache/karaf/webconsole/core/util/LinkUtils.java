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
package org.apache.karaf.webconsole.core.util;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Utility class to create links.
 */
public abstract class LinkUtils {

    /**
     * Creates new bookmarkable page link to given page class.
     * 
     * @param <T> Type of page.
     * 
     * @param linkId Link element id.
     * @param labelId Inner link element label.
     * @param label Text label.
     * @param page Page class to link.
     * @return Bookmarkable link.
     */
    public static <T extends Page> Link<Page> createPageLink(String linkId, String labelId, String label, Class<T> page) {
        return createPageLink(linkId, labelId, label, page, new PageParameters());
    }

    /**
     * Creates bookmarkable page link with given page class and parameters.
     * 
     * @param <T> Type of page.
     * 
     * @param linkId Link element id.
     * @param labelId Inner link element label.
     * @param label Text label.
     * @param page Page class to link.
     * @param params Page parameters.
     * @return Bookmarkable link.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T extends Page> Link<Page> createPageLink(String linkId, String labelId, String label, Class<T> page, PageParameters params) {
        Link link = new BookmarkablePageLink<T>(linkId, page, params);
        link.add(new Label(labelId, label));
        return link;
    }

    /**
     * Checks if given link is in active trail. It detects a path only from
     * bookmarkable links.
     * 
     * @param link Link.
     * @return True if link path is contained in request path.
     */
    public static boolean isActiveTrail(Link<?> link) {
        if (link instanceof BookmarkablePageLink) {
            RequestCycle requestCycle = RequestCycle.get();
            String requestPath = requestCycle.getRequest().getUrl().toString();
            Class<? extends Page> pageClass = ((BookmarkablePageLink<?>) link).getPageClass();

            String linkPath = requestCycle.urlFor(pageClass, null).toString();

            return requestPath.contains(RequestUtils.toAbsolutePath(requestPath, linkPath));
        }
        return false;
    }
}
