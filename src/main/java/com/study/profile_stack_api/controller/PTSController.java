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
    private final PTSController ptscontroller;

    public PTSController(PTSController ptscontroller) {
        this.ptscontroller = ptscontroller;}
    //=================================================================================
    //======================CREATE=====================================================
    @PostMapping
    public ResponseEntity<PTSResponse> getPtsController(@RequestBody PTSCreateRequest request){
        PTSResponse response = PTSService.getPTSController(request);
        return ResponseEntity.ok().body(response);
    }

    //================================================================================
    //==========================READ==================================================
    @GetMapping
    public ResponseEntity<PTSResponse> getAllPTSLogs(){
        List<PTSResponse> response = PTSService.getAllPTSLogs();
        return ResponseEntity.ok(PTSResponse.success(response));
    }

    //프로필 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<PTSResponse> getPTSLog(@PathVariable Long id){
        List<PTSResponse> response = PTSService.getAllPTSLogs(id);
        return ResponseEntity.ok(PTSResponse.success(response));
    }
    //직무별 프로필 조회
    @GetMapping("/position/{position}")
    public ResponseEntity<PTSResponse> getPTSLog(@PathVariable("position") String position){
        List<PTSResponse> response = PTSService.getPTSLogByPositon(position);
        return ResponseEntity.ok(PTSResponse.success(response));
    }
    //====================================================================================
    //===================================UPDATE========================================
    @GetMapping("/{id}")
    public ResponseEntity<PTSResponse> PTSLogUpdate(
        @PathVariable Long id,
        @RequestBody PTSUpdateRequest request){
     PTSResponse response = PTSService.updatePTSLog(id, request);
     return ResponseEntity.ok().body(response);
    }
    //=================================================================================
    //====================DELETE=======================================================
    @GetMapping("/{id}")
    public ResponseEntity<PTSLogDeleteResponse> deletePTSLog(
            @PathVariable Long id){
        PTSLogDeleteResponse response = PTSService.deletePTSLog(id);
        return ResponseEntity.ok().body(response);
    }
    )
}
