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
package org.apache.karaf.webconsole.osgi.log;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.karaf.webconsole.core.table.advanced.BaseDataTable;
import org.apache.karaf.webconsole.osgi.core.shared.OsgiPage;
import org.apache.karaf.webconsole.osgi.log.search.Matcher;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogReaderService;

/**
 * Page with log table and search form.
 */
@PaxWicketMountPoint(mountPoint = "/osgi/log")
public class LogsPage extends OsgiPage {

    private static final long serialVersionUID = 1L;

    @PaxWicketBean(name = "logReader")
    private LogReaderService logReader;

    @PaxWicketBean(name = "matchers")
    private List<Matcher> matchers;

    private Options options = new Options();

    @SuppressWarnings("serial")
    public LogsPage() {
        CompoundPropertyModel<Options> model = new CompoundPropertyModel<Options>(new PropertyModel<Options>(this, "options"));
        setDefaultModel(model);

        @SuppressWarnings("unchecked")
        IColumn<LogEntry, String>[] columns = new IColumn[] {
            new AbstractColumn<LogEntry, String>(Model.of("time")) {
                public void populateItem(Item<ICellPopulator<LogEntry>> cellItem, String componentId, IModel<LogEntry> rowModel) {
                    long time = rowModel.getObject().getTime();
                    DateFormat format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL);
                    cellItem.add(new Label(componentId, format.format(new Date(time))));
                }
            },
            new AbstractColumn<LogEntry, String>(Model.of("level")) {
                public void populateItem(Item<ICellPopulator<LogEntry>> cellItem, String componentId, IModel<LogEntry> rowModel) {
                    cellItem.add(new Label(componentId, Priority.valueOf(rowModel.getObject()).name()));
                }
            },
            new PropertyColumnExt<LogEntry>("Bundle", "bundle.symbolicName"),
            new PropertyColumnExt<LogEntry>("Version", "bundle.version"),
            new PropertyColumnExt<LogEntry>("Message", "message"),
            new PropertyColumnExt<LogEntry>("Exception", "exception"),
        };

        add(new OptionsForm("filters", model));

        LogEntriesDataProvider provider = new LogEntriesDataProvider(logReader, options, matchers);
        add(new BaseDataTable<LogEntry>("logs", Arrays.asList(columns), provider, 20));
    }
}
