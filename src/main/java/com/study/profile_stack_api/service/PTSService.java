package com.study.profile_stack_api.service;

import com.study.profile_stack_api.dao.PTSLogDao;
import com.study.profile_stack_api.dto.request.PTSCreateRequest;
import com.study.profile_stack_api.dto.request.PTSUpdateRequest;
import com.study.profile_stack_api.dto.response.PTSLogDeleteResponse;
import com.study.profile_stack_api.dto.response.PTSResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PTSService {
    // 의존성 주입 Repo -> dao
    private final PTSLogDao ptsLogDao;

    // 생성자 주입
    public PTSService(PTSLogDao ptsLogDao) {
        this.ptsLogDao = ptsLogDao;
    }

    public PTSResponse createPTSLog(PTSCreateRequest request) {
        // TODO: Implement logic
        return PTSResponse.builder().build();
    }

    public List<PTSResponse> getAllPTSLogs() {
        // TODO: Implement logic
        return Collections.emptyList();
    }
    
    public List<PTSResponse> getPTSLog(Long id) {
        // TODO: Implement logic
        return Collections.emptyList();
    }

    public PTSResponse updatePTSLog(Long id, PTSUpdateRequest request) {
        // TODO: Implement logic
        return PTSResponse.builder().build();
    }

    public PTSLogDeleteResponse deletePTSLog(Long id) {
        // TODO: Implement logic
        return new PTSLogDeleteResponse();
    }

    public List<PTSResponse> getPTSLogByPosition(String position) {
        // TODO: Implement logic
        return Collections.emptyList();
    }
}
