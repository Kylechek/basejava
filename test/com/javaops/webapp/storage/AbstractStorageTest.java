package com.javaops.webapp.storage;

import com.javaops.webapp.exeption.ExistStorageException;
import com.javaops.webapp.exeption.NotExistStorageException;
import com.javaops.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public abstract class AbstractStorageTest {

    private static final String UUID_1 = "uuid1";
    private static final String FULLNAME_1 = "fullName1";
    private static final Resume RESUME_1 = new Resume(UUID_1, FULLNAME_1);

    private static final String UUID_2 = "uuid2";
    private static final String FULLNAME_2 = "fullName2";
    private static final Resume RESUME_2 = new Resume(UUID_2, FULLNAME_2);

    private static final String UUID_3 = "uuid3";
    private static final String FULLNAME_3 = "fullName3";
    private static final Resume RESUME_3 = new Resume(UUID_3, FULLNAME_3);

    private static final String UUID_4 = "uuid4";
    private static final String FULLNAME_4 = "fullName4";
    private static final Resume RESUME_4 = new Resume(UUID_4, FULLNAME_4);

    Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void update() {
        Resume resume2 = new Resume(UUID_1, FULLNAME_1);
        storage.update(resume2);
        Assert.assertEquals(resume2, storage.get(UUID_1));
        storage.update(new Resume("uuid5", "newfullName"));
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        System.out.println(RESUME_4);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(RESUME_4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_2);
    }


    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void getAll() {
        List<Resume> list = storage.getAllSorted();
        Assert.assertEquals(RESUME_1, list.get(0));
        Assert.assertEquals(RESUME_2, list.get(1));
        Assert.assertEquals(RESUME_3, list.get(2));
    }
}