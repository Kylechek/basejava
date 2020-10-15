package com.javaops.webapp.storage;

import com.javaops.webapp.exeption.StorageException;
import com.javaops.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    public File directory;

    protected AbstractFileStorage(File directory) {
        this.directory = directory;
    }

    protected abstract void doWrite(Resume resume, OutputStream file) throws IOException;

    protected abstract Resume doRead(InputStream file) throws IOException;

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName());
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
            doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid());
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
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName());
        }
    }

    @Override
    protected List<Resume> doCopy() {
        File[] files = directory.listFiles();
        List<Resume> list = new ArrayList<>(files.length);
        if (files == null) {
            throw new StorageException("Directory hasn't files", null);
        }
        for (File file : files) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                doDelete(file);
            }
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if (list == null) {
            throw new StorageException("Directory read error", null);
        }
        return list.length;
    }
}
