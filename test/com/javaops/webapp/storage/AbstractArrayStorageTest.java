package com.javaops.webapp.storage;

import com.javaops.webapp.exeption.NotExistStorageException;
import com.javaops.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractArrayStorageTest {
    private static final String UUID_1 = "uuid1";
    Resume resume1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    Resume resume2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    Resume resume3 = new Resume(UUID_3);

    private Storage storage = new ArrayStorage();

    public AbstractArrayStorageTest(ArrayStorage arrayStorage) {

    }

    public AbstractArrayStorageTest(SortedArrayStorage sortedArrayStorage) {
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
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
        Assert.assertTrue(resume2 == storage.get(UUID_1));
    }

    @Test
    public void save() {
        Assert.assertEquals(3, storage.size());
    }

    @Test (expected = NullPointerException.class)
    public void delete() {
        storage.delete(resume1.getUuid());
        Assert.assertTrue(storage.size() == 2);
        storage.get(UUID_1);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(storage.get(resume1.getUuid()), resume1);
    }

    @Test
    public void getAll() {
        Resume[] array = storage.getAll();
        Assert.assertEquals(resume1, array[0]);
        Assert.assertEquals(resume2, array[1]);
        Assert.assertEquals(resume3, array[2]);
    }

    @Test (expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

}