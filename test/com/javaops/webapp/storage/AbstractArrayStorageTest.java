package com.javaops.webapp.storage;

import com.javaops.webapp.exeption.ExistStorageException;
import com.javaops.webapp.exeption.NotExistStorageException;
import com.javaops.webapp.exeption.StorageException;
import com.javaops.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractArrayStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final Resume resume1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume resume2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume resume3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume resume4 = new Resume(UUID_4);

    private Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume2 = new Resume(UUID_1);
        storage.update(resume2);
        Assert.assertEquals(resume2, storage.get(UUID_1));
    }

    @Test
    public void save() {
        storage.save(resume4);
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        storage.save(new Resume());
    }

    @Test (expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(resume2.getUuid());
        Assert.assertEquals(storage.size(), 2);
        storage.get(UUID_2);
    }

    @Test (expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(resume4.getUuid());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(resume1, storage.get(resume1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(resume4.getUuid());
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(resume1, resumes[0]);
        Assert.assertEquals(resume2, resumes[1]);
        Assert.assertEquals(resume3, resumes[2]);
    }
}