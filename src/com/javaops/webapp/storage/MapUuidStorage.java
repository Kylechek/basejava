package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doSave(Resume resume, Object uuid) {
        map.put((String) uuid, resume);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return (String) uuid;
    }

    @Override
    protected void doUpdate(Resume resume, Object uuid) {
        map.put((String) uuid,resume);
    }

    @Override
    protected boolean isExist(Object uuid) {
        return map.containsKey((String) uuid);
    }

    @Override
    protected void doDelete(Object uuid) {
        map.remove((String) uuid);
    }

    @Override
    protected Resume doGet(Object uuid) {
        return map.get((String) uuid);
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
