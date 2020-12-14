package com.javaops.webapp.storage.serializer;

import com.javaops.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializer {
    void doWrite(Resume resume, OutputStream path) throws IOException;

    Resume doRead(InputStream path) throws IOException;
}
