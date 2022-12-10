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
package org.apache.karaf.webconsole.camel.internal.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.karaf.webconsole.core.table.advanced.BaseDataProvider;
import org.apache.wicket.model.IModel;

/**
 * Camel context list data provider.
 */
public class CamelContextsDataProvider extends BaseDataProvider<CamelContext> {

    private static final long serialVersionUID = 1L;

    private final List<CamelContext> contexts;

    public CamelContextsDataProvider(List<CamelContext> contexts) {
        this.contexts = contexts;
    }

    public Iterator<? extends CamelContext> iterator(long first, long count) {
        return new ArrayList<CamelContext>(contexts).subList((int) first, (int) first + (int) count).iterator();
    }

    public long size() {
        return contexts.size();
    }

    public IModel<CamelContext> model(CamelContext object) {
        return new CamelContextModel(contexts, object);
    }
}

