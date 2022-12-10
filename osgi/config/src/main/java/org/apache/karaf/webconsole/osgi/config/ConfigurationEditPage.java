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
package org.apache.karaf.webconsole.osgi.config;

import java.io.IOException;
import java.util.Map;

import org.apache.karaf.webconsole.core.form.MapEditForm;
import org.apache.karaf.webconsole.core.table.map.MapDataProvider;
import org.apache.karaf.webconsole.core.table.map.MapDataTable;
import org.apache.karaf.webconsole.core.util.DictionaryUtils;
import org.apache.karaf.webconsole.osgi.config.model.ConfigurationModel;
import org.apache.karaf.webconsole.osgi.core.shared.OsgiPage;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * Configuration edit page.
 */
@PaxWicketMountPoint(mountPoint = "/osgi/configuration/edit")
public class ConfigurationEditPage extends OsgiPage {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "configurationAdmin")
    private ConfigurationAdmin configurationAdmin;
    private String pid;

    @SuppressWarnings("serial")
    public ConfigurationEditPage(PageParameters params) {
        pid = params.get("pid").toString();

        add(new Label("pid", pid));
        ConfigurationModel configuration = new ConfigurationModel(pid, configurationAdmin);
        setDefaultModel(configuration);

        @SuppressWarnings("unchecked")
        Map<String, String> properties = DictionaryUtils.map(configuration.getObject().getProperties());
        Map<String, String> system = ConfigurationFilterUtil.filter(properties);

        MapEditForm<String, String> mapEditForm = new MapEditForm<String, String>("edit", new CompoundPropertyModel<Map<String, String>>(properties)) {
            @Override
            protected void onSubmit() {
                Map<String, String> map = getModelObject();

                Configuration configuration = (Configuration) ConfigurationEditPage.this.getDefaultModelObject();
                try {
                    if (configuration.getBundleLocation() != null) {
                        configuration.setBundleLocation(null);
                    }
                    configuration.update(DictionaryUtils.dictionary(map));

                    Session.get().info("Configuration " + pid + " updated.");
                    RequestCycle.get().setResponsePage(ConfigurationsPage.class);
                } catch (IOException e) {
                    error("Unable to update configuration " + e.getMessage());
                }
            }
        };
        mapEditForm.add(new SubmitLink("submit"));
        add(mapEditForm);

        add(new MapDataTable<String, String>("system", new MapDataProvider<String, String>(system), 5));
    }

}
