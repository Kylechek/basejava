package com.javaops.webapp.model;

import java.util.List;

public class OrganizationsSection extends Section{
    private final List<Organizations> organizations;

    public OrganizationsSection(List<Organizations> organizations) {
        this.organizations = organizations;
    }

    public List<Organizations> getOrganizations() {
        return organizations;
    }
}
