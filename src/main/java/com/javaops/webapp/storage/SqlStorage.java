package com.javaops.webapp.storage;

import com.javaops.webapp.exeption.NotExistStorageException;
import com.javaops.webapp.exeption.StorageException;
import com.javaops.webapp.model.Resume;
import com.javaops.webapp.sql.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        try(Connection conn = connectionFactory.getConnection();
         PreparedStatement ps = conn.prepareStatement("DELETE FROM resume")){
         ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void update(Resume resume) {
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")){
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void save(Resume resume) {
        try(Connection conn = connectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Resume get(String uuid) {
        try(Connection conn = connectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r WHERE r.uuid =?")){
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid,rs.getString("full_name"));

        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {
        try(Connection conn = connectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM resume WHERE uuid")){
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        try(Connection conn = connectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume")){
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while(rs.next()) {
                resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return resumes;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public int size() {
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT count(*) AS count FROM resume")){
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return 0;
            }
            return rs.getInt("count");
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}