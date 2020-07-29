package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public abstract class ListStorage extends AbstractStorage{

    List<Resume> list = new ArrayList<>();

    public Integer getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    public void clear() {
        list.clear();
    }

    public void save(Resume resume, Object searchKey) {
        list.add(resume);
    }

    public void delete(String uuid, Object searchKey) {
        list.remove(uuid);
    }

    public int size() {
        return list.size();
    }
}
