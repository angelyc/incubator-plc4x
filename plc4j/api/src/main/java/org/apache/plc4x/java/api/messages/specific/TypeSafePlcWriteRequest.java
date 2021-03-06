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
package org.apache.plc4x.java.api.messages.specific;

import org.apache.plc4x.java.api.messages.PlcWriteRequest;
import org.apache.plc4x.java.api.messages.items.WriteRequestItem;
import org.apache.plc4x.java.api.model.Address;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TypeSafePlcWriteRequest<T> extends PlcWriteRequest {

    private final Class<T> dataType;

    public TypeSafePlcWriteRequest(Class<T> dataType) {
        Objects.requireNonNull(dataType, "Data type must not be null");
        this.dataType = dataType;
    }

    public TypeSafePlcWriteRequest(Class<T> dataType, PlcWriteRequest plcWriteRequest) {
        this(dataType);
        Objects.requireNonNull(plcWriteRequest, "PLC write request must not be null");
        Objects.requireNonNull(plcWriteRequest.getRequestItems(), "plc write request item on " + plcWriteRequest + " must not be null");
        for (WriteRequestItem<?> WriteRequestItem : plcWriteRequest.getRequestItems()) {
            addItem(WriteRequestItem);
        }
    }

    @SafeVarargs
    public TypeSafePlcWriteRequest(Class<T> dataType, Address address, T... values) {
        this(dataType);
        Objects.requireNonNull(address, "Address must not be null");
        addItem(new WriteRequestItem<>(dataType, address, values));
    }

    @SafeVarargs
    public TypeSafePlcWriteRequest(Class<T> dataType, WriteRequestItem<T>... requestItems) {
        this(dataType);
        Objects.requireNonNull(requestItems, "Request item must not be null");
        for (WriteRequestItem<T> requestItem : requestItems) {
            getRequestItems().add(requestItem);
        }
    }

    @Override
    public void addItem(WriteRequestItem<?> writeRequestItem) {
        Objects.requireNonNull(writeRequestItem, "Write request item must not be null");
        if (writeRequestItem.getDatatype() != dataType) {
            throw new IllegalArgumentException("Incompatible dataType " + writeRequestItem.getDatatype());
        }
        super.addItem(writeRequestItem);
    }

    @SuppressWarnings("unchecked")
    public List<WriteRequestItem<T>> getCheckedRequestItems() {
        return (List) getRequestItems();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<WriteRequestItem<T>> getRequestItem() {
        return (Optional<WriteRequestItem<T>>) super.getRequestItem();
    }

    public Class<T> getDataType() {
        return dataType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeSafePlcWriteRequest)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TypeSafePlcWriteRequest<?> that = (TypeSafePlcWriteRequest<?>) o;
        return Objects.equals(dataType, that.dataType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), dataType);
    }
}
