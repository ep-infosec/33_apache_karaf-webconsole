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
package org.apache.karaf.webconsole.karaf.admin.settings;

import org.apache.karaf.webconsole.karaf.admin.model.WicketInstanceSettings;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

/**
 * Instance settings modification form.
 */
public class InstanceSettingsForm extends Form<WicketInstanceSettings> {

    private static final long serialVersionUID = 1L;

    public InstanceSettingsForm(String id, IModel<WicketInstanceSettings> model) {
        super(id, new CompoundPropertyModel<WicketInstanceSettings>(model));

        add(new TextField<Integer>("sshPort"));
        add(new TextField<Integer>("rmiRegistryPort"));
        add(new TextField<Integer>("rmiServerPort"));

        add(new TextArea<String>("javaOpts"));
        add(new TextField<String>("location"));

        // we need additional checks here
        //add(new TextArea<String>("features"));
        //add(new TextArea<String>("featureURLs"));
    }

}
