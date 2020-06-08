package com.javaops.webapp.exeption;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " not exist", uuid);
    }
}
