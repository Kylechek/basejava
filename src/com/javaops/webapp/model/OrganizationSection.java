package com.javaops.webapp.model;

import java.util.Arrays;
import java.util.List;

public class OrganizationSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private final List<Organization> organizations;

    public OrganizationSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Organization> getOrganization() {
        return organizations;
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}
