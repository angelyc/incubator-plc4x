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
package org.apache.plc4x.java.isotp.netty.model.tpdus;

import io.netty.buffer.ByteBuf;
import org.apache.plc4x.java.isotp.netty.model.params.Parameter;
import org.apache.plc4x.java.isotp.netty.model.types.ProtocolClass;
import org.apache.plc4x.java.isotp.netty.model.types.TpduCode;

import java.util.List;

public abstract class ConnectionTpdu extends Tpdu {

    private final short destinationReference;
    private final short sourceReference;
    private final ProtocolClass protocolClass;

    public ConnectionTpdu(TpduCode tpduCode, short destinationReference, short sourceReference, ProtocolClass protocolClass, List<Parameter> parameters, ByteBuf userData) {
        super(tpduCode, parameters, userData);
        this.destinationReference = destinationReference;
        this.sourceReference = sourceReference;
        this.protocolClass = protocolClass;
    }

    public short getDestinationReference() {
        return destinationReference;
    }

    public short getSourceReference() {
        return sourceReference;
    }

    public ProtocolClass getProtocolClass() {
        return protocolClass;
    }

}
