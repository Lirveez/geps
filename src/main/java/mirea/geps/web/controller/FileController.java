package mirea.geps.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mirea.geps.core.dto.EducationalPlan;
import mirea.geps.core.service.FileService;
import mirea.geps.core.service.NotFoundException;
import mirea.geps.core.service.db.EducationalPlanRepositoryCustom;
import mirea.geps.core.service.db.FileDb;
import mirea.geps.web.dto.EducationalPlanRequest;
import mirea.geps.web.dto.UploadEducationalPlanRequest;
import mirea.geps.web.dto.UploadFileRequest;
import mirea.geps.web.dto.UploadFileResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("eps/file")
public class FileController {

    private final EducationalPlanRepositoryCustom educationalPlanRepository;
    private final FileService fileService;

    @PostMapping("/get-plan")
    @ApiOperation("Запрос учебного плана по имени (JSON)")
    public EducationalPlan getEducationalPlanByName(@RequestBody @Valid @NotNull EducationalPlanRequest request) throws NotFoundException {
        Optional<EducationalPlan> found = educationalPlanRepository.findPlanByCustomName(request.getName());
        return found.orElseThrow(() -> new NotFoundException("Educational plan not found"));
    }

    @GetMapping("/get-plans")
    @ApiOperation("Запрос всех учебных планов")
    public List<EducationalPlan> getEducationalPlans() throws NotFoundException {
        List<EducationalPlan> found = educationalPlanRepository.findAll();
        return found;
    }

    @PostMapping("/upload-plan")
    @ApiOperation("Загрузка учебного плана в БД")
    public void uploadEducationalPlan(@RequestBody @Valid @NotNull UploadEducationalPlanRequest request) {
        EducationalPlan newEducationalPlan = new EducationalPlan(request.getCustomName(),
                request.getTrDirection(),
                request.getProfile(),
                request.getForm(),
                request.getPlan(),
                request.getDepartment(),
                request.getDisciplines());
        educationalPlanRepository.save(newEducationalPlan);
    }

    @PostMapping("/upload-file")
    @ApiOperation("Загрузка файла (BYTE)")
    public UploadFileResponse uploadFile(@RequestBody @Valid @NotNull UploadFileRequest request) throws NotFoundException, IOException {
        MultipartFile requestFile = request.getFile();
        FileDb file = fileService.saveFile(requestFile, request.getLogin());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download-file/")
                .path(request.getLogin())
                .path(file.getFileName())
                .toUriString();
        return new UploadFileResponse(file.getFileName(), fileDownloadUri,
                requestFile.getContentType(), requestFile.getSize());
    }


    @GetMapping("/download-file/{login}/{fileId}")
    @ApiOperation("Запрос файла (BYTE)")
    public ResponseEntity<Resource> downloadFile(@PathVariable String login, @PathVariable String filename) throws NotFoundException {
        // Load file from database
        FileDb dbFile = fileService.getFile(login, filename);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}
