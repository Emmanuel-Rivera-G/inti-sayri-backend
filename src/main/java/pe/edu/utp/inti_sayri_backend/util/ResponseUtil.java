package pe.edu.utp.inti_sayri_backend.util;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
        
    public static ResponseEntity<Map<String, Object>> createResponse(Map<String, Object> response, HttpStatus successStatus, HttpStatus errorStatus) {
        String status = (String) response.get("status");
        
        if ("success".equals(status)) {
            return new ResponseEntity<>(response, successStatus);
        } else {
            return new ResponseEntity<>(response, errorStatus);
        }
    }
    
}
