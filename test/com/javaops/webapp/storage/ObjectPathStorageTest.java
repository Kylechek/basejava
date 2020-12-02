package com.javaops.webapp.storage;

import com.javaops.webapp.storage.serializer.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest{
    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new ObjectStreamSerializer()));
    }
}