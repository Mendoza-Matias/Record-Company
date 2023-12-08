package com.record.company.com.bussines.servicesImpl;

import com.record.company.com.bussines.IAlbumFileServices;
import com.record.company.com.domain.dto.albumFile.AlbumFileDto;
import com.record.company.com.domain.entity.AlbumFile;
import com.record.company.com.exception.NotFoundException;
import com.record.company.com.persistence.repository.IAlbumFileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class AlbumFileServicesImpl implements IAlbumFileServices {

    @Autowired
    IAlbumFileRepository albumFileRepository;

    @Autowired
    ModelMapper modelMapper;

    private final String FILE_PATH = "E:\\Proyectos-Java\\record.company\\src\\main\\resources\\static\\image";
    @Override
    public List<AlbumFileDto> getAllAlbumFile() {
        return null;
    }

    @Override
    public AlbumFileDto getAlbumFileById(int id) {
        return null;
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

            if(fileSize > maxFileSize){
                return null;
            }

            if(!fileOriginalName.endsWith(".jpg") &&
                    !fileOriginalName.endsWith(".jpeg")
            && !fileOriginalName.endsWith(".png")

            ){
                return null;
            }

            String fileExtension = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
            String newFileName = fileName + fileExtension;

            File folder = new File(FILE_PATH);
            if(!folder.exists()){
                folder.mkdirs();
            }

            Path path = Paths.get(FILE_PATH +"/" + newFileName);
            Files.write(path,bytes);

            albumFile.setPath(FILE_PATH);
            albumFile.setNameImg(newFileName);
            albumFile.setType(fileExtension);

            return modelMapper.map(albumFileRepository.save(albumFile),AlbumFileDto.class);
        }
        catch (Exception e){
           throw  new RuntimeException("Error save image");
        }
    }

    @Override
    public AlbumFileDto updateAlbum(int id, MultipartFile file) {
        return null;
    }

    @Override
    public AlbumFileDto deleteAlbum(int id) {
        
        return null;
    }
}
