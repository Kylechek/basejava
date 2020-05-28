package com.javaops.webapp.exeption;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume" + uuid + "already exist", uuid);
    }
}
