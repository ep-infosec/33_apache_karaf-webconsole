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
package org.apache.karaf.webconsole.karaf.feature.model;

import java.net.URI;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.features.Repository;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * Repository model.
 */
public class RepositoryModel extends LoadableDetachableModel<Repository> {

    private static final long serialVersionUID = 1L;

    private final FeaturesService service;
    private URI uri;

    public RepositoryModel(FeaturesService service, Repository object) {
        super(object);
        this.service = service;
        this.uri = object.getURI();
    }

    @Override
    protected Repository load() {
        Repository[] repositories = service.listRepositories();
        for (Repository repo : repositories) {
            if (uri.equals(repo.getURI())) {
                return repo;
            }
        }

        throw new RepositoryNotFoundException(uri);
    }

}
