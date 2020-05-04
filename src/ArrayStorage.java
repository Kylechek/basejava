/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = size - 1; i > 0; i--) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (storage[size] == null) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
            i++;
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume newstorage[] = new Resume[size];
        for (int i = 0; i < size; i++) {
            newstorage[i] = storage[i];
        }
        return newstorage;
    }

    int size() {
        return size;
    }

}