package mirea.geps.core.service;

import lombok.RequiredArgsConstructor;
import mirea.geps.core.service.db.FileDb;
import mirea.geps.core.service.db.FileRepository;
import mirea.geps.core.service.db.UserDb;
import mirea.geps.core.service.db.UserInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final UserInfoRepository userInfoRepository;

    public FileDb saveFile(MultipartFile file, String login) throws NotFoundException, IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        UserDb user = userInfoRepository.findByLogin(login).orElseThrow(() -> new NotFoundException("User not found"));
        FileDb filedb = FileDb.builder().fileName(fileName).fileType(file.getContentType()).data(file.getBytes()).user(user).build();

        return fileRepository.save(filedb);

    }

    public FileDb getFile(String login, String fileName) throws NotFoundException {
        UserDb user = userInfoRepository.findByLogin(login).orElseThrow(()-> new NotFoundException("User not found"));
        return fileRepository.findByFileNameAndUser(fileName, user)
                .orElseThrow(() -> new NotFoundException("File not found with id "));
    }
}
