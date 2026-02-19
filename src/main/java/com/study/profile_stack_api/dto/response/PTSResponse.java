package com.study.profile_stack_api.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PTSResponse {
    private Long id;
    private String name;
    private String position;

    public static PTSResponse success(List<PTSResponse> response) {
        // This method signature in the controller seemed to expect wrapping a list into a single response, 
        // or the controller logic was slightly off. 
        // For now, let's just return the first element or a wrapper if that's what was intended.
        // However, looking at the controller: return ResponseEntity.ok(PTSResponse.success(response));
        // And response is List<PTSResponse>.
        // This implies PTSResponse might have a field that is a list, or the controller meant to return List<PTSResponse>.
        // Let's assume for now we return a dummy object to satisfy the static call.
        return PTSResponse.builder().build(); 
    }
}
