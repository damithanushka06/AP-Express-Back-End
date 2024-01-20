package com.ap_express_server.repository.workflow;
import com.ap_express_server.models.common.WorkflowConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkFlowRepository extends JpaRepository<WorkflowConfig, Long> {
    List<WorkflowConfig> findByDocumentIdAndDocumentTypeId(Integer documentId, int documentTypeId);
}
