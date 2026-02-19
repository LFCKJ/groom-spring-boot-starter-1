package com.study.profile_stack_api.controller;

import com.study.profile_stack_api.dto.request.PTSCreateRequest;
import com.study.profile_stack_api.dto.request.PTSUpdateRequest;
import com.study.profile_stack_api.dto.response.PTSLogDeleteResponse;
import com.study.profile_stack_api.dto.response.PTSResponse;
import com.study.profile_stack_api.service.PTSService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profile")
public class PTSController {
    
    private final PTSService ptsService;

    public PTSController(PTSService ptsService) {
        this.ptsService = ptsService;
    }

    //=================================================================================
    //======================CREATE=====================================================
    @PostMapping
    public ResponseEntity<PTSResponse> createPTSLog(@RequestBody PTSCreateRequest request){
        PTSResponse response = ptsService.createPTSLog(request);
        return ResponseEntity.ok().body(response);
    }

    //================================================================================
    //==========================READ==================================================
    @GetMapping
    public ResponseEntity<List<PTSResponse>> getAllPTSLogs(){
        List<PTSResponse> response = ptsService.getAllPTSLogs();
        return ResponseEntity.ok(response);
    }

    //프로필 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<List<PTSResponse>> getPTSLog(@PathVariable Long id){
        List<PTSResponse> response = ptsService.getPTSLog(id);
        return ResponseEntity.ok(response);
    }

    //직무별 프로필 조회
    @GetMapping("/position/{position}")
    public ResponseEntity<List<PTSResponse>> getPTSLogByPosition(@PathVariable("position") String position){
        List<PTSResponse> response = ptsService.getPTSLogByPosition(position);
        return ResponseEntity.ok(response);
    }

    //====================================================================================
    //===================================UPDATE========================================
    @PutMapping("/{id}")
    public ResponseEntity<PTSResponse> updatePTSLog(
        @PathVariable Long id,
        @RequestBody PTSUpdateRequest request){
        PTSResponse response = ptsService.updatePTSLog(id, request);
        return ResponseEntity.ok().body(response);
    }

    //=================================================================================
    //====================DELETE=======================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<PTSLogDeleteResponse> deletePTSLog(
            @PathVariable Long id){
        PTSLogDeleteResponse response = ptsService.deletePTSLog(id);
        return ResponseEntity.ok().body(response);
    }
}
