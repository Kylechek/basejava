package com.javaops.webapp.storage;

import com.javaops.webapp.exeption.StorageException;
import com.javaops.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("fullName1"));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("fullName1"));
    }

}