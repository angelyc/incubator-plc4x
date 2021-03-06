/*
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */
package org.apache.plc4x.java.api.connection;

import org.apache.plc4x.java.api.messages.PlcProprietaryRequest;
import org.apache.plc4x.java.api.messages.PlcProprietaryResponse;

import java.util.concurrent.CompletableFuture;

/**
 * Interface implemented by all PlcConnections that are able to send custom commands to resources.
 */
@FunctionalInterface
public interface PlcProprietarySender {

    /**
     * Sends a given custom request to a PLC.
     *
     * @param request request to be send.
     * @param <T>     the request type
     * @param <R>     the response type
     * @return a {@link CompletableFuture} giving async access to the response of the send operation.
     */
    <T, R> CompletableFuture<PlcProprietaryResponse<R>> send(PlcProprietaryRequest<T> request);
}
