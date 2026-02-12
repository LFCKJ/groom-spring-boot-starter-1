package com.study.profile_stack_api.service;

import com.study.profile_stack_api.dto.request.PTSCreateRequest;
import com.study.profile_stack_api.dto.response.PTSLogDeleteResponse;
import com.study.profile_stack_api.dto.response.PTSResponse;

import java.util.List;

public class PTSService {
    public static PTSResponse getPTSController(PTSCreateRequest request) {
        return PTSService.getPTSController(request);
    }

    public static List<PTSResponse> getAllPTSLogs() {
        return  PTSService.getAllPTSLogs();
    }

    public static PTSResponse updatePTSLog(Long id, PTSUpdateRequest request) {
    }

    public static PTSLogDeleteResponse deletePTSLog(Long id) {
    }

    public static List<PTSResponse> getPTSLogByPositon(String position) {
    }
}
