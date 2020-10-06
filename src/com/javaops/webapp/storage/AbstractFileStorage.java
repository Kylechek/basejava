package com.javaops.webapp.storage;

import com.javaops.webapp.exeption.StorageException;
import com.javaops.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    public File directory;

    protected AbstractFileStorage(File directory) {
        this.directory = directory;
    }
    protected abstract void doWrite(Resume resume, File file) throws IOException;
    protected abstract Resume doRead(File file);

    @Override
    protected void doSave(Resume resume, File file) {
            try {
                file.createNewFile();
                doWrite(resume, file);
            } catch (IOException e) {

            }
            doUpdate(resume, file);
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean isExist(File file) {
        return false;
    }

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected Resume doGet(File file) {
        return doRead(file);
    }

    @Override
    protected List<Resume> doCopy() {
        File[] files = directory.listFiles();
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for( File file: files) {
                doDelete(file);
            }
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if(list ==null) {
            throw new StorageException("Directory read error", null);
        }
        return list.length;
    }
}
