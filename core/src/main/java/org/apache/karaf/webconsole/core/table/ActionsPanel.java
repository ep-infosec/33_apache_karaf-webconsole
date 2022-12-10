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
package org.apache.karaf.webconsole.core.table;

import java.util.Collections;
import java.util.List;

import org.apache.karaf.webconsole.core.behavior.CssBehavior;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;

/**
 * Actions panel used to render actions in data table.
 */
@SuppressWarnings("rawtypes")
public class ActionsPanel<T> extends Panel {

    private static final long serialVersionUID = 1L;

    public ActionsPanel(String componentId, IModel<T> model) {
        super(componentId, model);

        add(new CssBehavior(ActionsPanel.class, "actions.css"));

        final List<Link> links = getLinks(model.getObject(), "action", "label");
        add(new ListView<Link>("actions", new ListModel<Link>(links)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<Link> item) {
                item.addOrReplace(item.getModelObject());

                if (item.getIndex() == 0) {
                    item.add(new AttributeModifier("class", "first"));
                } else if (item.getIndex() + 1 == links.size()) {
                    item.add(new AttributeModifier("class", "last"));
                } else {
                    item.add(new AttributeModifier("class", "node"));
                }
            }
        });
    }

    protected List<Link> getLinks(T object, String linkId, String labelId) {
        return Collections.emptyList();
    }

    /**
     * Gets model
     * 
     * @return model
     */
    @SuppressWarnings("unchecked")
    public final IModel<T> getModel() {
        return (IModel<T>) getDefaultModel();
    }
}
