package com.javaops.webapp.storage;

import com.javaops.webapp.exeption.NotExistStorageException;
import com.javaops.webapp.model.AbstractSection;
import com.javaops.webapp.model.ContactType;
import com.javaops.webapp.model.Resume;
import com.javaops.webapp.model.SectionType;
import com.javaops.webapp.sql.SqlHelper;
import com.javaops.webapp.util.JsonParser;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {

    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume r SET full_name=? WHERE uuid=?")) {
                ps.setString(2, resume.getUuid());
                ps.setString(1, resume.getFullName());
                ps.execute();
            }
            try (PreparedStatement ps = conn.prepareStatement("UPDATE contact c SET type=?,value=? WHERE resume_uuid=?")) {
                insertContacts(resume, ps);
            }
            try (PreparedStatement ps = conn.prepareStatement("UPDATE sections SET type=?,content=? WHERE  resume_uuid=?")) {
                insertSections(resume, ps);
            }
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume(uuid,full_name) VALUES (?,?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact(resume_uuid,type,value) VALUES (?,?,?)")) {
                insertContacts(resume, ps);
            }
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO sections(resume_uuid,type,content) VALUES (?,?,?)")) {
                insertSections(resume, ps);
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume r;
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE resume.uuid=?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                r = new Resume(uuid, rs.getString("full_name"));
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact WHERE resume_uuid=?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(r, rs);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM sections WHERE resume_uuid=?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(r, rs);
                }
            }
            return r;
        });
    }


    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE resume.uuid=?", ps -> {
            ps.setString(1, uuid);
            ps.execute();
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> map = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    map.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume resume = map.get(rs.getString("resume_uuid"));
                    addContact(resume, rs);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM sections")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume resume = map.get(rs.getString("resume_uuid"));
                    addSection(resume, rs);
                }

            }
            return new ArrayList<>(map.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) AS count FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return 0;
            }
            return rs.getInt("count");
        });
    }

    private void addContact(Resume resume, ResultSet rs) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            resume.addContact(ContactType.valueOf(rs.getString("type")), rs.getString("value"));
        }
    }

    private void addSection(Resume resume, ResultSet rs) throws SQLException {
        String content = rs.getString("content");
        if (content != null) {
            SectionType type = SectionType.valueOf(rs.getString("type"));
            resume.addSection(type, JsonParser.read(content, AbstractSection.class));
        }
    }

    private void insertContacts(Resume resume, PreparedStatement ps) throws SQLException {
        for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
            ps.setString(1, resume.getUuid());
            ps.setString(2, e.getKey().name());
            ps.setString(3, e.getValue());
            ps.addBatch();
        }
        ps.executeBatch();
    }

    private void insertSections(Resume resume, PreparedStatement ps) throws SQLException {
        for (Map.Entry<SectionType, AbstractSection> e : resume.getSections().entrySet()) {
            ps.setString(1, resume.getUuid());
            ps.setString(2, e.getKey().name());
            AbstractSection section = e.getValue();
            ps.setString(3, JsonParser.write(section, AbstractSection.class));
            ps.addBatch();
        }
        ps.executeBatch();
    }
}