package com.javaops.webapp.model;

import java.util.Arrays;
import java.util.List;

public class OrganizationAbstractSection extends AbstractSection {
    private List<Organization> organizations;

    public OrganizationAbstractSection(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationAbstractSection(List<Organization> organizations) {
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
