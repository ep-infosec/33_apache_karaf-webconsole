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
package org.apache.karaf.webconsole.core;

import static org.easymock.EasyMock.anyObject;
import static org.ops4j.pax.wicket.test.spring.SimplifiedPaxWicketInjector.registerBeanInjector;

import java.util.*;

import org.apache.karaf.webconsole.core.brand.DefaultBrandProvider;
import org.apache.karaf.webconsole.core.internal.WebConsoleApplication;
import org.apache.karaf.webconsole.core.preferences.util.JdkPreferencesService;
import org.apache.karaf.webconsole.core.security.KarafJaasWebSession;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.ops4j.pax.wicket.internal.PaxWicketApplicationFactory;
import org.ops4j.pax.wicket.test.spring.SimplifiedPaxWicketInjector;

/**
 * Base class for webconsole tests.
 */
public class WebConsoleTest {

    protected WebApplication application;
    protected WicketTester tester;
    protected SimplifiedPaxWicketInjector injector;

    public WebConsoleTest() {
        super();
    }

    @Before
    public void setUp() {
        application = new WebConsoleApplication() {
            protected java.lang.Class<? extends AuthenticatedWebSession> getWebSessionClass() {
                return WebConsoleTest.this.getWebSessionClass();
            }
        };

        tester = new WicketTester(application);

        injector = registerBeanInjector(tester);
        injector.registerBean("brandProvider", new DefaultBrandProvider());
        injector.registerBean("preferencesService", new JdkPreferencesService());
        injector.registerBean("tabs", new ArrayList<ITab>());
    }

    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return KarafJaasWebSession.class;
    }

    // utility methods for easy mock

    /**
     * Easy mock argument matcher.
     */
    protected final static String anyString() {
        return anyObject();
    }

    /**
     * Empty list stub.
     */
    protected final static List<Link<Page>> emptyLinkList() {
        return Collections.<Link<Page>>emptyList();
    }
}