package com.record.company.com.bussines.servicesImpl;

import com.record.company.com.bussines.IAlbumFileServices;
import com.record.company.com.domain.dto.albumFile.AlbumFileDto;
import com.record.company.com.domain.dto.albumFile.AlbumFileUrlDto;
import com.record.company.com.domain.entity.AlbumFile;
import com.record.company.com.exception.NotFoundException;
import com.record.company.com.persistence.repository.IAlbumFileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.File;

import java.io.IOException;
import java.io.NotActiveException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlbumFileServicesImpl implements IAlbumFileServices {

    @Autowired
    IAlbumFileRepository albumFileRepository;

    @Autowired
    ModelMapper modelMapper;

    private Path rootLocation;

    private final String FILE_PATH = "C:\\Users\\leona\\Desktop\\imagenes";

    @Override
    public List<AlbumFileDto> getAllAlbumFile() {
        return albumFileRepository.findAll().stream().map(albumFile -> modelMapper.map(albumFile, AlbumFileDto.class)).collect(Collectors.toList());
    }

    @Override
    public AlbumFileDto getFileById(int id) {
        return modelMapper.map(albumFileRepository.findById(id), AlbumFileDto.class);
    }

    @Override
    public Resource getAlbumFileById(int id) throws MalformedURLException {
        AlbumFile albumFile = albumFileRepository.findById(id).orElseThrow(() -> new NotFoundException("Id del album no encotrado"));

        Path imagePath = Paths.get(albumFile.getPath(), albumFile.getNameImg());
        Resource resource = new UrlResource(imagePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new NotFoundException("La imagen no se encuentra");
        }
    }

    @Override
    public AlbumFileDto creteAlbumFile(MultipartFile file) {

        AlbumFile albumFile = new AlbumFile();

        try {
            String fileName = UUID.randomUUID().toString();
            byte[] bytes = file.getBytes();
            String fileOriginalName = file.getOriginalFilename();


            long fileSize = file.getSize();
            long maxFileSize = 5 * 1024 * 1024;

            if (fileSize > maxFileSize) {
                return null;
            }

            if (!fileOriginalName.endsWith(".jpg") &&
                    !fileOriginalName.endsWith(".jpeg")
                    && !fileOriginalName.endsWith(".png")

            ) {
                throw new NotFoundException("Imagen no compatible");
            }

            String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
            String newFileName = fileName + fileExtension;


            File folder = new File(FILE_PATH);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            Path path = Paths.get(FILE_PATH + "/" + newFileName);
            Files.write(path, bytes);

            albumFile.setPath(FILE_PATH);
            albumFile.setNameImg(newFileName);
            albumFile.setType(fileExtension);
            albumFile.setUrlImageLocation("/api/v1/albumFile/" + newFileName);

            return modelMapper.map(albumFileRepository.save(albumFile), AlbumFileDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error save image");
        }
    }

    @Override
    public AlbumFileDto updateAlbum(int id, MultipartFile file) {

        Optional<AlbumFile> albumFileExist = albumFileRepository.findById(id);
        AlbumFile albumFile = new AlbumFile();

        if (albumFileExist.isPresent()) {
            albumFile = albumFileExist.get();

            try {
                String fileName = UUID.randomUUID().toString();
                byte[] bytes = file.getBytes();
                String fileOriginalName = file.getOriginalFilename();


                long fileSize = file.getSize();
                long maxFileSize = 5 * 1024 * 1024;

                if (fileSize > maxFileSize) {
                    return null;
                }

                if (!fileOriginalName.endsWith(".jpg") &&
                        !fileOriginalName.endsWith(".jpeg")
                        && !fileOriginalName.endsWith(".png")

                ) {
                    throw new NotFoundException("Imagen no compatible");
                }

                String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
                String newFileName = fileName + fileExtension;


                File folder = new File(FILE_PATH);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                Path path = Paths.get(FILE_PATH + "/" + newFileName);
                Files.write(path, bytes);

                albumFile.setPath(FILE_PATH);
                albumFile.setNameImg(newFileName);
                albumFile.setType(fileExtension);
                albumFile.setUrlImageLocation("/api/v1/albumFile/" + newFileName);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return modelMapper.map(albumFileRepository.save(albumFile), AlbumFileDto.class);
        }
        throw new NotFoundException("Error al almacenar la imagen");
    }

    @Override
    public AlbumFileDto deleteAlbum(int id) {
        Optional<AlbumFile> albumFileExist = albumFileRepository.findById(id);
        AlbumFile albumFile = new AlbumFile();

        if(albumFileExist.isPresent()){
            albumFile = albumFileExist.get();
            albumFileRepository.deleteById(id);
        }

        return modelMapper.map(albumFile,AlbumFileDto.class);
    }

}
