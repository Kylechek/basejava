package com.javaops.webapp.storage;

import com.javaops.webapp.storage.serializer.JsonStreamSerializer;
import com.javaops.webapp.storage.serializer.XmlStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest{
    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new JsonStreamSerializer()));
    }
}