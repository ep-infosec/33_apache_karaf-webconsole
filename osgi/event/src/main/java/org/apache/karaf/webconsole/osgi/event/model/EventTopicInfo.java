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
package org.apache.karaf.webconsole.osgi.event.model;

import java.io.Serializable;

/**
 * POJO to collect a topic name and number of subscribers/consumers.
 */
public class EventTopicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String topic;
    private int consumers;

    public EventTopicInfo(String topic, int consumers) {
        this.topic = topic;
        this.consumers = consumers;
    }

    public String getTopic() {
        return topic;
    }

    public int getConsumers() {
        return consumers;
    }

    public void addConsumer() {
        consumers++;
    }
}
