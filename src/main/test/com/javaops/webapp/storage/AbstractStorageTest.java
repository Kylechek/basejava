package com.javaops.webapp.storage;

import com.javaops.webapp.Config;
import com.javaops.webapp.ResumeTestData;
import com.javaops.webapp.exeption.ExistStorageException;
import com.javaops.webapp.exeption.NotExistStorageException;
import com.javaops.webapp.model.ContactType;
import com.javaops.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.assertEquals;


public abstract class AbstractStorageTest extends ResumeTestData {

    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = fillOutResume(UUID_1, "fullName1");
        RESUME_2 = fillOutResume(UUID_2, "fullName2");
        RESUME_3 = fillOutResume(UUID_3, "fullName3");
        RESUME_4 = fillOutResume(UUID_4, "fullName4");
    }

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
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume = fillOutResume(UUID_1, "fullname1");
        RESUME_1.addContact(ContactType.EMAIL, "mail1@google.com");
        RESUME_1.addContact(ContactType.SKYPE, "NewSkype");
        RESUME_1.addContact(ContactType.TEL, "+7 921 222-22-22");
        storage.update(resume);
        assertEquals(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertEquals(4, storage.size());
        assertEquals(RESUME_4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception{
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void getAll() {
        ArrayList<Resume> expectedResumes = new ArrayList<Resume>(Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
        assertEquals(storage.getAllSorted(), expectedResumes);
    }
}