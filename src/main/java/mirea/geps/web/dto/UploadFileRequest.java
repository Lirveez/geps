package mirea.geps.web.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UploadFileRequest {

    @NotNull
    private MultipartFile file;
    @NotBlank
    private String login;
}
