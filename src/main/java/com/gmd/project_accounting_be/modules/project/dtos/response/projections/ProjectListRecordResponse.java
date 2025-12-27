package com.gmd.project_accounting_be.modules.project.dtos.response.projections;

public interface ProjectListRecordResponse {
    String getUuid();
    String getName();
    String getOwner();
    String getCity();
    String getAddress();
    String getStartDate();
    String getEndDate();
    String getNotes();
}
