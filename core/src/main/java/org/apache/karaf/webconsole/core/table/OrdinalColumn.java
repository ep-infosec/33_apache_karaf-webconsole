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

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 * Column with ordinal number.
 */
public class OrdinalColumn<T> extends AbstractColumn<T, String> {

    private static final long serialVersionUID = 1L;

    public OrdinalColumn(String displayModel, String sortProperty) {
        super(Model.of(displayModel), sortProperty);
    }

    public OrdinalColumn(String displayModel) {
        super(Model.of(displayModel));
    }

    public OrdinalColumn() {
        this("No.", "no");
    }

    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
        cellItem.add(new Label(componentId, new PropertyModel<Integer>(cellItem, "parent.parent.index") {
            private static final long serialVersionUID = 1L;

            @Override
            public Integer getObject() {
                // start counting from 1, not from 0
                return super.getObject() + 1;
            }
        }));
    }

}
