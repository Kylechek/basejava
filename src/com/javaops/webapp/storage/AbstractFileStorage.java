package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.io.File;
import java.util.List;

public class AbstractFileStorage extends AbstractStorage {

    public File directory;

    protected AbstractFileStorage(File directory) {
        this.directory = directory;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return null;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {

    }

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }

    @Override
    protected void doDelete(Object searchKey) {

    }

    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }

    @Override
    protected List<Resume> doCopy() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }
}
