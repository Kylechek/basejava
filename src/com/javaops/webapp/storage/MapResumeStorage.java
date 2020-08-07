package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage{

    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        map.put(resume.getUuid(),resume);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        map.put(resume.getUuid(),resume);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doDelete(Object resume) {
        map.remove(((Resume)resume).getUuid());
    }

    @Override
    protected Resume doGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList(map.values());
        Collections.sort(result);
        return result;
    }

    @Override
    public int size() {
        return map.size();
    }
}
